package net.timeless.jurassicraft.common.dna;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DNA
{
    private int quality;
    private int dinosaur;
    
    public DNA(int quality, int dinosaur)
    {
        this.quality = quality;
        this.dinosaur = dinosaur;
    }
    
    public DNA(ItemStack stack)
    {
        this(stack.getTagCompound().getInteger("DNAQuality"), stack.getItemDamage());
    }
    
    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("DNAQuality", quality);
        nbt.setInteger("Dinosaur", dinosaur);
    }
    
    public static DNA readFromNBT(NBTTagCompound nbt)
    {
        return new DNA(nbt.getInteger("DNAQuality"), nbt.getInteger("Dinosaur"));
    }
}
