package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.entity.IEntityAISwimmingCreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurSwimmingAggressive;

public class EntityDunkleosteus extends EntityDinosaurSwimmingAggressive implements IEntityAISwimmingCreature, ICarnivore
{
    public EntityDunkleosteus(World world)
    {
        super(world);
    }
}
