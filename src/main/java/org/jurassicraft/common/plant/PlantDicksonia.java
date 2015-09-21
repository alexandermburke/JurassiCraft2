package org.jurassicraft.common.plant;

import net.minecraft.block.Block;
import org.jurassicraft.common.block.JCBlockRegistry;

public class PlantDicksonia extends Plant
{
    @Override
    public EnumPlantType getPlantType()
    {
        return EnumPlantType.FERN;
    }

    @Override
    public String getName()
    {
        return "Dicksonia";
    }

    @Override
    public Block getBlock()
    {
        return JCBlockRegistry.dicksonia;
    }
}
