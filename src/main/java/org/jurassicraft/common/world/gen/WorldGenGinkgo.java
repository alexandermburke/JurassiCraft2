package org.jurassicraft.common.world.gen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.jurassicraft.common.block.JCBlockRegistry;

import java.util.Random;

class WorldGenGinkgo extends WorldGenAbstractTree
{
    public WorldGenGinkgo()
    {
        super(true);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int treeHeight = rand.nextInt(3) + 7;

        IBlockState wood = JCBlockRegistry.woods[0].getDefaultState();
        IBlockState leaves = JCBlockRegistry.leaves[0].getDefaultState();

        for (int i = 0; i < treeHeight; i++)
        {
            world.setBlockState(pos.add(0, i, 0), wood);
        }

        int sideHeight = treeHeight - 4;

        int facing = rand.nextInt(4);

        EnumFacing eFacing;

        if (facing == 0)
        {
            eFacing = EnumFacing.NORTH;
        }
        else if (facing == 1)
        {
            eFacing = EnumFacing.EAST;
        }
        else if (facing == 2)
        {
            eFacing = EnumFacing.SOUTH;
        }
        else
        {
            eFacing = EnumFacing.WEST;
        }

        world.setBlockState(pos.add(0, 1, 0).offset(eFacing), wood);

        world.setBlockState(pos.add(1, sideHeight, 0), wood);
        world.setBlockState(pos.add(0, sideHeight, 1), wood);
        world.setBlockState(pos.add(-1, sideHeight, 0), wood);
        world.setBlockState(pos.add(0, sideHeight, -1), wood);

        world.setBlockState(pos.add(2, sideHeight + 1, 0), wood);
        world.setBlockState(pos.add(0, sideHeight + 1, 2), wood);
        world.setBlockState(pos.add(-2, sideHeight + 1, 0), wood);
        world.setBlockState(pos.add(0, sideHeight + 1, -2), wood);

        world.setBlockState(pos.add(3, sideHeight + 1, 0), leaves);
        world.setBlockState(pos.add(0, sideHeight + 1, 3), leaves);
        world.setBlockState(pos.add(-3, sideHeight + 1, 0), leaves);
        world.setBlockState(pos.add(0, sideHeight + 1, -3), leaves);

        world.setBlockState(pos.add(3, sideHeight + 2, 0), leaves);
        world.setBlockState(pos.add(0, sideHeight + 2, 3), leaves);
        world.setBlockState(pos.add(-3, sideHeight + 2, 0), leaves);
        world.setBlockState(pos.add(0, sideHeight + 2, -3), leaves);

        world.setBlockState(pos.add(3, sideHeight + 3, 0), leaves);
        world.setBlockState(pos.add(0, sideHeight + 3, 3), leaves);
        world.setBlockState(pos.add(-3, sideHeight + 3, 0), leaves);
        world.setBlockState(pos.add(0, sideHeight + 3, -3), leaves);

        world.setBlockState(pos.add(4, sideHeight + 2, 0), leaves);
        world.setBlockState(pos.add(0, sideHeight + 2, 4), leaves);
        world.setBlockState(pos.add(-4, sideHeight + 2, 0), leaves);
        world.setBlockState(pos.add(0, sideHeight + 2, -4), leaves);

        world.setBlockState(pos.add(3, sideHeight + 2, 1), leaves);
        world.setBlockState(pos.add(1, sideHeight + 2, 3), leaves);
        world.setBlockState(pos.add(-3, sideHeight + 2, 1), leaves);
        world.setBlockState(pos.add(1, sideHeight + 2, -3), leaves);

        world.setBlockState(pos.add(3, sideHeight + 2, -1), leaves);
        world.setBlockState(pos.add(-1, sideHeight + 2, 3), leaves);
        world.setBlockState(pos.add(-3, sideHeight + 2, -1), leaves);
        world.setBlockState(pos.add(-1, sideHeight + 2, -3), leaves);

        world.setBlockState(pos.add(2, sideHeight + 2, 1), leaves);
        world.setBlockState(pos.add(1, sideHeight + 2, 2), leaves);
        world.setBlockState(pos.add(-2, sideHeight + 2, 1), leaves);
        world.setBlockState(pos.add(1, sideHeight + 2, -2), leaves);

        world.setBlockState(pos.add(2, sideHeight + 2, -1), leaves);
        world.setBlockState(pos.add(-1, sideHeight + 2, 2), leaves);
        world.setBlockState(pos.add(-2, sideHeight + 2, -1), leaves);
        world.setBlockState(pos.add(-1, sideHeight + 2, -2), leaves);

        world.setBlockState(pos.add(2, sideHeight + 2, 0), leaves);
        world.setBlockState(pos.add(0, sideHeight + 2, 2), leaves);
        world.setBlockState(pos.add(-2, sideHeight + 2, 0), leaves);
        world.setBlockState(pos.add(0, sideHeight + 2, -2), leaves);

        world.setBlockState(pos.add(0, treeHeight, 0), leaves);
        world.setBlockState(pos.add(0, treeHeight + 1, 0), leaves);

        world.setBlockState(pos.add(1, treeHeight, 0), leaves);
        world.setBlockState(pos.add(0, treeHeight, 1), leaves);
        world.setBlockState(pos.add(-1, treeHeight, 0), leaves);
        world.setBlockState(pos.add(0, treeHeight, -1), leaves);

        world.setBlockState(pos.add(1, treeHeight, 1), leaves);
        world.setBlockState(pos.add(-1, treeHeight, 1), leaves);
        world.setBlockState(pos.add(-1, treeHeight, -1), leaves);
        world.setBlockState(pos.add(1, treeHeight, -1), leaves);

        world.setBlockState(pos.add(2, treeHeight, 0), leaves);
        world.setBlockState(pos.add(0, treeHeight, 2), leaves);
        world.setBlockState(pos.add(-2, treeHeight, 0), leaves);
        world.setBlockState(pos.add(0, treeHeight, -2), leaves);

        world.setBlockState(pos.add(1, treeHeight - 1, 0), leaves);
        world.setBlockState(pos.add(0, treeHeight - 1, 1), leaves);
        world.setBlockState(pos.add(-1, treeHeight - 1, 0), leaves);
        world.setBlockState(pos.add(0, treeHeight - 1, -1), leaves);

        world.setBlockState(pos.add(1, treeHeight - 1, 1), leaves);
        world.setBlockState(pos.add(-1, treeHeight - 1, 1), leaves);
        world.setBlockState(pos.add(-1, treeHeight - 1, -1), leaves);
        world.setBlockState(pos.add(1, treeHeight - 1, -1), leaves);

        world.setBlockState(pos.add(2, treeHeight - 1, 0), leaves);
        world.setBlockState(pos.add(0, treeHeight - 1, 2), leaves);
        world.setBlockState(pos.add(-2, treeHeight - 1, 0), leaves);
        world.setBlockState(pos.add(0, treeHeight - 1, -2), leaves);

        world.setBlockState(pos.add(-2, treeHeight - 1, -1), leaves);
        world.setBlockState(pos.add(-1, treeHeight - 1, -2), leaves);
        world.setBlockState(pos.add(2, treeHeight - 1, 1), leaves);
        world.setBlockState(pos.add(1, treeHeight - 1, 2), leaves);

        world.setBlockState(pos.add(-2, treeHeight - 1, 1), leaves);
        world.setBlockState(pos.add(-1, treeHeight - 1, 2), leaves);
        world.setBlockState(pos.add(2, treeHeight - 1, -1), leaves);
        world.setBlockState(pos.add(1, treeHeight - 1, -2), leaves);

        return true;
    }
}
