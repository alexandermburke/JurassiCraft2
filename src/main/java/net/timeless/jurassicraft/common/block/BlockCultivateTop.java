package net.timeless.jurassicraft.common.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockCultivateTop extends BlockCultivate
{
    public BlockCultivateTop()
    {
        super("top");
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        BlockPos bottomBlock = pos.add(0, -1, 0);

        if(worldIn.getBlockState(bottomBlock).getBlock() != JCBlockRegistry.cultivate_bottom)
        {
            worldIn.setBlockState(bottomBlock, JCBlockRegistry.cultivate_bottom.getDefaultState().withProperty(COLOR, state.getValue(COLOR)));
        }
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.setBlockState(pos.add(0, -1, 0), Blocks.air.getDefaultState());

        super.breakBlock(worldIn, pos, state);
    }
}
