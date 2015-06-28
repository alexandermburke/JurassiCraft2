package net.timeless.jurassicraft.period;

import net.minecraft.util.IStringSerializable;

/**
 * PropertyEnum used create fossil blocks from different periods in time.
 */
public enum EnumTimePeriod implements IStringSerializable
{
    QUATERNARY(0, "quaternary", 2.588F, 0.0F, false), NEOGENE(1, "neogene", 23.03F, 2.589F, false), PALEOGENE(2, "paleogene", 66.0F, 23.04F, false), CRETACEOUS(3, "cretaceous", 145.5F, 66.1F, true), JURASSIC(4, "jurassic", 201.3F, 145.6F, false), TRIASSIC(5, "triassic", 252.17F, 201.4F, false), PERMIAN(6, "permian", 298.9F, 252.18F, false), CARBONIFEROUS(7, "carboniferous", 358.9F, 299.0F, false), DEVONIAN(8, "devonian", 419.2F, 359.0F, false), SILURIAN(9, "silurian", 443.4F, 419.3F, false), ORDOVICIAN(10, "ordovician", 485.4F, 443.5F, false), CAMBRIAN(11, "cambrian", 541.0F, 485.5F, false);

    /**
     * Returns the metadata of this time period.
     */
    public int getMetadata()
    {
        return this.meta;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    /**
     * Returns the time period from its metadata.
     */
    public static EnumTimePeriod byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
            meta = 0;

        return META_LOOKUP[meta];
    }

    /**
     * Returns the name of this time period.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns if this time period should be implemented.
     */
    public float getStartTime()
    {
        return this.startTime;
    }

    /**
     * Returns if this time period should be implemented.
     */
    public float getEndTime()
    {
        return this.endTime;
    }

    /**
     * Returns the tooltip of this itemBlock.
     */
    public String getNameForDisplay()
    {
        return this.name;
    }

    /**
     * Returns if this time period should be implemented.
     */
    public boolean shouldBeImplement()
    {
        return this.shouldImplement;
    }

    /**
     * Name of this period.
     */
    private final String name;
    /**
     * ID of this period.
     */
    private final int meta;
    /**
     * Start time of this period.
     */
    private final float startTime;
    /**
     * Final time of this period.
     */
    private final float endTime;
    /**
     * Tells if this period should be used.
     */
    private final boolean shouldImplement;
    /**
     * META_LOOKUP array.
     */
    private static final EnumTimePeriod[] META_LOOKUP = new EnumTimePeriod[values().length];

    EnumTimePeriod(int meta, String name, float startTime, float endTime, boolean shouldImplement)
    {
        this.meta = meta;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shouldImplement = shouldImplement;
    }

    static
    {
        for (EnumTimePeriod timePeriod : values())
        {
            META_LOOKUP[timePeriod.getMetadata()] = timePeriod;
        }
    }
}