package org.jurassicraft.common.world.jurdstrees.trees;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.jurassicraft.common.world.jurdstrees.algorythms.TreeGenerator;

import java.util.Random;

/**
 * Created by Jordi on 12/08/2015.
 */
public class WorldGenCalamites extends WorldGenAbstractTree
{

    private int code;

    public WorldGenCalamites(int code)
    {

        super(true);
        this.code = code;

    }

    @Override
    public boolean generate(World worldIn, Random random, BlockPos pos)
    {

        TreeGenerator generator = new TreeGenerator(code, worldIn, pos);
        generator.placeTree();

        return true;

    }
}
