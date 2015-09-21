package org.jurassicraft.common.plant;

import net.minecraft.block.Block;
import org.jurassicraft.common.block.JCBlockRegistry;

public class PlantZamites extends Plant
{
    @Override
    public EnumPlantType getPlantType()
    {
        return EnumPlantType.FERN;
    }

    @Override
    public String getName()
    {
        return "Cycad Zamites";
    }

    @Override
    public Block getBlock()
    {
        return JCBlockRegistry.cycad_zamites;
    }
}
