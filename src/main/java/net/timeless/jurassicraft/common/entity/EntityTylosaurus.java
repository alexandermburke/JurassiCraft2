package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.ecology.role.IHerbivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.reuxertz.ecoapi.entity.IEntityAISwimmingCreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurSwimmingAggressive;

public class EntityTylosaurus extends EntityDinosaurSwimmingAggressive implements IEntityAISwimmingCreature, ICarnivore
{
    public EntityTylosaurus(World world)
    {
        super(world);
    }
}
