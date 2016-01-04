package org.jurassicraft.common.world.jurdstrees.algorythms;

import net.minecraft.block.Block;

public class Feature
{
    private final int FeatureCode;
    private final Block block;
    private final Shape shape;
    private final FeatureType type;

    public Feature(int code, FeatureType type, Shape shape, Block block)
    {

        this.shape = shape;
        this.block = block;
        this.type = type;
        FeatureCode = code;
    }

    public FeatureType getType()
    {
        return type;
    }

    public enum FeatureType
    {
        Trunk, Branch, Fruit, leaves, wood, TrunkLeaves;
    }
}
