package net.timeless.jurassicraft.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.timeless.jurassicraft.common.genetics.GeneticsContainer;
import net.timeless.jurassicraft.common.genetics.GeneticsHelper;
import net.timeless.jurassicraft.common.lang.AdvLang;

import java.util.List;

public class ItemDnaContainer extends Item
{
    public int getContainerId(ItemStack stack)
    {
        return 0;
    }

    public int getDNAQuality(EntityPlayer player, ItemStack stack)
    {
        int quality = player.capabilities.isCreativeMode ? 100 : 0;

        NBTTagCompound nbt = stack.getTagCompound();

        if (nbt == null)
        {
            nbt = new NBTTagCompound();
        }

        if (nbt.hasKey("DNAQuality"))
        {
            quality = nbt.getInteger("DNAQuality");
        }
        else
        {
            nbt.setInteger("DNAQuality", quality);
        }

        stack.setTagCompound(nbt);

        return quality;
    }

    public GeneticsContainer getGeneticCode(EntityPlayer player, ItemStack stack)
    {
        int quality = getDNAQuality(player, stack);

        NBTTagCompound nbt = stack.getTagCompound();

        GeneticsContainer genetics = GeneticsHelper.randomGenetics(player.worldObj.rand, getContainerId(stack), quality);

        if (nbt == null)
        {
            nbt = new NBTTagCompound();
        }

        if (nbt.hasKey("Genetics"))
        {
            genetics = new GeneticsContainer(nbt.getString("Genetics"));
        }
        else
        {
            nbt.setString("Genetics", genetics.toString());
        }

        stack.setTagCompound(nbt);

        return genetics;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List lore, boolean advanced)
    {
        int quality = getDNAQuality(player, stack);

        EnumChatFormatting colour;

        if (quality > 75)
            colour = EnumChatFormatting.GREEN;
        else if (quality > 50)
            colour = EnumChatFormatting.YELLOW;
        else if (quality > 25)
            colour = EnumChatFormatting.GOLD;
        else
            colour = EnumChatFormatting.RED;

        lore.add(colour + new AdvLang("lore.dna_quality.name").withProperty("quality", quality + "").build());
        lore.add(EnumChatFormatting.BLUE + new AdvLang("lore.genetic_code.name").withProperty("code", getGeneticCode(player, stack).toString()).build());
    }
}
