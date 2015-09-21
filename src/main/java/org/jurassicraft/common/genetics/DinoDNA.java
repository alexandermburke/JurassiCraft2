package org.jurassicraft.common.genetics;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.lang.AdvLang;

import java.util.List;

public class DinoDNA
{
    private int quality;
    private GeneticsContainer genetics;

    public DinoDNA(int quality, String genetics)
    {
        this.quality = quality;
        this.genetics = new GeneticsContainer(genetics);
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("DNAQuality", quality);
        nbt.setString("Genetics", genetics.toString());
        nbt.setString("StorageId", "DinoDNA");
    }

    public static DinoDNA fromStack(ItemStack stack)
    {
        return readFromNBT(stack.getTagCompound());
    }

    public static DinoDNA readFromNBT(NBTTagCompound nbt)
    {
        return new DinoDNA(nbt.getInteger("DNAQuality"), nbt.getString("Genetics"));
    }

    public int getDNAQuality()
    {
        return quality;
    }

    public String toString()
    {
        return genetics.toString();
    }

    public GeneticsContainer getContainer()
    {
        return genetics;
    }

    public void addInformation(ItemStack stack, List tooltip)
    {
        tooltip.add(EnumChatFormatting.DARK_AQUA + new AdvLang("lore.dinosaur.name").withProperty("dino", "entity." + JCEntityRegistry.getDinosaurById(genetics.getDinosaur()).getName(genetics.getGeneticVariation()).toLowerCase() + ".name").build());

        EnumChatFormatting colour;

        if (quality > 75)
            colour = EnumChatFormatting.GREEN;
        else if (quality > 50)
            colour = EnumChatFormatting.YELLOW;
        else if (quality > 25)
            colour = EnumChatFormatting.GOLD;
        else
            colour = EnumChatFormatting.RED;

        tooltip.add(colour + new AdvLang("lore.dna_quality.name").withProperty("quality", quality + "").build());
        tooltip.add(EnumChatFormatting.BLUE + new AdvLang("lore.genetic_code.name").withProperty("code", genetics.toString()).build());
    }
}
