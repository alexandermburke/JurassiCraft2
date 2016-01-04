package org.jurassicraft.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jurassicraft.common.item.ItemSyringe;

public class SlotSyringe extends Slot
{
    public SlotSyringe(IInventory inventory, int xPosition, int yPosition)
    {
        super(inventory, 0, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof ItemSyringe;
    }
}
