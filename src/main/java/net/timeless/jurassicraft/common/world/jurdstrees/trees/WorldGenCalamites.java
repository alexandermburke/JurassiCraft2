package net.timeless.jurassicraft.common.world.jurdstrees.trees;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

/**
 * Created by Jordi on 12/08/2015.
 */
public class WorldGenCalamites extends WorldGenAbstractTree {


    public WorldGenCalamites() {
        super(true);
    }

    @Override
    public boolean generate(World worldIn, Random random, BlockPos pos) {

        return false;

    }
}
