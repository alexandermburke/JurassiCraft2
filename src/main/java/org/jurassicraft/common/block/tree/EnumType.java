package org.jurassicraft.common.block.tree;

import net.minecraft.util.IStringSerializable;

public enum EnumType implements IStringSerializable
{
    GINKGO(0, "ginkgo"),
    CALAMITES(1, "calamites");
    private static final EnumType[] META_LOOKUP = new EnumType[values().length];
    private final int meta;
    private final String name;

    private EnumType(int meta, String name)
    {
        this.meta = meta;
        this.name = name;
    }

    public void setMetaLookup()
    {
        EnumType.META_LOOKUP[this.meta] = this;
    }

    public static EnumType[] getMetaLookup()
    {
        return META_LOOKUP;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    public String toString()
    {
        return this.name;
    }

    public String getName()
    {
        return this.name;
    }
}