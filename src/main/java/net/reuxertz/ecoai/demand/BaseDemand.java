package net.reuxertz.ecoai.demand;

import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.item.ItemStack;
import net.reuxertz.ecoai.ai.AICore;
import net.reuxertz.ecoapi.ecology.EcoFauna;
import net.reuxertz.ecoapi.item.BaseItem;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDemand implements IDemand
{
    protected List<ItemStack> itemsDemanded = Lists.newArrayList();
    protected AICore aiCore = null;

    public BaseDemand(AICore ai, List<ItemStack> demand)
    {
        aiCore = ai;
        itemsDemanded = demand;
    }

    public AICore getAiCore()
    {
        return this.aiCore;
    }

    public ItemStack isItemDemanded(ItemStack demanded)
    {
        if (demanded == null)
            return null;

        for (ItemStack anItemsDemanded : this.itemsDemanded)
            if (ItemStack.areItemsEqual(demanded, anItemsDemanded))
                return demanded;

        return null;
    }

    public ItemStack isEntityDemanded(Entity demanded)
    {
        List<ItemStack> drops = new ArrayList<ItemStack>();

        for (ItemStack anItemsDemanded : itemsDemanded)
        {
            if (EntityCreature.class.isInstance(demanded))
            {
                ItemStack heldItem = ((EntityCreature) demanded).getHeldItem();

                if (heldItem != null && BaseItem.itemsEqual(heldItem, anItemsDemanded))
                    drops.add(((EntityCreature) demanded).getHeldItem());

                List<ItemStack> items = EcoFauna.getDropItemsByEntity(demanded);
                for (int j = 0; items != null && j < items.size(); j++)
                {
                    if (BaseItem.itemsEqual(items.get(j), anItemsDemanded))
                    {
                        drops.add(items.get(j));
                    }
                }
            }
        }

        if (drops.size() > 0)
            return drops.get(0);

        return null;

    }

    public abstract int wantedDemandCount(ItemStack demanded);
}
