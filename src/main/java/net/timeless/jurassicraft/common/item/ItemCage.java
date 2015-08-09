package net.timeless.jurassicraft.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;

public class ItemCage extends ItemBlock
{
    public ItemCage(Block block)
    {
        super(block);
        this.setUnlocalizedName("cage_small"); //TODO
        this.setCreativeTab(JCCreativeTabs.blocks);
    }
}
