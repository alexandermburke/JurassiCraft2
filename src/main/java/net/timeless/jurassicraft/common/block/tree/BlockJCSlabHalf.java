package net.timeless.jurassicraft.common.block.tree;

import net.minecraft.block.state.IBlockState;

public class BlockJCSlabHalf extends BlockJCSlab
{
    public BlockJCSlabHalf(String name, IBlockState state)
    {
        super(state);
        this.setUnlocalizedName(name + "_slab");
    }

    public boolean isDouble()
    {
        return false;
    }
}