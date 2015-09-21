package org.jurassicraft.common.plant;

import net.minecraft.block.Block;
import org.jurassicraft.common.block.JCBlockRegistry;

public class PlantCalamites extends Plant
{
    @Override
    public EnumPlantType getPlantType()
    {
        return EnumPlantType.TREE;
    }

    @Override
    public String getName()
    {
        return "Calamites";
    }

    @Override
    public Block getBlock()
    {
        return JCBlockRegistry.saplings[1];
    }
}
