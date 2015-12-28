package org.jurassicraft.common.food;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.item.JCItemRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodHelper
{
    private static final Map<EnumFoodType, List<Item>> foods = new HashMap<EnumFoodType, List<Item>>();
    private static final List<Item> allFoods = new ArrayList<Item>();

    public static void init()
    {
        registerFood(Items.apple, EnumFoodType.PLANT);
        registerFood(Items.potato, EnumFoodType.PLANT);
        registerFood(Items.carrot, EnumFoodType.PLANT);
        registerFood(Items.wheat, EnumFoodType.PLANT);
        registerFood(Items.wheat_seeds, EnumFoodType.PLANT);
        registerFood(Items.melon_seeds, EnumFoodType.PLANT);
        registerFood(Items.pumpkin_seeds, EnumFoodType.PLANT);
        registerFood(Items.melon, EnumFoodType.PLANT);

        registerFood(Items.beef, EnumFoodType.MEAT);
        registerFood(Items.cooked_beef, EnumFoodType.MEAT);
        registerFood(Items.porkchop, EnumFoodType.MEAT);
        registerFood(Items.cooked_porkchop, EnumFoodType.MEAT);
        registerFood(Items.chicken, EnumFoodType.MEAT);
        registerFood(Items.cooked_chicken, EnumFoodType.MEAT);
        registerFood(Items.fish, EnumFoodType.FISH);
        registerFood(Items.cooked_fish, EnumFoodType.MEAT);
        registerFood(Items.mutton, EnumFoodType.MEAT);
        registerFood(Items.cooked_mutton, EnumFoodType.MEAT);
        registerFood(Items.rabbit, EnumFoodType.MEAT);
        registerFood(Items.cooked_rabbit, EnumFoodType.MEAT);

        registerFood(JCItemRegistry.dino_meat, EnumFoodType.MEAT);
        registerFood(JCItemRegistry.dino_steak, EnumFoodType.MEAT);

        for (Object item : Item.itemRegistry)
        {
            String resourceDomain = ((ResourceLocation) Item.itemRegistry.getNameForObject(item)).getResourceDomain();

            if (!resourceDomain.equals("minecraft"))
            {
                if (item instanceof ItemFood)
                {
                    ItemFood food = (ItemFood) item;

                    if (food.getHealAmount(new ItemStack(food)) > 3)
                    {
                        registerFood(food, EnumFoodType.PLANT);
                    }
                    else
                    {
                        registerFood(food, EnumFoodType.MEAT);
                    }
                }
            }
        }
    }

    public static void registerFood(Item food, EnumFoodType foodType)
    {
        if (!allFoods.contains(food))
        {
            List<Item> foodsForType = foods.get(foodType);

            if (foodsForType == null)
            {
                foodsForType = new ArrayList<Item>();
            }

            foodsForType.add(food);

            allFoods.add(food);

            foods.put(foodType, foodsForType);
        }
    }

    public static List<Item> getFoodsForFoodType(EnumFoodType type)
    {
        return foods.get(type);
    }

    public static EnumFoodType getFoodType(Item item)
    {
        for (EnumFoodType foodType : EnumFoodType.values())
        {
            if (getFoodsForFoodType(foodType).contains(item))
            {
                return foodType;
            }
        }

        return null;
    }

    public static boolean canDietEat(EnumDiet diet, Item item)
    {
        return getFoodsForDiet(diet).contains(item);
    }

    private static List<Item> getFoodsForDiet(EnumDiet diet)
    {
        List<Item> possibleItems = new ArrayList<Item>();

        if (diet.doesEatPlants())
        {
            possibleItems.addAll(getFoodsForFoodType(EnumFoodType.PLANT));
        }

        if (diet.doesEatFish())
        {
            possibleItems.addAll(getFoodsForFoodType(EnumFoodType.FISH));
        }

        if (diet.doesEatMeat())
        {
            possibleItems.addAll(getFoodsForFoodType(EnumFoodType.MEAT));
        }

        return possibleItems;
    }
}
