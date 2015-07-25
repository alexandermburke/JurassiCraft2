package net.reuxertz.ecoai.demand;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.reuxertz.ecoai.ai.AICore;

import java.util.List;

public class DemandHunger extends BaseDemand
{
    public DemandHunger(AICore ai, List<ItemStack> i)
    {
        super(ai, i);
    }

    public int wantedDemandCount(ItemStack stack)
    {
        if (!ItemFood.class.isInstance(stack.getItem()))
            return 0;

        return 64;
    }

    public ItemStack isItemDemanded(ItemStack item)
    {
        return super.isItemDemanded(item);
    }

    public ItemStack isEntityDemanded(Entity e)
    {
        return super.isEntityDemanded(e);
    }

    public void performSeekedEntityAction2(Entity e)
    {
        /*
        if (e == null)
            return;

        if (EntityItem.class.isInstance(e))
        {

            EntityItem ei = ((EntityItem) e);
            ItemStack stack = ei.getEntityItem();

            if (stack != null && this.isItemDemanded(stack) != null)
            {
                //Raw Eat
                ItemStack retItemStack = stack.copy();

                if (this.aiCore.getBioState().isHungry())
                    retItemStack = this.aiCore.getBioState().intakeFood(retItemStack);

                //Add To Inventory
                if (retItemStack != null && this.aiCore.getMaxInventorySize() > 0)
                    retItemStack = this.aiCore.addToInventory(retItemStack);

                //Handle environment
                if (retItemStack != null && retItemStack.stackSize > 0)
                    ei.setEntityItemStack(retItemStack);

                //Remove if null/zero
                if (retItemStack == null || retItemStack.stackSize == 0)
                    this.aiCore.agentAI().worldObj.removeEntity(ei);
            }
        }*/

    }
}
