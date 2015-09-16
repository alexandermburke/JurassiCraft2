package net.reuxertz.ecoapi.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BaseBlock extends Block
{
    public float weightPercentDrag()
    {
        return 0.0f;
    }

    public BaseBlock(Material blockMaterial)
    {
        super(blockMaterial);
    }
}


