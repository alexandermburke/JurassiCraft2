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
import net.timeless.jurassicraft.common.container.ContainerDNAExtractor;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.genetics.DNA;
import net.timeless.jurassicraft.common.genetics.GeneticsHelper;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

import java.util.List;
import java.util.Random;

public class TileDNAExtractor extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
    private static final int[] slotsTop = new int[]{0, 1}; //input
    private static final int[] slotsBottom = new int[]{2, 3, 4, 5}; //output
    private static final int[] slotsSides = new int[]{};

    /**
     * The ItemStacks that hold the items currently being used in the dna extractor
     */
    private ItemStack[] slots = new ItemStack[6];

    private int extractTime;
    private int totalExtractTime;

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
            this.totalExtractTime = this.getStackExtractTime(stack);
            this.extractTime = 0;
            worldObj.markBlockForUpdate(pos);
        }
    }

    /**
     * Gets the name of this command sender (usually username, but possibly "Rcon")
     */
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.dna_extractor";
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

        this.extractTime = compound.getShort("ExtractTime");
        this.totalExtractTime = compound.getShort("ExtractTimeTotal");

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("ExtractTime", (short) this.extractTime);
        compound.setShort("ExtractTimeTotal", (short) this.totalExtractTime);
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

    public boolean isExtracting()
    {
        return this.extractTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isExtracting(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }

    /**
     * Updates the JList with a new model.
     */
    public void update()
    {
        boolean flag = this.isExtracting();
        boolean sync = false;

        if (!this.worldObj.isRemote)
        {
            if (!this.isExtracting() && (this.slots[0] == null))
            {
                if (!this.isExtracting() && this.extractTime > 0)
                {
                    this.extractTime = MathHelper.clamp_int(this.extractTime - 2, 0, this.totalExtractTime);
                }
            }
            else
            {
                if (this.canExtract())
                {
                    ++this.extractTime;

                    if (this.extractTime == this.totalExtractTime)
                    {
                        this.extractTime = 0;
                        this.totalExtractTime = this.getStackExtractTime(this.slots[0]);
                        this.extract();
                        sync = true;
                    }
                }
                else
                {
                    this.extractTime = 0;
                    sync = true;
                }
            }

            if (flag != this.isExtracting())
            {
                sync = true;
            }
        }
        else
        {
            if (this.canExtract())
            {
                ++this.extractTime;
            }
        }

        if (sync)
        {
            worldObj.markBlockForUpdate(pos);
        }
    }

    public int getStackExtractTime(ItemStack stack)
    {
        return 2000;
    }

    /**
     * Returns true if the dna extractor can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canExtract()
    {
        ItemStack extraction = this.slots[0];
        ItemStack storage = this.slots[1];

        if (storage != null && storage.getItem() == JCItemRegistry.storage_disc && extraction != null && (extraction.getItem() == JCItemRegistry.amber || extraction.getItem() == JCItemRegistry.sea_lamprey) && (storage.getTagCompound() == null || !storage.getTagCompound().hasKey("Genetics")))
        {
            for (int i = 2; i < 6; i++)
            {
                if (slots[i] == null)
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Turn one item from the dna extractor source stack into the appropriate grinded item in the dna extractor result stack
     */
    public void extract()
    {
        if (this.canExtract())
        {
            if (slots[0].getItem() == JCItemRegistry.amber || slots[0].getItem() == JCItemRegistry.sea_lamprey)
            {
                if (slots[0].getItemDamage() == 0)
                {
                    Random rand = worldObj.rand;

                    List<Dinosaur> amberDinos = slots[0].getItem() == JCItemRegistry.amber ? JCEntityRegistry.getDinosaursFromAmber() : JCEntityRegistry.getDinosaursFromSeaLampreys();
                    Dinosaur dino = amberDinos.get(rand.nextInt(amberDinos.size()));

                    int dinosaurId = JCEntityRegistry.getDinosaurId(dino);

                    ItemStack disc = new ItemStack(JCItemRegistry.storage_disc, 1, dinosaurId);

                    int quality = rand.nextInt(50);

                    if (rand.nextDouble() < 0.1)
                    {
                        quality += 50;
                    }

                    DNA dna = new DNA(quality, GeneticsHelper.randomGenetics(rand, dinosaurId, quality).toString());

                    NBTTagCompound nbt = new NBTTagCompound();
                    dna.writeToNBT(nbt);

                    disc.setTagCompound(nbt);

                    int empty = 0;

                    for (int i = 2; i < 6; i++)
                    {
                        if (slots[i] == null)
                        {
                            empty = i;
                            break;
                        }
                    }

                    slots[empty] = disc;

                    this.slots[0].stackSize--;
                    this.slots[1].stackSize--;

                    if (this.slots[0].stackSize <= 0)
                        this.slots[0] = null;

                    if (this.slots[1].stackSize <= 0)
                        this.slots[1] = null;
                }
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
        return JurassiCraft.modid + ":dna_extractor";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerDNAExtractor(playerInventory, this);
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.extractTime;
            case 1:
                return this.totalExtractTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.extractTime = value;
                break;
            case 1:
                this.totalExtractTime = value;
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