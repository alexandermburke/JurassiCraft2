package org.jurassicraft.common.block.tree;

import net.minecraft.block.state.IBlockState;
import org.jurassicraft.common.creativetab.JCCreativeTabs;

public class BlockJCSlabHalf extends BlockJCSlab
{
    public BlockJCSlabHalf(String name, IBlockState state)
    {
        super(state);
        this.setUnlocalizedName(name + "_slab");
        this.setCreativeTab(JCCreativeTabs.plants);
    }

    public boolean isDouble()
    {
        return false;
    }
}