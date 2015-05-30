package net.reuxertz.ecocraft.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDroppedItem extends TileEntity
{
    //Dropped Item Fields
    protected ItemStack _myDroppedItem = null;

    //Properties
    public ItemStack getDroppedItem()
    {
        return this._myDroppedItem;
    }

    //NBT Functions
    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setTag("droppedItem", this._myDroppedItem.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        if (nbt.hasKey("droppedItem"))
            this._myDroppedItem.loadItemStackFromNBT(nbt.getCompoundTag("droppedItem"));
        else
        {
            this.worldObj.removeTileEntity(this.pos);
            this.worldObj.destroyBlock(this.pos, false);
            this.markDirty();
            return;
        }
    }

    //Properties
    public void SetStack(ItemStack is)
    {
        this._myDroppedItem = is;
    }

    //Constructors
    public TileEntityDroppedItem()
    {
        super();
    }
}
