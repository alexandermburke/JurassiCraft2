package net.ilexiconn.jurassicraft.item;

import net.ilexiconn.jurassicraft.block.BlockEncasedFossil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class ItemEncasedFossil extends ItemBlock
{
    public ItemEncasedFossil(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("item_encased_fossil");
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
    {
        BlockEncasedFossil.EnumTimePeriod timePeriod = BlockEncasedFossil.EnumTimePeriod.byMetadata(stack.getMetadata());
        tooltip.add("Test");
        if (timePeriod != null)
            tooltip.add("period." + timePeriod.getNameForDisplay() + ".name");
    }

    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        BlockEncasedFossil.EnumTimePeriod timePeriod = BlockEncasedFossil.EnumTimePeriod.byMetadata(stack.getMetadata());
        return super.getUnlocalizedName() + "." + timePeriod.getName();
    }
}
