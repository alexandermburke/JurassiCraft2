package net.timeless.unilib.common.structure.rules;

import net.minecraft.world.World;
import net.timeless.unilib.common.structure.BlockCoords;

import java.util.Random;

public class FixedRule extends RepeatRule {
    protected int times;
    protected int countdown;

    public FixedRule(int times) {
        super();
        this.times = times;
        this.countdown = times;
    }

    public boolean continueRepeating(World world, Random rand, BlockCoords position) {
        return countdown > 0;
    }

    public void repeat(World world, Random rand, BlockCoords position) {
        countdown--;
        position.x += getSpacingX();
        position.y += getSpacingY();
        position.z += getSpacingZ();
    }

    @Override
    public void reset(World world, Random random, BlockCoords pos) {
        countdown = times;
    }

}
