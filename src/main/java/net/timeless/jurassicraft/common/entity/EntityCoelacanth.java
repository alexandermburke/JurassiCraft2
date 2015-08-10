package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.ICarnivore;
import net.reuxertz.ecoapi.entity.IEntityAISwimmingCreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurSwimmingAggressive;

public class EntityCoelacanth extends EntityDinosaurSwimmingAggressive implements IEntityAISwimmingCreature, ICarnivore
{
    public EntityCoelacanth(World world)
    {
        super(world);
    }

    public void onUpdate()
    {
        super.onUpdate();
    }

}
