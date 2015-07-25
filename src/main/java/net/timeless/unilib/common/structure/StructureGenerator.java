package net.timeless.unilib.common.structure;

import com.google.common.collect.Lists;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public abstract class StructureGenerator {

    public static final List<EnumFacing> clockwiseFacings = Lists.newArrayList(EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST);

    public static EnumFacing getNextClockwise(EnumFacing facing) {
        int index = clockwiseFacings.indexOf(facing);
        if(index < 0) {
            throw new IllegalArgumentException();
        }
        index++;
        index %= clockwiseFacings.size();
        return clockwiseFacings.get(index);
    }

    public abstract void generate(World world, int x, int y, int z, Random random);

    public abstract StructureGenerator rotateClockwise(EnumRotAngle angle);

    public abstract StructureGenerator rotateTowards(EnumFacing facing);
}
