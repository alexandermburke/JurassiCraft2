package net.timeless.jurassicraft.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

public class SlotStorage extends Slot
{
    private boolean stored;

    public SlotStorage(IInventory inventory, int slotIndex, int xPosition, int yPosition, boolean stored)
    {
        super(inventory, slotIndex, xPosition, yPosition);

        this.stored = stored;
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        if (stored)
        {
            return stack.getItem() == JCItemRegistry.storage_disc && (stack.getTagCompound() != null && stack.getTagCompound().hasKey("Genetics"));
        } else
        {
            return stack.getItem() == JCItemRegistry.storage_disc && (stack.getTagCompound() == null || !stack.getTagCompound().hasKey("Genetics"));
        }
    }
}
