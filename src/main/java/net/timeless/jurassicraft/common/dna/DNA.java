package net.timeless.jurassicraft.common.dna;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DNA
{
    private int quality;
    private String genetics;

    public DNA(int quality, String genetics)
    {
        this.quality = quality;
        this.genetics = genetics;
    }

    public DNA(ItemStack stack)
    {
        this(stack.getTagCompound().getInteger("DNAQuality"), stack.getTagCompound().getString("Genetics"));
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("DNAQuality", quality);
        nbt.setString("Genetics", genetics);
    }

    public static DNA readFromNBT(NBTTagCompound nbt)
    {
        return new DNA(nbt.getInteger("DNAQuality"), nbt.getString("Genetics"));
    }
}
