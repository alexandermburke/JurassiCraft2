package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.IHerbivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityMicroceratus extends EntityDinosaurDefensiveHerbivore implements IEntityAICreature, IHerbivore
{
    public EntityMicroceratus(World world)
    {
        super(world);
    }
}