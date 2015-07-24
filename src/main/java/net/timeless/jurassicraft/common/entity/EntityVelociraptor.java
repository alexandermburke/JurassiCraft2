package net.timeless.jurassicraft.common.entity;

import net.ilexiconn.llibrary.client.model.modelbase.ControlledAnimation;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import net.timeless.jurassicraft.client.model.animation.ai.JCAutoAnimBase;
import net.timeless.jurassicraft.client.model.animation.ai.JCNonAutoAnimBase;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaurAggressive;
import net.timeless.jurassicraft.common.entity.base.buffer.ChainBuffer;

public class EntityVelociraptor extends EntityDinosaurAggressive
{
    public ChainBuffer tailBuffer = new ChainBuffer(6);

    private static final String[] hurtSounds = new String[] { "velociraptor_hurt_1" };
    private static final String[] livingSounds = new String[] { "velociraptor_living_1", "velociraptor_living_2", "velociraptor_living_3" };
    private static final String[] deathSounds = new String[] { "velociraptor_death_1" };
    private static final String[] callSounds = new String[] { "velociraptor_call_1", "velociraptor_call_2", "velociraptor_call_3" };
    private static final String[] barkSounds = new String[] { "velociraptor_bark_1", "velociraptor_bark_2", "velociraptor_bark_3" };
    private static final String[] hissSounds = new String[] { "velociraptor_hiss_1", "velociraptor_hiss_2", "velociraptor_hiss_3" };

    public ControlledAnimation dontLean = new ControlledAnimation(5);

    public EntityVelociraptor(World world)
    {
        super(world);

        //Attacks smaller dinosaurs(And pigs, everyone hates pigs!)
        this.attackCreature(EntityPig.class, 2);
        this.attackCreature(EntityPlayer.class, 0);
        this.attackCreature(EntityCompsognathus.class, 1);
        this.attackCreature(EntityGallimimus.class, 3);
        this.attackCreature(EntitySegisaurus.class, 1);
        this.attackCreature(EntityAchillobator.class, 0);

        this.defendFromAttacker(EntityPlayer.class, 0);
        this.defendFromAttacker(EntityTyrannosaurusRex.class, 0);
        this.defendFromAttacker(EntityIndominusRex.class, 0);
        this.defendFromAttacker(EntitySpinosaurus.class, 0);
        this.defendFromAttacker(EntityAchillobator.class, 0);

        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));

        this.tasks.addTask(6, new EntityAIWander(this, dinosaur.getAdultSpeed()));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        tasks.addTask(2, new JCAutoAnimBase(this, 25, 1)); //Call
//        tasks.addTask(2, new JCAutoAnimBase(this, 25, 2)); //Attack
//        tasks.addTask(2, new JCAutoAnimBase(this, 25, 3)); //Die
//        tasks.addTask(2, new JCAutoAnimBase(this, 6, 4)); //Hurt
        tasks.addTask(2, new JCNonAutoAnimBase(this, 25, 10, 100)); //Head twitch right
        tasks.addTask(2, new JCNonAutoAnimBase(this, 25, 11, 100)); //Head twitch left
        tasks.addTask(2, new JCNonAutoAnimBase(this, 45, 12, 150)); //Sniff
    }

    public String getLivingSound()
    {
        if (getAnimID() == 0) {
            AnimationAPI.sendAnimPacket(this, 1);
            return randomSound(livingSounds);
        }
        return null;
    }

    public String getHurtSound()
    {
        return randomSound(hurtSounds);
    }

    public String getDeathSound()
    {
        return randomSound(deathSounds);
    }

    public void onUpdate()
    {
        this.tailBuffer.calculateChainSwingBuffer(68.0F, 5, 4.0F, this);
        super.onUpdate();

        if (getAnimID() == 12 || getAnimID() == 1) dontLean.decreaseTimer();
        else dontLean.increaseTimer();
    }
}
