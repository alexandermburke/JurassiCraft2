package net.timeless.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;

import java.util.Random;

public class BlockGypsumStone extends Block
{
    public BlockGypsumStone()
    {
        super(Material.rock);
        this.setUnlocalizedName("gypsum_stone");
        this.setCreativeTab(JCCreativeTabs.blocks);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(JCBlockRegistry.gypsum_cobblestone);
    }
}
