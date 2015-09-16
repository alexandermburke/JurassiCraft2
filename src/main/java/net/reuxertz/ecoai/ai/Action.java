package net.reuxertz.ecoai.ai;

import net.minecraft.entity.EntityCreature;
import net.reuxertz.ecoai.demand.IDemand;

public class Action
{
    //Fields
    protected Target target;
    protected EntityCreature entity;
    protected AICore aiCore;
    protected AIModule aiModule;
    protected IDemand demand;

    //Modifiers
    public Target getTarget()
    {
        return this.target;
    }

    //Constructor
    public Action(EntityCreature entity, AICore ai, AIModule module, IDemand demand, Target t)
    {
        this.entity = entity;
        this.aiCore = ai;
        this.aiModule = module;
        this.demand = demand;
        this.target = t;
    }

}
