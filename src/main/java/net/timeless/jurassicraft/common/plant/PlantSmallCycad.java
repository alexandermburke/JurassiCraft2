package net.timeless.jurassicraft.common.plant;

import net.minecraft.block.Block;
import net.timeless.jurassicraft.common.block.JCBlockRegistry;

public class PlantSmallCycad extends Plant
{
    @Override
    public EnumPlantType getPlantType()
    {
        return EnumPlantType.FERN;
    }

    @Override
    public String getName()
    {
        return "Small Cycad";
    }

    @Override
    public Block getBlock()
    {
        return JCBlockRegistry.small_cycad;
    }
}
