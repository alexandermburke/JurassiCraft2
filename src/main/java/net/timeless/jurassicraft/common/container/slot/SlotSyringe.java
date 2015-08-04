package net.timeless.jurassicraft.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.timeless.jurassicraft.common.item.ItemSoftTissue;
import net.timeless.jurassicraft.common.item.ItemSyringe;

public class SlotSyringe extends Slot
{
    public SlotSyringe(IInventory inventory, int slotIndex, int xPosition, int yPosition)
    {
        super(inventory, slotIndex, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof ItemSyringe;
    }
}
