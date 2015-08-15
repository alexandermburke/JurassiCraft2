package net.timeless.jurassicraft.common.entity;

import net.minecraft.world.World;
import net.reuxertz.ecoapi.ecology.role.IOmnivore;
import net.reuxertz.ecoapi.entity.IEntityAICreature;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.jurassicraft.common.entity.ai.animations.JCAutoAnimSoundBase;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.unilib.common.animation.ChainBuffer;

public class EntitySpinosaurus extends EntityDinosaurAggressive implements IEntityAICreature, IOmnivore
{
    private static final String[] hurtSounds = new String[] { "spinosaurus_hurt_1" };
    private static final String[] livingSounds = new String[] { "spinosaurus_living_1", "spinosaurus_living_2", "spinosaurus_living_3", "spinosaurus_living_4" };
    private static final String[] deathSounds = new String[] { "spinosaurus_death_1", "spinosaurus_death_2" };
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    public EntitySpinosaurus(World world)
    {
        super(world);

        tasks.addTask(2, new JCAutoAnimSoundBase(this, 75, 1, "")); //Roar
    }

    public void onUpdate()
    {
        super.onUpdate();
        if (this.getAnimID() == 0)
            AnimationAPI.sendAnimPacket(this, 1);
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 10, 4.0F, this);
    }

    public String getLivingSound()
    {
        return randomSound(livingSounds);
    }

    public String getHurtSound()
    {
        return randomSound(hurtSounds);
    }

    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }
}