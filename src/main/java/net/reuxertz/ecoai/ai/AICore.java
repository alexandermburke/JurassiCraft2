package net.reuxertz.ecoai.ai;

import com.google.common.collect.Lists;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.reuxertz.ecoai.ai.modules.AIModule;
import net.reuxertz.ecoai.demand.DemandHunger;
import net.reuxertz.ecoai.demand.IDemand;
import net.reuxertz.ecoai.demand.ItemDemand;
import net.reuxertz.ecoai.state.StateLife;
import net.reuxertz.ecoapi.ecology.EcoFauna;
import net.reuxertz.ecoapi.ecology.role.IEcologicalRole;
import net.reuxertz.ecoapi.entity.Target;
import net.reuxertz.ecoapi.item.BaseItem;
import net.reuxertz.ecoapi.util.CounterObj;
import net.reuxertz.ecoapi.util.IdObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AICore extends AIBase
{
    public static Random RND = new Random();

    //Operation Fields
    protected CounterObj timer;

    //Entity Fields
    protected IdObject id;
    protected int maxInvSize = 27;
    protected IEcologicalRole role = null;
    protected List<ItemStack> curInventory = Lists.newArrayList();
    protected List<ItemDemand> curItemDemands = Lists.newArrayList();
    protected List<IDemand> curDemands = Lists.newArrayList();
    protected List<Class> aiObjs = new ArrayList<Class>();
    protected StateLife state;
    protected AIModule aiObject = null;

    //Static Getters
    public static AICore getCore(EntityCreature e)
    {
        return (AICore) AICore.getAi(e, AICore.class);
    }

    public static AINavigate getAiNavigate(EntityCreature e)
    {
        return (AINavigate) AICore.getAi(e, AINavigate.class);
    }

    public static Object getAi(EntityCreature e, Class AIClass)
    {
        for (int i = 0; i < e.tasks.taskEntries.size(); i++)
        {
            EntityAIBase t = ((EntityAITasks.EntityAITaskEntry) e.tasks.taskEntries.get(i)).action;
            if (AIClass.isInstance(t))
            {
                return t;
            }
        }
        return null;
    }

    public static List<EntityAIBase> getAllAI(EntityCreature e)
    {
        List<EntityAIBase> r = new ArrayList<EntityAIBase>();
        for (int i = 0; i < e.tasks.taskEntries.size(); i++)
        {
            EntityAIBase t = ((EntityAITasks.EntityAITaskEntry) e.tasks.taskEntries.get(i)).action;
            r.add(t);
        }
        return r;
    }

    public static StateLife getBioState(EntityCreature e)
    {
        return AICore.getCore(e).state;
    }

    //Getters
    public StateLife getBioState()
    {
        return state;
    }

    public int getMaxInventorySize()
    {
        return maxInvSize;
    }

    //Inventory
    protected ItemStack addToStack(ItemStack stackDest, ItemStack stackSrc)
    {
        if (!BaseItem.itemsEqual(stackDest, stackSrc))
            return stackSrc;

        if (stackDest.stackSize == stackDest.getMaxStackSize())
            return stackSrc;

        int spcRem = stackDest.getMaxStackSize() - stackDest.stackSize;

        if (spcRem >= stackSrc.stackSize)
        {
            stackDest.stackSize += stackSrc.stackSize;
            stackSrc.stackSize = 0;
        }
        if (spcRem < stackSrc.stackSize)
        {
            stackDest.stackSize = stackDest.getMaxStackSize();
            stackSrc.stackSize -= spcRem;
        }

        if (stackSrc.stackSize == 0)
            return null;
        else
            return stackSrc;
    }

    protected ItemStack removeFromStack(ItemStack stackRemAmt, ItemStack stackSrc)
    {
        if (!BaseItem.itemsEqual(stackRemAmt, stackSrc))
            return stackRemAmt;

        if (stackRemAmt.stackSize == stackSrc.getMaxStackSize() - stackSrc.stackSize)
        {
            stackRemAmt.stackSize = 0;
            stackSrc.stackSize = stackSrc.getMaxStackSize();
            return null;
        }

        if (stackRemAmt.stackSize > stackSrc.stackSize)
        {
            stackRemAmt.stackSize -= stackSrc.stackSize;
            stackSrc.stackSize = 0;
            return stackRemAmt;
        }
        if (stackRemAmt.stackSize < stackSrc.stackSize)
        {
            stackSrc.stackSize -= stackRemAmt.stackSize;
            stackRemAmt.stackSize = 0;
            return null;
        }

        if (stackRemAmt.stackSize == 0)
            return null;
        else
            return stackRemAmt;
    }

    public ItemStack addToInventory(ItemStack stack)
    {
        if (stack == null || stack.stackSize == 0)
            return null;

        for (int i = 0; i < this.curInventory.size(); i++)
            stack = this.addToStack(this.curInventory.get(i), stack);

        if (stack != null && this.curInventory.size() < this.maxInvSize)
        {
            this.curInventory.add(stack);
            return null;
        }

        return stack;
    }

    public ItemStack getFromInventory(ItemStack stack)
    {
        int ss = stack.stackSize;
        ItemStack remainder = this.removeFromInventory(stack);

        if (remainder == null)
            return new ItemStack(remainder.getItem(), ss, remainder.getItemDamage());
        else
            return new ItemStack(remainder.getItem(), ss - remainder.stackSize, remainder.getItemDamage());
    }

    public ItemStack removeFromInventory(ItemStack stack)
    {
        if (stack == null || stack.stackSize == 0)
            return null;

        for (int i = 0; i < this.curInventory.size(); i++)
        {
            ItemStack src = this.curInventory.get(i);
            stack = this.removeFromStack(stack, src);

            if (src.stackSize == 0)
                this.curInventory.set(i, null);
            else
                this.curInventory.set(i, src);

            if (stack.stackSize == 0)
                break;
        }

        if (stack.stackSize == 0)
            return null;
        else
            return stack;
    }

    public int getInventoryCount(ItemStack stack)
    {
        int count = 0;
        for (int i = 0; i < this.curInventory.size(); i++)
            if (BaseItem.itemsEqual(stack, this.curInventory.get(i)))
                count += this.curInventory.get(i).stackSize;

        return 0;
    }

    //Constructor
    public AICore(EntityCreature entity, List<Class> aiObjs)
    {
        super(entity);

        if (entity.worldObj.isRemote)
            return;

        if (aiObjs != null)
            this.aiObjs = aiObjs;
        else
            this.aiObjs = EcoFauna.getDefaultAIObjs(entity);
        this.role = EcoFauna.getRole(entity);

        if (role != null)
            this.curDemands.add(new DemandHunger(this, role.getFoodItems()));
        //this.curDemands.add(new DemandTemp(this));
        /*for (IEcologicalRole role : this.roles)
        {
            for (int j = 0; j < role.getFoodItems().size(); j++)
            {
                ItemStack ni = new ItemStack(role.getFoodItems().get(j), 0);
                this.curDemands.add(new DemandHunger(this, ni));
            }
        }*/

        readFromEntityNbt();

        id = IdObject.validateIdObj(entity, true);
        state = new StateLife(entity);
        timer = new CounterObj(entity.worldObj.getWorldTime(), 80, 20);
    }

    public AICore(EntityCreature entity)
    {
        this(entity, null);
    }

    //NBT
    @Override
    public NBTTagCompound writeToEntityNbt()
    {
        NBTTagCompound nbt = this.entity().getEntityData();

        NBTTagList nbtInvList = new NBTTagList();
        for (ItemStack i : this.curInventory)
            nbtInvList.appendTag(i.writeToNBT(new NBTTagCompound()));
        nbt.setTag("aiinv", nbtInvList);

        //this.entity.writeToNBT(nbt);

        return nbt;
    }

    @Override
    public void readFromEntityNbt()
    {
        NBTTagCompound nbt = this.entity().getEntityData();

        NBTTagList nbtInvList = nbt.getTagList("aiinv", 10);
        for (int i = 0; i < nbtInvList.tagCount(); i++)
            this.curInventory.add(ItemStack.loadItemStackFromNBT(nbtInvList.getCompoundTagAt(i)));
    }

    public void updateTask()
    {
        if (entity.worldObj.isRemote)
            return;

        this.updateActions();

        if (!this.timer.updateMSTime(entity().worldObj.getWorldTime()))
            return;

        AINavigate aiNav = AICore.getAiNavigate(this.entity());
        //AINav aiNav = GetAINav();
        //if ((aiNav != null) && (!aiNav.isEnabled()))
        //{
        //    //aiNav.setEnabled(true);
        //aiNav.activateWander();
        //    aiNav.activateIdleWander(.05);
        //}

        //if (!(EntityWolf.class.isInstance(this.agentAI())) || EntityRabbit.class.isInstance(this.agentAI()))
        //    return;

        //this.updateBio();

        this.updateWants();

        //System.out.println("aiTick");
    }

    public void updateActions()
    {

        if (aiObject != null)
        {
            if (!aiObject.isDead())
            {
                aiObject.update();
                return;
            }
            else
                aiObject = null;
            //if (!aiObject.getWorkCompleted())
            //else
            //    aiObject.setWorkCompleted(false);
        }

        List<AIModule> nobjs = new ArrayList<AIModule>();
        for (IDemand curDemand : this.curDemands)
        {
            boolean seek = false, heldItem = false;
            Target t = null;
            AIModule nai = null;//c.newInstance(curDemand, this, getAiNavigate(this.entity()), null);

            //Create AI Object instance based on avaialble classes using reflection
            for (Class c : this.aiObjs)
            {
                Constructor[] cs = c.getConstructors();
                Constructor ci = cs[0];
                //for (Constructor ci: cs)
                {
                    //if (ci.getParameterCount() == 4)
                    //{
                    try
                    {
                        nai = (AIModule) ci.newInstance(curDemand, this, getAiNavigate(this.entity()), t);
                        //break;
                    }
                    catch (InstantiationException x)
                    {
                        x.printStackTrace();
                    }
                    catch (InvocationTargetException x)
                    {
                        x.printStackTrace();
                    }
                    catch (IllegalAccessException x)
                    {
                        x.printStackTrace();
                    }
                    //}
                }

                //AIHunt ai = new AIHunt(curDemand, this, getAiNavigate(this.entity()), null);
                t = nai.nextNavigatePosition();

                if (t != null)
                {
                    //this.aiObject = nai;
                    nai.setTarget(t);
                    nobjs.add(nai);

                    //return;
                }
            }
        }

        if (nobjs.size() > 0)
            this.aiObject = nobjs.get(0);
        else if (!AICore.getAiNavigate(this.entity).isEnabled())
            AICore.getAiNavigate(this.entity).activateIdleWander(.05);

    }

    public void updateWants()
    {
        //HashMap<ItemDemand, ItemDemand> oldNewMap = new HashMap<ItemDemand, ItemDemand>();

        List<ItemDemand> nds = new ArrayList<ItemDemand>();
        for (ItemDemand itemDemand : this.curItemDemands)
        {
            //oldNewMap.put(itemDemand, new ItemDemand(itemDemand.stack));
            ItemDemand nd = new ItemDemand(itemDemand.stack);
            for (IDemand demand : this.curDemands)
            {
                if (demand.isItemDemanded(itemDemand.stack) != null)
                    nd.addDemand(demand, itemDemand.stack, demand.wantedDemandCount(itemDemand.stack));
            }

            if (nd.wantSize > 0)
                nds.add(nd);
        }

        this.curItemDemands = nds;
    }
}
