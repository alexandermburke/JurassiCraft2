package net.timeless.jurassicraft.common.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

public class ItemDnaContainer extends Item
{
    public int getDNAQuality(EntityPlayer player, ItemStack stack)
    {
        int quality = player.capabilities.isCreativeMode ? 100 : 0;

        NBTTagCompound nbt = stack.getTagCompound();

        if (nbt == null)
        {
            nbt = new NBTTagCompound();
            nbt.setInteger("DNAQuality", quality);
        }
        else
        {
            quality = nbt.getInteger("DNAQuality");
        }

        stack.setTagCompound(nbt);

        return quality;
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

        lore.add(colour + "DNA Quality: " + quality + "%"); //TODO translation
    }
}
