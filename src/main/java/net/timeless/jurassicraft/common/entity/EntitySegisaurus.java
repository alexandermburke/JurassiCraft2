package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;

public class EntitySegisaurus extends EntityDinosaurAggressive implements IEntityAICreature, ICarnivore
{
    public EntitySegisaurus(World world)
    {
        super(world);
    }
}