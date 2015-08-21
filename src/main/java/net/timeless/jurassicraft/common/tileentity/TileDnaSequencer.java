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
import net.timeless.jurassicraft.common.genetics.DNA;
import net.timeless.jurassicraft.common.genetics.GeneticsHelper;
import net.timeless.jurassicraft.common.item.ItemSoftTissue;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

import java.util.Random;

public class TileDnaSequencer extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
    private static final int[] slotsTop = new int[] { 0, 1, 2, 3, 4, 5 }; //input
    private static final int[] slotsBottom = new int[] { 6, 7, 8 }; //output
    private static final int[] slotsSides = new int[] {};

    /** The ItemStacks that hold the items currently being used in the dna sequencer */
    private ItemStack[] slots = new ItemStack[9];

    private int[] sequenceTime = new int[3];
    private int[] totalSequenceTime = new int[3];

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

        if (index < 6 && !flag)
        {
            int i = (int) Math.floor(index / 2);
            this.totalSequenceTime[i] = this.getStackSequenceTime(stack);
            this.sequenceTime[i] = 0;
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

        for (int i = 0; i < 3; i++)
        {
            this.sequenceTime[i] = compound.getShort("SequenceTime" + i);
            this.totalSequenceTime[i] = compound.getShort("SequenceTimeTotal" + i);
        }

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        for (int i = 0; i < 3; i++)
        {
            compound.setShort("SequenceTime" + i, (short) this.sequenceTime[i]);
            compound.setShort("SequenceTimeTotal" + i, (short) this.totalSequenceTime[i]);
        }

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

    public boolean isSequencing(int index)
    {
        return this.sequenceTime[index] > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isSequencing(IInventory inventory, int index)
    {
        return inventory.getField(index) > 0;
    }

    /**
     * Updates the JList with a new model.
     */
    public void update()
    {
        for (int i = 0; i < 3; i++)
        {
            boolean flag = this.isSequencing(i);
            boolean sync = false;

            if (!this.worldObj.isRemote)
            {
                if (!this.isSequencing(i) && (this.slots[i * 2] == null))
                {
                    if (!this.isSequencing(i) && this.sequenceTime[i] > 0)
                    {
                        this.sequenceTime[i] = MathHelper.clamp_int(this.sequenceTime[i] - 2, 0, this.totalSequenceTime[i]);
                    }
                }
                else
                {
                    if (this.canSequence(i))
                    {
                        ++this.sequenceTime[i];

                        if (this.sequenceTime[i] == this.totalSequenceTime[i])
                        {
                            this.sequenceTime[i] = 0;
                            this.totalSequenceTime[i] = this.getStackSequenceTime(this.slots[i * 2]);
                            this.sequenceItem(i);
                            sync = true;
                        }
                    }
                    else
                    {
                        this.sequenceTime[i] = 0;
                        sync = true;
                    }
                }

                if (flag != this.isSequencing(i))
                {
                    sync = true;
                }
            }
            else
            {
                if (this.canSequence(i))
                {
                    ++this.sequenceTime[i];
                }
            }

            if (sync)
            {
                worldObj.markBlockForUpdate(pos);
            }
        }
    }

    public int getStackSequenceTime(ItemStack stack)
    {
        return 2000;
    }

    /**
     * Returns true if the dna sequencer can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSequence(int index)
    {
        int tissue = index * 2;

        ItemStack input = this.slots[tissue];
        ItemStack storage = this.slots[tissue + 1];

        if (input != null && input.getItem() instanceof ItemSoftTissue)
        {
            if (storage != null && storage.getItem() == JCItemRegistry.storage_disc)
            {
                ItemStack output = new ItemStack(JCItemRegistry.storage_disc, 1, input.getItemDamage());
                output.setTagCompound(input.getTagCompound());

                if (this.slots[index + 6] == null || ItemStack.areItemsEqual(this.slots[index + 6], output) && ItemStack.areItemStackTagsEqual(this.slots[index + 6], output))
                    return true;
            }
        }

        return false;
    }

    /**
     * Turn one item from the dna sequencer source stack into the appropriate sequenceed item in the dna sequencer result stack
     */
    public void sequenceItem(int index)
    {
        if (this.canSequence(index))
        {
            Random rand = new Random();

            int tissue = index * 2;

//            EntityPlayer player = worldObj.getPlayerEntityByUUID(UUID.fromString(slots[1].getTagCompound().getString("LastOwner")));

            int quality = rand.nextInt(25) + 1;

            if (rand.nextDouble() < 0.10)
            {
                quality += 25;

                if (rand.nextDouble() < 0.10)
                {
                    quality += 25;

                    if (rand.nextDouble() < 0.10)
                    {
                        quality += 25;
                    }
                }
            }

            NBTTagCompound nbt = slots[tissue + 1].getTagCompound();

            if(nbt == null)
            {
                nbt = new NBTTagCompound();
            }

            DNA dna = new DNA(quality, GeneticsHelper.randomGenetics(rand, slots[tissue].getItemDamage(), quality).toString());
            dna.writeToNBT(nbt);

            ItemStack output = new ItemStack(JCItemRegistry.storage_disc, 1, slots[tissue].getItemDamage());
            output.setItemDamage(dna.getContainer().getDinosaur());
            output.setTagCompound(nbt);

//            JCPlayerData.getPlayerData(player).addSequencedDNA(new DNA(quality, GeneticsHelper.randomGenetics(rand, slots[0].getItemDamage(), quality).toString()));

            if (this.slots[index + 6] == null)
            {
                this.slots[index + 6] = output;
            }
            else if (this.slots[index + 6].getItem() == output.getItem() && ItemStack.areItemStackTagsEqual(this.slots[index + 6], output))
            {
                this.slots[index + 6].stackSize += output.stackSize;
            }

            this.slots[tissue].stackSize--;
            this.slots[tissue + 1].stackSize--;

            if (this.slots[tissue].stackSize <= 0)
                this.slots[tissue] = null;

            if (this.slots[tissue + 1].stackSize <= 0)
                this.slots[tissue + 1] = null;
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
        if(id < 3)
        {
            return sequenceTime[id];
        }
        else if(id < 6)
        {
            return totalSequenceTime[id - 3];
        }

        return 0;
    }

    public void setField(int id, int value)
    {
        if(id < 3)
        {
            sequenceTime[id] = value;
        }
        else if(id < 6)
        {
            totalSequenceTime[id - 3] = value;
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