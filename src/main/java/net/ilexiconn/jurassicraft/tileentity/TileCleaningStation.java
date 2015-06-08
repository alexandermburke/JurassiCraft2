package net.ilexiconn.jurassicraft.tileentity;

import net.ilexiconn.jurassicraft.entity.base.JCEntityRegistry;
import net.ilexiconn.jurassicraft.item.ItemEncasedFossil;
import net.ilexiconn.jurassicraft.item.JCItemRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;

public class TileCleaningStation extends TileEntity implements ISidedInventory
{

    private ItemStack[] slots = new ItemStack[7];
    private static final int[] slotFossilBlock = new int[] { 0 };
    private static final int[] slotFossil = new int[] { 1, 2, 3, 4, 5, 6 };
    private String tileCustomName;

    public TileCleaningStation()
    {

    }

    /**
     * Returns if the player can clean an encased fossil before removing the ones on the table
     */
    public boolean canCleanFossil()
    {
        for (int slot : slotFossil)
            if (slots[slot] == null)
                return true;
        return false;
    }

    /**
     * Returns if the player can clean a fossil before removing the ones on the table
     */
    public void cleanFossil()
    {
        ItemStack encasedFossil = slots[0];
        if (encasedFossil != null && encasedFossil.getItem() instanceof ItemEncasedFossil)
        {
            ItemStack fossil = new ItemStack(JCItemRegistry.fossil);
            int periodID = encasedFossil.getMetadata();
            int dinosaurID = this.worldObj.rand.nextInt(JCEntityRegistry.getDinosaursFromPeriod(periodID).size());
            fossil.setItemDamage(dinosaurID);

            for (int slot : slotFossil)
                if (slots[slot] == fossil)
                {
                    slots[slot].stackSize++;
                    return;
                }

            for (int slot : slotFossil)
                if (slots[slot] == null)
                {
                    slots[slot] = fossil;
                    return;
                }
        }
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? slotFossil : slotFossilBlock;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        return false;
    }

    @Override
    public int getSizeInventory()
    {
        return this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.slots[index];
    }

    @Override
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
                    this.slots[index] = null;
                return itemstack;
            }
        }
        else
            return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (this.slots[index] != null)
        {
            ItemStack itemstack = this.slots[index];
            this.slots[index] = null;
            return itemstack;
        }
        else
            return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        boolean flag = stack != null && stack.isItemEqual(this.slots[index]) && ItemStack.areItemStackTagsEqual(stack, this.slots[index]);
        this.slots[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
            stack.stackSize = this.getInventoryStackLimit();
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) != this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player)
    {

    }

    @Override
    public void closeInventory(EntityPlayer player)
    {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return false;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {

    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < this.slots.length; i++)
            this.slots[i] = null;
    }

    @Override
    public String getName()
    {
        return this.hasCustomName() ? this.tileCustomName : "container.cleaning_station";
    }

    @Override
    public boolean hasCustomName()
    {
        return this.tileCustomName != null && this.tileCustomName.length() > 0;
    }

    public void setCustomInventoryName(String name)
    {
        this.tileCustomName = name;
    }

    @Override
    public IChatComponent getDisplayName()
    {
        return (IChatComponent) (this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.slots.length; ++i)
        {
            if (this.slots[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.slots[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        compound.setTag("Items", nbttaglist);

        if (this.hasCustomName())
            compound.setString("CustomName", this.tileCustomName);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.slots = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");
            if (b0 >= 0 && b0 < this.slots.length)
                this.slots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
        }

        if (compound.hasKey("CustomName", 8))
            this.tileCustomName = compound.getString("CustomName");
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
