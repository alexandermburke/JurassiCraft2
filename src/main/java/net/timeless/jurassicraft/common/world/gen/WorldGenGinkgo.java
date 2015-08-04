package net.timeless.jurassicraft.common.world.gen;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.timeless.jurassicraft.common.block.JCBlockRegistry;

import java.util.Random;

public class WorldGenGinkgo extends WorldGenAbstractTree
{
    public WorldGenGinkgo()
    {
        super(true);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int treeHeight = rand.nextInt(3) + 7;

        for (int i = 0; i < treeHeight; i++)
        {
            world.setBlockState(pos.add(0, i, 0), JCBlockRegistry.woods[0].getDefaultState());
        }

        int sideHeight = treeHeight - 3;

        world.setBlockState(pos.add(1, sideHeight, 0), JCBlockRegistry.woods[0].getDefaultState());
        world.setBlockState(pos.add(0, sideHeight, 1), JCBlockRegistry.woods[0].getDefaultState());
        world.setBlockState(pos.add(-1, sideHeight, 0), JCBlockRegistry.woods[0].getDefaultState());
        world.setBlockState(pos.add(0, sideHeight, -1), JCBlockRegistry.woods[0].getDefaultState());

        world.setBlockState(pos.add(2, sideHeight + 1, 0), JCBlockRegistry.woods[0].getDefaultState());
        world.setBlockState(pos.add(0, sideHeight + 1, 2), JCBlockRegistry.woods[0].getDefaultState());
        world.setBlockState(pos.add(-2, sideHeight + 1, 0), JCBlockRegistry.woods[0].getDefaultState());
        world.setBlockState(pos.add(0, sideHeight + 1, -2), JCBlockRegistry.woods[0].getDefaultState());

        return true;
    }
}
