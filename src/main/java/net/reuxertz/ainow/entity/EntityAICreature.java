package net.reuxertz.ainow.entity;

import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.world.World;
import net.reuxertz.ainow.entity.ai.AICore;
import net.reuxertz.ainow.entity.ai.AINavigate;
import net.reuxertz.ainow.utils.IDObj;

public abstract class EntityAICreature
        extends EntityCreature
{
    public static void ConstructAIEntity(EntityCreature entity)
    {
        IDObj.ValidateIDObj(entity, true);

        entity.tasks.taskEntries.clear();


        ((PathNavigateGround)entity.getNavigator()).func_179690_a(true);
        entity.tasks.addTask(1, new EntityAISwimming(entity));
        entity.tasks.addTask(2, new EntityAIAttackOnCollide(entity, 1.0D, false));

        AINavigate aiNav = new AINavigate(entity, 0.3);
        AICore ai = new AICore(entity);


        entity.tasks.addTask(10, ai);
        ai.AddTask(11, aiNav);


        ai.SetEnabled(true);
    }

    public EntityAICreature(World w)
    {
        this(w, true);
    }

    public EntityAICreature(World w, boolean initialize)
    {
        super(w);
        if (initialize) {
            ConstructAIEntity(this);
        }
    }
}
