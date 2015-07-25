package net.reuxertz.ecoapi.ecology.role;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface IEcologicalRole
{
    List<ItemStack> getFoodItems();

    String getName();

    void addFoodItem(ItemStack stack);
}
