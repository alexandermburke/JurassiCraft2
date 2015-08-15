package net.reuxertz.ecoai.ai.modules;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.reuxertz.ecoai.ai.AICore;
import net.reuxertz.ecoai.ai.AINavigate;
import net.reuxertz.ecoai.demand.IDemand;
import net.reuxertz.ecoapi.ecology.EcoFauna;
import net.reuxertz.ecoapi.entity.Target;
import net.reuxertz.ecoapi.item.BaseItem;
import net.reuxertz.ecoapi.util.EntityHelper;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

import java.util.List;

public class AIHunt extends AIModule
{
    public AIHunt(IDemand demand, AICore entity, AINavigate navigate, Target T)
    {
        super(demand, entity, navigate);

        this.workTarget = T;
    }

    public boolean evaluateTarget(Target t)
    {
        if (!t.enableEvaluateTarget)
            return true;

        if (t.pos != null)
            return Block.isEqualTo(t.world.getBlockState(t.pos).getBlock(), t.posBlockState.getBlock());

        if (t.entity != null && t.entity == this.getAgent())
            return false;

        if (t.entity != null && !t.entity.isDead && t.entityHeldItem == null)
            return true;

        return t.entity != null && !t.entity.isDead && t.entityHeldItem != null && BaseItem.itemsEqual(((EntityPlayer) t.entity).getHeldItem(), t.entityHeldItem);

    }

    public Target nextNavigatePosition()
    {
        List<Entity> distEnts = EntityHelper.getEntitiesWithinDistance(this.getAgent(), 10, 10);
        for (Entity distEnt : distEnts)
        {
            boolean strongerDino = false;

            if (distEnt instanceof EntityDinosaur && getAgent() instanceof EntityDinosaur)
            {
                EntityDinosaur agent = (EntityDinosaur) this.getAgent();

                strongerDino = agent.isStronger((EntityDinosaur) distEnt);
            }

            if (!strongerDino)
            {
                boolean seek = false, heldItem = false;
                Target t = null;
                //Is Entity Item
                if ((EntityItem.class.isInstance(distEnt) && (this.demand.isItemDemanded(((EntityItem) distEnt).getEntityItem()) != null) ||
                        this.demand.isEntityDemanded(distEnt) != null))
                    t = new Target(this.getAgent().worldObj, distEnt);
                //seek = true;

                if (EntityPlayer.class.isInstance(distEnt) && this.demand.isItemDemanded(((EntityPlayer) distEnt).getHeldItem()) != null)
                {
                    //seek = true;
                    heldItem = true;
                    t = new Target(this.getAgent().worldObj, distEnt, ((EntityPlayer) distEnt).getHeldItem());
                }

                if (!seek)
                {
                    List<ItemStack> dropItemStacks = EcoFauna.getDropItemsByEntity(distEnt);
                    if (dropItemStacks != null && dropItemStacks.size() > 0 && this.demand.isItemDemanded(dropItemStacks.get(0)) != null)
                        t = new Target(this.getAgent().worldObj, distEnt);
                    //seek = true;
                }

                if (t != null && this.evaluateTarget(t))
                    return t;
            }
        }

        return null;
    }

    public boolean doWorkContinue()
    {
        if (this.workTarget.entity instanceof EntityCreature)
            this.workTarget.entity.attackEntityFrom(DamageSource.generic, 1.0f);

        if (this.workTarget.entity instanceof EntityItem)
        {
            EntityItem entityItem = (EntityItem) this.workTarget.entity;
            ItemStack r = this.agentAI.addToInventory(entityItem.getEntityItem());

            if (r == null || r.stackSize == 0)
                entityItem.setDead();

            if (r != null && r.stackSize != entityItem.getEntityItem().stackSize)
                entityItem.setEntityItemStack(r);

            return false;
        }

        return true;
    }
}
