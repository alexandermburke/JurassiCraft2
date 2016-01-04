package org.jurassicraft.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jurassicraft.common.item.ItemSoftTissue;

public class SlotDNAHolder extends Slot
{
    public SlotDNAHolder(IInventory inventory, int slotIndex, int yPosition)
    {
        super(inventory, slotIndex, 44, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof ItemSoftTissue;
    }
}
