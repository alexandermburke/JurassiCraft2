package net.reuxertz.ecoai.ai;

import net.minecraft.entity.EntityCreature;
import net.reuxertz.ecoai.demand.IDemand;
import net.reuxertz.ecoapi.entity.Target;

public abstract class Action
{
    //Fields
    protected Target target;

    //Constructor
    public Action(EntityCreature entity, IDemand demand)
    {

    }

    //Modifiers
    public Target getTarget()
    {
        return this.target;
    }

}
