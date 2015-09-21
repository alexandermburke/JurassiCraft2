package org.jurassicraft.common.plant;

import net.minecraft.block.Block;
import org.jurassicraft.common.block.JCBlockRegistry;

public class PlantCryPansy extends Plant
{
    @Override
    public EnumPlantType getPlantType()
    {
        return EnumPlantType.FLOWER;
    }

    @Override
    public String getName()
    {
        return "Cry Pansy";
    }

    @Override
    public Block getBlock()
    {
        return JCBlockRegistry.cry_pansy;
    }
}
