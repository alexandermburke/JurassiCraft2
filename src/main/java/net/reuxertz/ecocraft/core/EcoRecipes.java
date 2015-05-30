package net.reuxertz.ecocraft.core;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.util.Iterator;
import java.util.List;

public class EcoRecipes
{

    public static void InitCraftingRecipes()
    {
        CraftingManager inst = CraftingManager.getInstance();

        //Remove Crafting Recipes
        EcoRecipes.RemoveVanillaItems();

        //Vanilla Recipes
        inst.addRecipe(new ItemStack(Blocks.cobblestone), new Object[]{"##", "##", '#', Blocks.gravel});
        inst.addRecipe(new ItemStack(Blocks.wool), new Object[]{"##", "##", '#', Items.string});
        inst.addRecipe(new ItemStack(Items.string, 4), new Object[]{"#", '#', Blocks.wool});
        inst.addRecipe(new ItemStack(Items.saddle), new Object[]{new String[]{"XXX", "X#X", "# #"}, 'X', Items.leather, '#', Items.iron_ingot});

        //Sub-Item Recipes
        inst.addRecipe(new ItemStack(Items.gold_nugget, 9), new Object[]{"#", '#', Items.gold_ingot});
        inst.addRecipe(new ItemStack(Items.diamond), new Object[]{"###", "###", "###", '#', EcoRegistry.diamondShard});
        inst.addRecipe(new ItemStack(EcoRegistry.diamondShard, 9), new Object[]{"#", '#', Items.diamond});
        inst.addRecipe(new ItemStack(Items.emerald), new Object[]{"###", "###", "###", '#', EcoRegistry.emeraldShard});
        inst.addRecipe(new ItemStack(EcoRegistry.emeraldShard, 9), new Object[]{"#", '#', Items.emerald});
        inst.addRecipe(new ItemStack(Items.iron_ingot), new Object[]{"###", "###", "###", '#', EcoRegistry.ironNugget});
        inst.addRecipe(new ItemStack(EcoRegistry.ironNugget, 9), new Object[]{"#", '#', Items.iron_ingot});

        //Tools
        inst.addRecipe(new ItemStack(EcoRegistry.flintPickaxe), new Object[]{new String[]{"XXX", " # ", " # "}, '#', Items.stick, 'X', Items.flint});
        inst.addRecipe(new ItemStack(EcoRegistry.flintAxe), new Object[]{new String[]{"XXX", "X# ", " # "}, '#', Items.stick, 'X', Items.flint});
        inst.addRecipe(new ItemStack(EcoRegistry.flintShovel), new Object[]{new String[]{" X ", " # ", " # "}, '#', Items.stick, 'X', Items.flint});
        inst.addRecipe(new ItemStack(EcoRegistry.flintHoe), new Object[]{new String[]{"XX ", " # ", " # "}, '#', Items.stick, 'X', Items.flint});
        inst.addRecipe(new ItemStack(EcoRegistry.ironHammer), new Object[]{new String[]{"XXY", " #Y", " # "}, '#', Items.stick, 'X', Items.iron_ingot, 'Y', EcoRegistry.ironNugget});
        inst.addRecipe(new ItemStack(EcoRegistry.ironChisel), new Object[]{new String[]{"Y", "#", "#"}, '#', Items.stick, 'Y', EcoRegistry.ironNugget});
        inst.addRecipe(new ItemStack(EcoRegistry.ironKnife), new Object[]{new String[]{"Y", "#"}, '#', Items.stick, 'Y', EcoRegistry.ironNugget});
    }

    public static void RemoveVanillaItems()
    {
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

        Iterator<IRecipe> Leash = recipes.iterator();

        while (Leash.hasNext())
        {
            ItemStack is = Leash.next().getRecipeOutput();
            if (is != null)
            {
                boolean rem = false;
                Item item = is.getItem();
                Block block = null;
                if (ItemBlock.class.isInstance(item))
                    block = ((ItemBlock) item).getBlock();

                if (item == Items.wooden_axe)
                    rem = true;
                if (item == Items.wooden_pickaxe)
                    rem = true;
                if (item == Items.stone_axe)
                    rem = true;
                if (item == Items.stone_pickaxe)
                    rem = true;

                if (rem)
                {
                    Leash.remove();

                    if (item != null)
                        item.setCreativeTab(null);
                    if (block != null)
                        block.setCreativeTab(null);
                }
            }
        }
    }

}
