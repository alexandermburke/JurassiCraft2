package net.timeless.jurassicraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.timeless.jurassicraft.block.BlockEncasedFossil;

public class ItemEncasedFossil extends ItemBlock
{
    public ItemEncasedFossil(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("encased_fossil");
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        return StatCollector.translateToLocal("period." + BlockEncasedFossil.EnumTimePeriod.byMetadata(stack.getMetadata()).getName() + ".name") + " " + StatCollector.translateToLocal("tile.encased_fossil.name");
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
