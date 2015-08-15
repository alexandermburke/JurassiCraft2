package net.timeless.jurassicraft.common.world.jurdstrees.algorythms;

import net.minecraft.block.Block;

public class Feature {

    private int FeatureCode;
    private Block block;
    private Shape shape;
    private FeatureType type;

    public Feature(int code, FeatureType type, Shape shape, Block block) {

        this.shape = shape;
        this.block = block;
        this.type = type;
        FeatureCode = code;
    }

    public FeatureType getType() {

        return type;

    }

    public enum FeatureType {

        Trunk, Branch, Fruit, leaves, wood, TrunkLeaves;

    }

}
