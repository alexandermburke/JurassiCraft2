package org.jurassicraft.common.storagedisc;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jurassicraft.common.genetics.DinoDNA;

import java.util.List;

public class StorageTypeDinosaurDNA implements IStorageType
{
    private DinoDNA dna;

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        dna.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        dna = DinoDNA.readFromNBT(nbt);
    }

    @Override
    public void addInformation(ItemStack stack, List tooltip)
    {
        dna.addInformation(stack, tooltip);
    }
}
