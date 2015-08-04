package net.timeless.jurassicraft.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.container.ContainerDNASequencer;
import net.timeless.jurassicraft.common.dna.DNA;
import net.timeless.jurassicraft.common.dna.GeneticsHelper;
import net.timeless.jurassicraft.common.entity.data.JCPlayerData;
import net.timeless.jurassicraft.common.item.ItemPaleoTab;
import net.timeless.jurassicraft.common.item.ItemSoftTissue;

import java.util.Random;
import java.util.UUID;

public class TileDnaSequencer extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
    private static final int[] slotsTop = new int[] { 0 }; //input
    private static final int[] slotsBottom = new int[] { 1 }; //output
    private static final int[] slotsSides = new int[] {};

    /** The ItemStacks that hold the items currently being used in the dna sequencer */
    private ItemStack[] slots = new ItemStack[7];

    private int sequenceTime;
    private int totalSequenceTime;

    private String customName;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.slots.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int index)
    {
        return this.slots[index];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.slots[index] != null)
        {
            ItemStack itemstack;

            if (this.slots[index].stackSize <= count)
            {
                itemstack = this.slots[index];
                this.slots[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.slots[index].splitStack(count);

                if (this.slots[index].stackSize == 0)
                {
                    this.slots[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (this.slots[index] != null)
        {
            ItemStack itemstack = this.slots[index];
            this.slots[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        boolean flag = stack != null && stack.isItemEqual(this.slots[index]) && ItemStack.areItemStackTagsEqual(stack, this.slots[index]);
        this.slots[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (index == 0 && !flag)
        {
            this.totalSequenceTime = this.getStackSequenceTime(stack);
            this.sequenceTime = 0;
            worldObj.markBlockForUpdate(pos);
        }
    }

    /**
     * Gets the name of this command sender (usually username, but possibly "Rcon")
     */
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.dna_sequencer";
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName()
    {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setCustomInventoryName(String customName)
    {
        this.customName = customName;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        NBTTagList itemList = compound.getTagList("Items", 10);
        this.slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < itemList.tagCount(); ++i)
        {
            NBTTagCompound item = itemList.getCompoundTagAt(i);

            byte slot = item.getByte("Slot");

            if (slot >= 0 && slot < this.slots.length)
            {
                this.slots[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }

        this.sequenceTime = compound.getShort("SequenceTime");
        this.totalSequenceTime = compound.getShort("SequenceTimeTotal");

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("SequenceTime", (short) this.sequenceTime);
        compound.setShort("SequenceTimeTotal", (short) this.totalSequenceTime);
        NBTTagList itemList = new NBTTagList();

        for (int slot = 0; slot < this.slots.length; ++slot)
        {
            if (this.slots[slot] != null)
            {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte) slot);

                this.slots[slot].writeToNBT(itemTag);
                itemList.appendTag(itemTag);
            }
        }

        compound.setTag("Items", itemList);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isSequenceing()
    {
        return this.sequenceTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isSequenceing(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }

    /**
     * Updates the JList with a new model.
     */
    public void update()
    {
        boolean flag = this.isSequenceing();
        boolean sync = false;

        if (!this.worldObj.isRemote)
        {
            if (!this.isSequenceing() && (this.slots[0] == null))
            {
                if (!this.isSequenceing() && this.sequenceTime > 0)
                {
                    this.sequenceTime = MathHelper.clamp_int(this.sequenceTime - 2, 0, this.totalSequenceTime);
                }
            }
            else
            {
                if (this.canSequence())
                {
                    ++this.sequenceTime;

                    if (this.sequenceTime == this.totalSequenceTime)
                    {
                        this.sequenceTime = 0;
                        this.totalSequenceTime = this.getStackSequenceTime(this.slots[0]);
                        this.sequenceItem();
                        sync = true;
                    }
                }
                else
                {
                    this.sequenceTime = 0;
                    sync = true;
                }
            }

            if (flag != this.isSequenceing())
            {
                sync = true;
            }
        }
        else
        {
            if (this.canSequence())
            {
                ++this.sequenceTime;
            }
        }

        if (sync)
        {
            worldObj.markBlockForUpdate(pos);
        }
    }

    public int getStackSequenceTime(ItemStack stack)
    {
        return 200;
    }

    /**
     * Returns true if the dna sequencer can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSequence()
    {
        ItemStack input = this.slots[0];
        ItemStack storage = this.slots[1];

        if (input != null && input.getItem() instanceof ItemSoftTissue)
        {
            if (storage != null && storage.getItem() instanceof ItemPaleoTab)
                if (worldObj.getPlayerEntityByUUID(UUID.fromString(storage.getTagCompound().getString("LastOwner"))) != null)
                    return true;
        }

        return false;
    }

    /**
     * Turn one item from the dna sequencer source stack into the appropriate sequenceed item in the dna sequencer result stack
     */
    public void sequenceItem()
    {
        if (this.canSequence())
        {
            Random rand = new Random();

            EntityPlayer player = worldObj.getPlayerEntityByUUID(UUID.fromString(slots[1].getTagCompound().getString("LastOwner")));

            int quality = rand.nextInt(25) + 1;

            if (rand.nextInt(3) == 0)
            {
                quality += 25;

                if (rand.nextInt(3) == 0)
                {
                    quality += 25;

                    if (rand.nextInt(3) == 0)
                    {
                        quality += 25;
                    }
                }
            }

            JCPlayerData.getPlayerData(player).addSequencedDNA(new DNA(quality, GeneticsHelper.randomGenetics(rand, slots[0].getItemDamage(), quality).toString()));

            slots[0].stackSize--;

            if (slots[0].stackSize <= 0)
            {
                slots[0] = null;
            }
        }
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public void openInventory(EntityPlayer player)
    {
    }

    public void closeInventory(EntityPlayer player)
    {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return index == 2 ? false : true;
    }

    public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: slot, item,
     * side
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: slot, item,
     * side
     */
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.water_bucket && item != Items.bucket)
            {
                return false;
            }
        }

        return true;
    }

    public String getGuiID()
    {
        return JurassiCraft.modid + ":dna_sequencer";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerDNASequencer(playerInventory, this);
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.sequenceTime;
            case 1:
                return this.totalSequenceTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.sequenceTime = value;
                break;
            case 1:
                this.totalSequenceTime = value;
        }
    }

    public int getFieldCount()
    {
        return 2;
    }

    public void clear()
    {
        for (int i = 0; i < this.slots.length; ++i)
        {
            this.slots[i] = null;
        }
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeToNBT(compound);
        return new S35PacketUpdateTileEntity(this.pos, this.getBlockMetadata(), compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        NBTTagCompound compound = packet.getNbtCompound();
        this.readFromNBT(compound);
    }
}