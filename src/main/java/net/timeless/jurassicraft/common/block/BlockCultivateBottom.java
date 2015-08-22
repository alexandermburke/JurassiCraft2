package net.timeless.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;

public class BlockCultivateBottom extends BlockCultivate
{
    public BlockCultivateBottom()
    {
        super("bottom");
        this.setCreativeTab(JCCreativeTabs.blocks);
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        BlockPos topBlock = pos.add(0, 1, 0);

        Block block = worldIn.getBlockState(topBlock).getBlock();

        if(block == Blocks.air)
        {
            worldIn.setBlockState(topBlock, JCBlockRegistry.cultivate_top.getDefaultState().withProperty(COLOR, state.getValue(COLOR)));
        }
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.setBlockState(pos.add(0, 1, 0), Blocks.air.getDefaultState());

        super.breakBlock(worldIn, pos, state);
    }
}
