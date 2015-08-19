package net.timeless.jurassicraft.common.plant;

import net.minecraft.block.Block;

public class PlantSmallRoyalFern extends Plant
{
    @Override
    public EnumPlantType getPlantType()
    {
        return EnumPlantType.FERN;
    }

    @Override
    public String getName()
    {
        return "Small Royal Fern";
    }

    @Override
    public Block getBlock()
    {
        return null;
    }
}
