package net.timeless.jurassicraft.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.timeless.jurassicraft.block.JCBlockRegistry;

public class JCRecipeRegistry
{
    public static void preInitCommon()
    {

    }

    public static void initCommon()
    {
        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.cleaning_station), new Object[]{"WCW", "W.W", 'C', Blocks.crafting_table, 'W', Blocks.planks});
    }

    public static void postInitCommon()
    {

    }

    public static void preInitClientOnly()
    {

    }

    public static void initClientOnly()
    {

    }

    public static void postInitClientOnly()
    {

    }
}
