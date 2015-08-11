package net.timeless.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;

import java.util.Random;

public class BlockBasic extends Block
{
    private Item drop;

    public BlockBasic(Material material, String name)
    {
        super(material);
        this.setUnlocalizedName(name.toLowerCase().replaceAll(" ", "_"));
        this.setCreativeTab(JCCreativeTabs.blocks);
    }

    public BlockBasic setDrop(Item item)
    {
        this.drop = item;

        return this;
    }

    public BlockBasic setDrop(Block block)
    {
        return setDrop(Item.getItemFromBlock(block));
    }


    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return drop;
    }
}
