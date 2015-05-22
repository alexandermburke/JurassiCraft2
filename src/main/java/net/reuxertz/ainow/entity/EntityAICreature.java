package net.reuxertz.ainow.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.*;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.world.World;
import net.reuxertz.ainow.entity.ai.AICore;
import net.reuxertz.ainow.entity.ai.AINavigate;

public class EntityAICreature extends EntityCreature
{
    //Static Functions
    public static void ConstructAI(EntityCreature entity)
    {
        //Clear
        entity.tasks.taskEntries.clear();

        //Base
        ((PathNavigateGround)entity.getNavigator()).func_179690_a(true);
        entity.tasks.addTask(1, new EntityAISwimming(entity));
        entity.tasks.addTask(2, new EntityAIAttackOnCollide(entity, 1.0F * 1, false));
        //entity.tasks.addTask(3, new EntityAIMoveTowardsRestriction(entity, 1));
        //entity.tasks.addTask(4, new EntityAIWatchClosest(entity, EntityPlayer.class, 6.0F));
        //entity.tasks.addTask(5, new EntityAILookIdle(entity));

        //AI
        AINavigate aiNav = new AINavigate(entity, 0.3F);
        AICore ai = new AICore(entity);

        //Add AI
        entity.tasks.addTask(10, ai);
        ai.AddTask(11, aiNav);

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
            EntityAICreature.ConstructAI(this);
    }
}
