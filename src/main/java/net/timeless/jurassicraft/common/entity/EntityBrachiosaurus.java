package net.timeless.jurassicraft.common.entity;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurDefensiveHerbivore;

public class EntityBrachiosaurus extends EntityDinosaurDefensiveHerbivore
{
    private static final String[] livingSounds = new String[] { "brachiosaurus_living_1", "brachiosaurus_living_2", "brachiosaurus_living_3", "brachiosaurus_living_4" };

    public EntityBrachiosaurus(World world)
    {
        super(world);
        this.defendFromAttacker(EntityTyrannosaurusRex.class, 1);
        this.defendFromAttacker(EntityPlayer.class, 3);
        this.defendFromAttacker(EntityIndominusRex.class, 1);

        this.tasks.addTask(6, new EntityAIWander(this, dinosaur.getAdultSpeed()));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    public String getLivingSound()
    {
        return randomSound(livingSounds);
    }
}