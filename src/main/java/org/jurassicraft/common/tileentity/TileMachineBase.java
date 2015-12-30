package org.jurassicraft.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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

public abstract class TileMachineBase extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
    protected String customName;

    protected int[] processTime = new int[getProcessCount()];
    protected int[] totalProcessTime = new int[getProcessCount()];

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        NBTTagList itemList = compound.getTagList("Items", 10);
        ItemStack[] slots = new ItemStack[getSlots().length];

        for (int i = 0; i < itemList.tagCount(); ++i)
        {
            NBTTagCompound item = itemList.getCompoundTagAt(i);

            byte slot = item.getByte("Slot");

            if (slot >= 0 && slot < slots.length)
            {
                slots[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }

        for (int i = 0; i < getProcessCount(); i++)
        {
            processTime[i] = compound.getShort("ProcessTime" + i);
            totalProcessTime[i] = compound.getShort("ProcessTimeTotal" + i);
        }

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }

        setSlots(slots);
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        for (int i = 0; i < getProcessCount(); i++)
        {
            compound.setShort("ProcessTime" + i, (short) this.processTime[i]);
            compound.setShort("ProcessTimeTotal" + i, (short) this.totalProcessTime[i]);
        }

        ItemStack[] slots = getSlots();

        NBTTagList itemList = new NBTTagList();

        for (int slot = 0; slot < getSizeInventory(); ++slot)
        {
            if (slots[slot] != null)
            {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte) slot);

                slots[slot].writeToNBT(itemTag);
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
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int index)
    {
        return getSlots()[index];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a new stack.
     */
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack[] slots = getSlots();

        if (slots[index] != null)
        {
            ItemStack stack;

            if (slots[index].stackSize <= count)
            {
                stack = slots[index];
                slots[index] = null;
                return stack;
            }
            else
            {
                stack = slots[index].splitStack(count);

                if (slots[index].stackSize == 0)
                {
                    slots[index] = null;
                }

                return stack;
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
        ItemStack[] slots = getSlots();

        if (slots[index] != null)
        {
            ItemStack itemstack = slots[index];
            slots[index] = null;
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
        ItemStack[] slots = getSlots();

        boolean flag = stack != null && stack.isItemEqual(slots[index]) && ItemStack.areItemStackTagsEqual(stack, slots[index]);
        slots[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (index < getInputs().length && !flag)
        {
            int i = getProcess(index);
            this.totalProcessTime[i] = this.getStackProcessTime(stack);
            this.processTime[i] = 0;
            worldObj.markBlockForUpdate(pos);
        }
    }

    private boolean isInput(int slot)
    {
        int[] inputs = getInputs();

        for (int i = 0; i < inputs.length; i++)
        {
            if (inputs[i] == slot)
            {
                return true;
            }
        }

        return false;
    }

    private boolean isOutput(int slot)
    {
        int[] outputs = getOutputs();

        for (int i = 0; i < outputs.length; i++)
        {
            if (outputs[i] == slot)
            {
                return true;
            }
        }

        return false;
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

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.getSlots().length;
    }

    public boolean isProcessing(int index)
    {
        return this.processTime[index] > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isProcessing(IInventory inventory, int index)
    {
        return inventory.getField(index) > 0;
    }

    /**
     * Updates the JList with a new model.
     */
    public void update()
    {
        ItemStack[] slots = getSlots();

        for (int i = 0; i < getProcessCount(); i++)
        {
            boolean flag = this.isProcessing(i);
            boolean sync = false;

            if (!this.worldObj.isRemote)
            {
                if (!this.isProcessing(i) && (slots[getMainInput(i)] == null))
                {
                    if (!this.isProcessing(i) && this.processTime[i] > 0)
                    {
                        this.processTime[i] = MathHelper.clamp_int(this.processTime[i] - 2, 0, this.totalProcessTime[i]);
                    }
                }
                else
                {
                    if (this.canProcess(i))
                    {
                        ++this.processTime[i];

                        if (this.processTime[i] == this.totalProcessTime[i])
                        {
                            this.processTime[i] = 0;
                            this.totalProcessTime[i] = this.getStackProcessTime(slots[getMainInput(i)]);
                            this.processItem(i);
                            sync = true;
                        }
                    }
                    else
                    {
                        this.processTime[i] = 0;
                        sync = true;
                    }
                }

                if (flag != this.isProcessing(i))
                {
                    sync = true;
                }
            }
            else
            {
                if (this.canProcess(i))
                {
                    ++this.processTime[i];
                }
            }

            if (sync)
            {
                worldObj.markBlockForUpdate(pos);
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
        return !isOutput(index);
    }

    public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? getOutputs() : getOutputs();
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: slot, item, side
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    protected abstract int getProcess(int slot);

    protected abstract boolean canProcess(int process);

    protected abstract void processItem(int process);

    protected abstract int getMainInput(int process);

    protected abstract int getMainOutput(int process);

    protected abstract int getStackProcessTime(ItemStack stack);

    protected abstract int getProcessCount();

    protected abstract int[] getInputs();

    protected abstract int[] getOutputs();

    protected abstract ItemStack[] getSlots();

    protected abstract void setSlots(ItemStack[] slots);

    public boolean hasOutputSlot(ItemStack output)
    {
        return getOutputSlot(output) != -1;
    }

    public int getOutputSlot(ItemStack output)
    {
        ItemStack[] slots = getSlots();

        int[] outputs = getOutputs();

        for (int i = 0; i < outputs.length; i++)
        {
            int slot = outputs[i];

            if (slots[slot] == null || (ItemStack.areItemStackTagsEqual(slots[slot], output) && slots[slot].getItem() == output.getItem()))
            {
                return slot;
            }
        }

        return -1;
    }

    public int getField(int id)
    {
        int processCount = getProcessCount();

        if (id < processCount)
        {
            return processTime[id];
        }
        else if (id < processCount * 2)
        {
            return totalProcessTime[id - processCount];
        }

        return 0;
    }

    public void setField(int id, int value)
    {
        int processCount = getProcessCount();

        if (id < processCount)
        {
            processTime[id] = value;
        }
        else if (id < processCount * 2)
        {
            totalProcessTime[id - processCount] = value;
        }
    }

    public int getFieldCount()
    {
        return getProcessCount() * 2;
    }

    public void clear()
    {
        ItemStack[] slots = getSlots();

        for (int i = 0; i < slots.length; ++i)
        {
            slots[i] = null;
        }
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: slot, item, side
     */
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) // TODO
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

    protected void mergeStack(int slot, ItemStack stack)
    {
        ItemStack[] slots = getSlots();

        if (slots[slot] == null)
        {
            slots[slot] = stack;
        }
        else if (slots[slot].getItem() == stack.getItem() && ItemStack.areItemStackTagsEqual(slots[slot], stack))
        {
            slots[slot].stackSize += stack.stackSize;
        }
    }

    protected void decreaseStackSize(int slot)
    {
        ItemStack[] slots = getSlots();

        slots[slot].stackSize--;

        if (slots[slot].stackSize <= 0)
        {
            slots[slot] = null;
        }
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
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
