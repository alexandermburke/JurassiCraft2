package net.reuxertz.ecoapi.ecology.role;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseEcologicalRole implements IEcologicalRole
{
    protected List<ItemStack> foodItems = new ArrayList<ItemStack>();

    protected BaseEcologicalRole()
    {
    }

    public void addFoodItem(ItemStack foodItem)
    {
        for (ItemStack is : foodItems)
        {
            if (ItemStack.areItemsEqual(is, foodItem))
            {
                System.out.println("ItemStack { " + is.getDisplayName() +
                        " } skipped due to duplicate entry in ecological role { " + this.getName() + " }");
                return;
            }
        }
        this.foodItems.add(foodItem);
    }

    public List<ItemStack> getFoodItems()
    {
        return this.foodItems;
    }

    public abstract String getName();
}
