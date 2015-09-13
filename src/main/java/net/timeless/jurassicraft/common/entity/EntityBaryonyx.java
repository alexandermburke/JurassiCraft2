package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.IHerbivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityBaryonyx extends EntityDinosaurDefensiveHerbivore implements IEntityAICreature, IHerbivore
{
    public EntityBaryonyx(World world)
    {
        super(world);
    }
}
