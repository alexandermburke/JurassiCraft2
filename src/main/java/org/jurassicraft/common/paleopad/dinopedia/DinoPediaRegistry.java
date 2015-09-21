package org.jurassicraft.common.paleopad.dinopedia;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DinoPediaRegistry
{
    private static final Map<IRecipe, ItemStack> recipes = new HashMap<IRecipe, ItemStack>();

    private static final List<ItemStack> registeredItems = new ArrayList<ItemStack>();

    public static void addItemRecipe(ItemStack stack, IRecipe recipe)
    {
        recipes.put(recipe, stack);

        registeredItems.add(stack);
    }

    public static List<IRecipe> getRecipesForItem(ItemStack stack)
    {
        List<IRecipe> recipesForItem = new ArrayList<IRecipe>();

        for (Map.Entry<IRecipe, ItemStack> entry : recipes.entrySet())
        {
            if (ItemStack.areItemStacksEqual(entry.getValue(), stack))
            {
                recipesForItem.add(entry.getKey());
            }
        }

        return recipesForItem;
    }

    public static List<ItemStack> getItems()
    {
        return registeredItems;
    }
}
