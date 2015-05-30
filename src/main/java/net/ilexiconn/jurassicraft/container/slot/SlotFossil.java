package net.ilexiconn.jurassicraft.container.slot;

import net.ilexiconn.jurassicraft.item.ItemFossil;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFossil extends Slot
{

    public SlotFossil(IInventory inventory, int slotIndex, int xPosition, int yPosition)
    {
        super(inventory, slotIndex, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof ItemFossil;
    }
}
