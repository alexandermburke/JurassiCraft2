package net.timeless.jurassicraft.common.paleopad;

import net.minecraft.nbt.NBTTagCompound;

public class AppDinoPedia extends App
{
    private int selectedItem;

    @Override
    public String getName()
    {
        return "DinoPedia";
    }

    @Override
    public void update()
    {

    }

    public int getSelectedItem()
    {
        return selectedItem;
    }

    public void setSelectedItem(int item)
    {
        this.selectedItem = item;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("SelectedItem", selectedItem);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        selectedItem = nbt.getInteger("SelectedItem");
    }

    @Override
    public void init()
    {

    }
}
