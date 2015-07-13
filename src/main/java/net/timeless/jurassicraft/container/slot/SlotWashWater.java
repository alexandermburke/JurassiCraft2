package net.timeless.jurassicraft.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.timeless.jurassicraft.tileentity.TileCleaningStation;

public class SlotWashWater extends Slot
{
    public SlotWashWater(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack stack)
    {
        return TileCleaningStation.isItemFuel(stack);
    }

    public int getItemStackLimit(ItemStack stack)
    {
        return 1;
    }
}