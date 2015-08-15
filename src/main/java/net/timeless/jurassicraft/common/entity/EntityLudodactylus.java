package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.entity.IEntityAIFlyingCreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurFlyingAggressive;

public class EntityLudodactylus extends EntityDinosaurFlyingAggressive implements IEntityAIFlyingCreature, ICarnivore
{
    public EntityLudodactylus(World world)
    {
        super(world);
    }
}
