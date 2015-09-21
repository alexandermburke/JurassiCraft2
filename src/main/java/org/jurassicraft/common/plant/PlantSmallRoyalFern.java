package org.jurassicraft.common.plant;

import net.minecraft.block.Block;
import org.jurassicraft.common.block.JCBlockRegistry;

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
        return JCBlockRegistry.small_royal_fern;
    }
}
