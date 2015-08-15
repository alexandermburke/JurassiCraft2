package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.entity.IEntityAIFlyingCreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurFlyingAggressive;

public class EntityDimorphodon extends EntityDinosaurFlyingAggressive implements IEntityAIFlyingCreature, ICarnivore
{
    public EntityDimorphodon(World world)
    {
        super(world);
    }
}
