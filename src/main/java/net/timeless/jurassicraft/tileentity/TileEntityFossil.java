package net.timeless.jurassicraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFossil extends TileEntity
{
    private int dinosaurId;

    public TileEntityFossil()
    {
        super();
    }

    public TileEntityFossil(int id)
    {
        super();

        this.dinosaurId = id;
    }

    public int getDinosaurId()
    {
        return dinosaurId;
    }

    public void setDinosaurId(int id)
    {
        this.dinosaurId = id;
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        this.dinosaurId = nbt.getInteger("jcid");
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setInteger("jcid", dinosaurId);
    }
}
