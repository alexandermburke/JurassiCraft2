package net.timeless.jurassicraft.common.entity;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.IHerbivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurProvokableHerbivore;

public class EntityTriceratops extends EntityDinosaurProvokableHerbivore implements IEntityAICreature, IHerbivore
{
    public EntityTriceratops(World world)
    {
        super(world);
        this.tasks.addTask(6, new EntityAIWander(this, dinosaur.getAdultSpeed()));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        this.defendFromAttacker(EntityPlayer.class, 2);
        this.defendFromAttacker(EntityTyrannosaurusRex.class, 1);
    }
}