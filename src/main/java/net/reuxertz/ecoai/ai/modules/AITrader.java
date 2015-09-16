package net.reuxertz.ecoai.ai.modules;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.reuxertz.ecoai.ai.AICore;
import net.reuxertz.ecoai.ai.AINavigate;
import net.reuxertz.ecoai.demand.IDemand;
import net.reuxertz.ecoapi.ecology.EcoFlora;
import net.reuxertz.ecoapi.entity.Target;
import net.reuxertz.ecoapi.item.BaseItem;
import net.reuxertz.ecoapi.util.BlockPosHelper;
import net.reuxertz.ecoapi.util.EntityHelper;

import java.util.List;

public class AITrader extends AIModule
{
    protected int collectDistXZ = 6, collectDistY = 4, blockSearchPasses = 30;

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
        Target t = null;

        List<Entity> distEnts = EntityHelper.getEntitiesWithinDistance(this.getAgent(), this.collectDistXZ, this.collectDistY);
        for (Entity distEnt : distEnts)
        {
            //Is Entity Item
            if (EntityItem.class.isInstance(distEnt) && ((this.demand.isItemDemanded(((EntityItem) distEnt).getEntityItem()) != null)))// ||
                //this.demand.isEntityDemanded(distEnt) != null))
                t = new Target(this.getAgent().worldObj, distEnt);
            //seek = true;

            //return t;
        }

        for (int i = 0; i < this.blockSearchPasses; i++)
        {
            BlockPos ep = this.getAgent().getPosition();
            BlockPos p = BlockPosHelper.getRandomPos(ep, this.collectDistXZ, this.collectDistY);
            IBlockState b = this.agentAI.entity().worldObj.getBlockState(p);

            if (b == null || Block.isEqualTo(b.getBlock(), Blocks.air))
                continue;

            //Block b2 = EcoFlora.getFloraBlockDrop(new ItemStack(Item.getItemFromBlock(b)));
            ItemStack s = EcoFlora.getFloraBlockDrop(b);

            if (s == null)
                continue;

            if (this.demand.isItemDemanded(s) != null)
                t = new Target(this.agentAI.entity().worldObj, p);
        }

        if (t != null)
            return t;

        return null;
    }

    public boolean doWorkContinue()
    {
        if (this.getAgent().worldObj.isRemote)
            return false;

        if (this.workTarget.pos != null)
        {
            //S25PacketBlockBreakAnim packet = new S25PacketBlockBreakAnim(0, this.workTarget.pos, DAMAGE);
            //int dimension = this.getAgent().dimension;
            float bd = 0;
            //Minecraft.getMinecraft().theWorld.sendBlockBreakProgress(this.getAgent().getEntityId(), this.workTarget.pos, (int) (bd * 10.0F) - 1);

            this.getAgent().worldObj.destroyBlock(this.workTarget.pos, true);
            return true;
        }

        if (this.workTarget.entity instanceof EntityItem)
        {
            EntityItem entityItem = (EntityItem) this.workTarget.entity;
            ItemStack r = this.agentAI.addToInventory(entityItem.getEntityItem());

            if (r == null)
                entityItem.setDead();

            if (r != null && r.stackSize != entityItem.getEntityItem().stackSize)
            {
                if (r.stackSize != 0)
                    entityItem.setEntityItemStack(r);
            }
            return false;
        }

        return false;
    }

    public AITrader(IDemand demand, AICore entity, AINavigate navigate, Target t)
    {
        super(demand, entity, navigate, t);
    }
}
