package org.jurassicraft.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jurassicraft.common.item.ItemDNA;
import org.jurassicraft.common.item.ItemPlantDNA;

public class SlotTestTube extends Slot
{
    public SlotTestTube(IInventory inventory, int slotIndex, int xPosition, int yPosition)
    {
        super(inventory, slotIndex, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof ItemDNA || stack.getItem() instanceof ItemPlantDNA;
    }
}
