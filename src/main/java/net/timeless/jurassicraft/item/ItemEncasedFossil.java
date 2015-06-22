package net.timeless.jurassicraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.timeless.jurassicraft.block.BlockEncasedFossil;

public class ItemEncasedFossil extends ItemBlock
{
    public ItemEncasedFossil(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("item_encased_fossil");
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
