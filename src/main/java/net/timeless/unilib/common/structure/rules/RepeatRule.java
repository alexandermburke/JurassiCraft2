package net.timeless.unilib.common.structure.rules;

import net.minecraft.world.World;
import net.timeless.unilib.common.structure.BlockCoords;

import java.util.Random;

public abstract class RepeatRule {
    private int spacingX;
    private int spacingY;
    private int spacingZ;

    public void setSpacing(int spacingX, int spacingY, int spacingZ) {
        this.spacingX = spacingX;
        this.spacingY = spacingY;
        this.spacingZ = spacingZ;
    }

    public int getSpacingX() {
        return spacingX;
    }

    public int getSpacingY() {
        return spacingY;
    }

    public int getSpacingZ() {
        return spacingZ;
    }

    public abstract boolean continueRepeating(World world, Random rand, BlockCoords position);

    public abstract void repeat(World world, Random rand, BlockCoords position);

    public abstract void reset(World world, Random random, BlockCoords pos);

    public void init(World world, Random random, BlockCoords pos) {
        reset(world, random, pos);
    }
}
