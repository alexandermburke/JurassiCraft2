package net.timeless.unilib.common.structure.rules;

import net.minecraft.world.World;
import net.timeless.unilib.common.structure.BlockCoords;

import java.util.Random;

public class RandomRule extends FixedRule {

    private int max;
    private int min;

    public RandomRule(int min, int max) {
        this(min, max, false);
    }

    public RandomRule(int min, int max, boolean maxIncluded) {
        super(0);
        this.min = min;
        this.max = max;
        if(maxIncluded)
            this.max++;
    }

    @Override
    public void reset(World world, Random random, BlockCoords pos) {
        times = random.nextInt(max - min) + min;
    }

    public void init(World world, Random random, BlockCoords pos) {
        countdown = times;
    }
}
