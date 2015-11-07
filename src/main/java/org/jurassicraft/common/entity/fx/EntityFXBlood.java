package org.jurassicraft.common.entity.fx;

import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityFXBlood extends EntityDiggingFX
{
    public EntityFXBlood(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, Blocks.redstone_block.getDefaultState());
        this.particleMaxAge += 550;
    }
}
