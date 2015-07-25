package net.reuxertz.ecoai.demand;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public interface IDemand
{
    ItemStack isItemDemanded(ItemStack demanded);

    ItemStack isEntityDemanded(Entity demanded);

    int wantedDemandCount(ItemStack demanded);
}
