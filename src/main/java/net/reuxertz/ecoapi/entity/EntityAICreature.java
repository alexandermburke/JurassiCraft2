package net.reuxertz.ecoapi.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.world.World;
import net.reuxertz.ecoai.ai.AICore;
import net.reuxertz.ecoai.ai.AIMetabolism;
import net.reuxertz.ecoai.ai.AINavigate;

public class EntityAICreature extends EntityCreature implements IEntityAICreature
{
    public boolean canBreatheUnderwater()
    {
        return IEntityAIAquaticCreature.class.isInstance(this);
    }

    public void setNavigator(PathNavigate pn)
    {
        this.navigator = pn;
    }

    public EntityAICreature(World w)
    {
        this(w, true);
    }

    public EntityAICreature(World w, boolean initialize)
    {
        super(w);
        if (initialize)
        {
            try
            {
                ConstructAIEntity(this);
            }
            catch (Exception ex)
            {

            }
        }
    }

    public static void ConstructAIEntity(EntityCreature entity) throws SecurityException,
            NoSuchFieldException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException
    {
        entity.tasks.taskEntries.clear();
        entity.targetTasks.taskEntries.clear();

        if (IEntityAICreature.class.isInstance(entity))
        {
            if (IEntityAIFlyingCreature.class.isInstance(entity))
                ((PathNavigateGround) entity.getNavigator()).func_179690_a(true);
            else if (IEntityAISwimmingCreature.class.isInstance(entity))
            {
                //((PathNavigateGround) entity.getNavigator()).func_179690_a(false);
                ((IEntityAICreature) entity).setNavigator(new PathNavigateSwimmer(entity, entity.worldObj));
            }
            else
                ((PathNavigateGround) entity.getNavigator()).func_179690_a(true);
        }
        else
            ((PathNavigateGround) entity.getNavigator()).func_179690_a(true);
        //agentAI.tasks.addTask(1, new EntityAISwimming(agentAI));


        //agentAI.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.3f);
        entity.getNavigator().setSpeed(1);
        AIMetabolism aiMet = new AIMetabolism(entity);
        AINavigate aiNav = new AINavigate(entity, .666);
        AICore ai = new AICore(entity);

        entity.tasks.addTask(1, aiMet);
        entity.tasks.addTask(2, ai);
        entity.tasks.addTask(3, aiNav);

        ai.setEnabled(true);
    }

}
