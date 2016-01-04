package org.jurassicraft.common.genetics;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import org.jurassicraft.common.lang.AdvLang;
import org.jurassicraft.common.plant.JCPlantRegistry;

import java.util.List;

public class PlantDNA
{
    private final int plant;
    private final int quality;

    public PlantDNA(int plant, int quality)
    {
        this.plant = plant;
        this.quality = quality;
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("DNAQuality", quality);
        nbt.setInteger("Plant", plant);
        nbt.setString("StorageId", "PlantDNA");
    }

    public static PlantDNA fromStack(ItemStack stack)
    {
        return readFromNBT(stack.getTagCompound());
    }

    public static PlantDNA readFromNBT(NBTTagCompound nbt)
    {
        return new PlantDNA(nbt.getInteger("Plant"), nbt.getInteger("DNAQuality"));
    }

    public int getDNAQuality()
    {
        return quality;
    }

    public int getPlant()
    {
        return plant;
    }

    public void addInformation(List<String> tooltip)
    {
        tooltip.add(EnumChatFormatting.DARK_AQUA + new AdvLang("lore.plant.name").withProperty("plant", "plants." + JCPlantRegistry.getPlantById(plant).getName().toLowerCase().replaceAll(" ", "_") + ".name").build());

        EnumChatFormatting colour;

        if (quality > 75)
        {
            colour = EnumChatFormatting.GREEN;
        }
        else if (quality > 50)
        {
            colour = EnumChatFormatting.YELLOW;
        }
        else if (quality > 25)
        {
            colour = EnumChatFormatting.GOLD;
        }
        else
        {
            colour = EnumChatFormatting.RED;
        }

        tooltip.add(colour + new AdvLang("lore.dna_quality.name").withProperty("quality", quality + "").build());
    }
}
