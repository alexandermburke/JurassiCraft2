package org.jurassicraft.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jurassicraft.common.item.JCItemRegistry;

public class SlotDNAExtraction extends Slot
{
    public SlotDNAExtraction(IInventory inventory, int slotIndex, int xPosition, int yPosition)
    {
        super(inventory, slotIndex, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() == JCItemRegistry.amber || stack.getItem() == JCItemRegistry.sea_lamprey;
    }
}
