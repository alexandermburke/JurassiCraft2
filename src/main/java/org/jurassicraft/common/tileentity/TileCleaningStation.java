package org.jurassicraft.common.tileentity;

import net.minecraft.block.Block;
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
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.block.BlockEncasedFossil;
import org.jurassicraft.common.container.ContainerCleaningStation;
import org.jurassicraft.common.item.JCItemRegistry;
import org.jurassicraft.common.item.itemblock.ItemEncasedFossil;

public class TileCleaningStation extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
    private static final int[] slotsTop = new int[] { 0 };
    private static final int[] slotsBottom = new int[] { 7, 6, 5, 4, 3, 2, 1 };
    private static final int[] slotsSides = new int[] { 1 }; // 0 = cleaning 1 = fuel 2 = output

    /**
     * The ItemStacks that hold the items currently being used in the cleaning station
     */
    private ItemStack[] slots = new ItemStack[8];

    /**
     * The number of ticks that the cleaning station will keep washing
     */
    private int cleaningStationWaterTime;

    /**
     * The number of ticks that a fresh copy of the currently-washing item would keep the cleaning station washing for
     */
    private int currentItemWaterTime;

    private int cleanTime;
    private int totalCleanTime;

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
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a new stack.
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
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem - like when you close a workbench GUI.
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
            this.totalCleanTime = this.getStackWashTime(stack);
            this.cleanTime = 0;
            worldObj.markBlockForUpdate(pos);
        }
    }

    /**
     * Gets the name of this command sender (usually username, but possibly "Rcon")
     */
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.cleaning_station";
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

        this.cleaningStationWaterTime = compound.getShort("WaterTime");
        this.cleanTime = compound.getShort("CleanTime");
        this.totalCleanTime = compound.getShort("CleanTimeTotal");
        this.currentItemWaterTime = getItemCleanTime(this.slots[1]);

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("WaterTime", (short) this.cleaningStationWaterTime);
        compound.setShort("CleanTime", (short) this.cleanTime);
        compound.setShort("CleanTimeTotal", (short) this.totalCleanTime);
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
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isCleaning()
    {
        return this.cleaningStationWaterTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isCleaning(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }

    /**
     * Updates the JList with a new model.
     */
    public void update()
    {
        boolean flag = this.isCleaning();
        boolean sync = false;

        if (this.isCleaning() && canClean())
        {
            --this.cleaningStationWaterTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (!this.isCleaning() && (this.slots[1] == null || this.slots[0] == null))
            {
                if (!this.isCleaning() && this.cleanTime > 0)
                {
                    this.cleanTime = MathHelper.clamp_int(this.cleanTime - 2, 0, this.totalCleanTime);
                }
            }
            else
            {
                if (!this.isCleaning() && this.canClean() && isItemFuel(slots[1]))
                {
                    this.currentItemWaterTime = this.cleaningStationWaterTime = getItemCleanTime(this.slots[1]);

                    if (this.isCleaning())
                    {
                        sync = true;

                        if (this.slots[1] != null)
                        {
                            --this.slots[1].stackSize;

                            if (this.slots[1].stackSize == 0)
                            {
                                this.slots[1] = slots[1].getItem().getContainerItem(slots[1]);
                            }
                        }
                    }
                }

                if (this.isCleaning() && this.canClean() && cleaningStationWaterTime > 0)
                {
                    ++this.cleanTime;

                    if (this.cleanTime == this.totalCleanTime)
                    {
                        this.cleanTime = 0;
                        this.totalCleanTime = this.getStackWashTime(this.slots[0]);
                        this.cleanItem();
                        sync = true;
                    }
                }
                else
                {
                    this.cleanTime = 0;
                    sync = true;
                }
            }

            if (flag != this.isCleaning())
            {
                sync = true;
            }
        }

        if (cleaningStationWaterTime == 0)
        {
            this.cleanTime = 0;
        }

        if (sync)
        {
            worldObj.markBlockForUpdate(pos);
        }
    }

    private int getItemCleanTime(ItemStack itemStack)
    {
        return 1600;
    }

    public int getStackWashTime(ItemStack stack)
    {
        return 200;
    }

    /**
     * Returns true if the cleaning station can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canClean()
    {
        if (this.slots[0] != null && this.slots[0].getItem() instanceof ItemEncasedFossil)
        {
            for (int i = 2; i < 8; i++)
            {
                if (this.slots[i] == null)
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Turn one item from the cleaning station source stack into the appropriate cleaned item in the cleaning station result stack
     */
    public void cleanItem()
    {
        if (this.canClean())
        {
            ItemStack fossil = new ItemStack(worldObj.rand.nextBoolean() ? JCItemRegistry.skull : JCItemRegistry.tooth, 1, JurassiCraft.blockRegistry.getDinosaurId((BlockEncasedFossil) Block.getBlockFromItem(slots[0].getItem()), slots[0].getItemDamage()));

            int emptySlot = -1;

            for (int i = 2; i < 8; i++)
            {
                if (this.slots[i] == null || (ItemStack.areItemsEqual(slots[i], fossil) && ItemStack.areItemStackTagsEqual(slots[i], fossil)))
                {
                    emptySlot = i;

                    break;
                }
            }

            if (emptySlot != -1)
            {
                if (this.slots[emptySlot] == null)
                {
                    this.slots[emptySlot] = fossil;
                }
                else if (this.slots[emptySlot].getItem() == fossil.getItem() && ItemStack.areItemStackTagsEqual(this.slots[emptySlot], fossil))
                {
                    this.slots[emptySlot].stackSize += fossil.stackSize;
                }

                this.slots[0].stackSize--;

                if (this.slots[0].stackSize <= 0)
                {
                    this.slots[0] = null;
                }
            }
        }
    }

    public static boolean isItemFuel(ItemStack stack)
    {
        return stack != null ? stack.getItem() == Items.water_bucket : false;
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
        return index == 2 ? false : (index != 1 ? true : isItemFuel(stack));
    }

    public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: slot, item, side
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: slot, item, side
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
        return JurassiCraft.MODID + ":cleaning_station";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerCleaningStation(playerInventory, this);
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.cleaningStationWaterTime;
            case 1:
                return this.currentItemWaterTime;
            case 2:
                return this.cleanTime;
            case 3:
                return this.totalCleanTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.cleaningStationWaterTime = value;
                break;
            case 1:
                this.currentItemWaterTime = value;
                break;
            case 2:
                this.cleanTime = value;
                break;
            case 3:
                this.totalCleanTime = value;
        }
    }

    public int getFieldCount()
    {
        return 4;
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
