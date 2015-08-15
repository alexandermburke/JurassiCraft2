package net.timeless.jurassicraft.common.genetics;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DNA
{
    private int quality;
    private GeneticsContainer genetics;

    public DNA(int quality, String genetics)
    {
        this.quality = quality;
        this.genetics = new GeneticsContainer(genetics);
    }

    public DNA(ItemStack stack)
    {
        this(stack.getTagCompound().getInteger("DNAQuality"), stack.getTagCompound().getString("Genetics"));
    }

    public static DNA readFromNBT(NBTTagCompound nbt)
    {
        return new DNA(nbt.getInteger("DNAQuality"), nbt.getString("Genetics"));
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("DNAQuality", quality);
        nbt.setString("Genetics", genetics.toString());
    }

    public String toString()
    {
        return genetics.toString();
    }

    public GeneticsContainer getContainer()
    {
        return genetics;
    }
}
