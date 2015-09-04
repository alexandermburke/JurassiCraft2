package net.timeless.jurassicraft.common.block.tree;

import net.minecraft.block.state.IBlockState;

public class BlockJCSlabDouble extends BlockJCSlab
{
    public BlockJCSlabDouble(String name, IBlockState state)
    {
        super(state);
        this.setUnlocalizedName(name + "_double_slab");
    }

    public boolean isDouble()
    {
        return true;
    }
}