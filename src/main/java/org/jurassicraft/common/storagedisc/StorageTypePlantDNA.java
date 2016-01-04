package org.jurassicraft.common.storagedisc;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jurassicraft.common.genetics.PlantDNA;

import java.util.List;

public class StorageTypePlantDNA implements IStorageType
{
    private PlantDNA dna;

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        dna.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        dna = PlantDNA.readFromNBT(nbt);
    }

    @Override
    public void addInformation(ItemStack stack, List<String> tooltip)
    {
        dna.addInformation(tooltip);
    }
}
