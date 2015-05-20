package net.reuxertz.ainow.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.world.World;
import net.reuxertz.ainow.entity.ai.AICore;
import net.reuxertz.ainow.entity.ai.AINavigate;

public class EntityAICreature extends EntityCreature
{
    //Static Functions
    public static void InitializeAI(EntityCreature entity)
    {
        //Clear
        entity.tasks.taskEntries.clear();

        //Base
        ((PathNavigateGround)entity.getNavigator()).func_179690_a(true);
        entity.tasks.addTask(1, new EntityAISwimming(entity));
        entity.tasks.addTask(2, new EntityAIAttackOnCollide(entity, 1.0F * 1, false));
        entity.tasks.addTask(3, new EntityAIMoveTowardsRestriction(entity, 1));
        entity.tasks.addTask(4, new EntityAIWatchClosest(entity, EntityPlayer.class, 6.0F));
        entity.tasks.addTask(5, new EntityAILookIdle(entity));

        //AI
        AICore ai = new AICore(entity);
        entity.tasks.addTask(10, ai);
        entity.tasks.addTask(11, new AINavigate(entity, 1.0F));

        //Init
        ai.SetEnabled(true);
    }

    //Constructor
    public EntityAICreature(World w)
    {
        this(w, true);
    }
    public EntityAICreature(World w, boolean initialize)
    {
        super(w);

        if (initialize)
            EntityAICreature.InitializeAI(this);
    }
}
