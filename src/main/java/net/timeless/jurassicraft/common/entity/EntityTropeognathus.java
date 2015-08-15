package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.entity.IEntityAIFlyingCreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurFlyingAggressive;

public class EntityTropeognathus extends EntityDinosaurFlyingAggressive implements IEntityAIFlyingCreature, ICarnivore
{
    public EntityTropeognathus(World world)
    {
        super(world);
    }
}
