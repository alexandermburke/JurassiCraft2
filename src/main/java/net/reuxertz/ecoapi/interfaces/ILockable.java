package net.reuxertz.ecoapi.interfaces;

import net.minecraft.item.ItemStack;

public interface ILockable
{
    boolean acceptsKey(ItemStack stack);

    boolean isLocked();
}
