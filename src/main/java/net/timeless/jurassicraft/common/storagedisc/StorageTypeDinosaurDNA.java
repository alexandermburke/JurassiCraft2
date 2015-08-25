package net.timeless.jurassicraft.common.storagedisc;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.genetics.DinoDNA;
import net.timeless.jurassicraft.common.genetics.GeneticsContainer;
import net.timeless.jurassicraft.common.lang.AdvLang;

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
