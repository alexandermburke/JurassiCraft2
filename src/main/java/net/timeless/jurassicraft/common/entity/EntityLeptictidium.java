package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.IHerbivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityLeptictidium extends EntityDinosaurDefensiveHerbivore implements IEntityAICreature, IHerbivore
{
    public EntityLeptictidium(World world)
    {
        super(world);
    }
}