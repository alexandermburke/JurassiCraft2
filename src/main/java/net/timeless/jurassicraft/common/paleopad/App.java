package net.timeless.jurassicraft.common.paleopad;

import net.minecraft.nbt.NBTTagCompound;

public abstract class App
{
    public abstract String getName();

    public abstract void update();

    public abstract void writeToNBT(NBTTagCompound nbt);
    public abstract void readFromNBT(NBTTagCompound nbt);

    public abstract void init();
}
