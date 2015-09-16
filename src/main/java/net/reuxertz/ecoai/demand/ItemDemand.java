package net.reuxertz.ecoai.demand;

import net.minecraft.item.ItemStack;
import net.reuxertz.ecoapi.item.BaseItem;

import java.util.ArrayList;
import java.util.List;

public class ItemDemand
{
    public final List<IDemand> demands = new ArrayList<IDemand>();
    public ItemStack stack;
    public int wantSize = 0;

    public ItemDemand(IDemand demand, ItemStack stack, int stackSize)
    {
        ItemStack ns = stack.copy();
        ns.stackSize = stackSize;
        this.stack = ns;
        this.wantSize = stackSize;

        if (demand != null)
            this.demands.add(demand);
    }

    public void addDemand(IDemand demand, ItemStack stackType, int stackSize)
    {
        if (!BaseItem.itemsEqual(stackType, stack))
            return;

        boolean addDemand = true;
        for (IDemand d: this.demands)
            if (d.getClass().isInstance(demand))
                addDemand = false;

        if (addDemand)
            this.demands.add(demand);

        this.wantSize += stackSize;
    }
}
