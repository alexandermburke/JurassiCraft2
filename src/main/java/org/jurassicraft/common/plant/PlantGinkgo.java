package org.jurassicraft.common.plant;

import net.minecraft.block.Block;
import org.jurassicraft.common.block.JCBlockRegistry;

public class PlantGinkgo extends Plant
{
    @Override
    public EnumPlantType getPlantType()
    {
        return EnumPlantType.TREE;
    }

    @Override
    public String getName()
    {
        return "Ginkgo";
    }

    @Override
    public Block getBlock()
    {
        return JCBlockRegistry.saplings[0];
    }
}
