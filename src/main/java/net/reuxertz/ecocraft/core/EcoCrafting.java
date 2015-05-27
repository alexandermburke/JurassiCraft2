package net.reuxertz.ecocraft.core;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.reuxertz.ecocraft.core.registry.ECItemRegistry;

import java.util.Iterator;
import java.util.List;

public class EcoCrafting {

    public static void InitCraftingRecipes()
    {
        CraftingManager inst = CraftingManager.getInstance();

        //Remove Crafting Recipes
        EcoCrafting.RemoveVanillaItems();

        //Vanilla Recipes
        inst.addRecipe(new ItemStack(Blocks.cobblestone), new Object[] {"##", "##", '#', Blocks.gravel});
        inst.addRecipe(new ItemStack(Blocks.wool), new Object[] {"##", "##", '#', Items.string});
        inst.addRecipe(new ItemStack(Items.string, 4), new Object[] {"#", '#', Blocks.wool});
        inst.addRecipe(new ItemStack(Items.saddle), new Object[] {new String[] {"XXX", "X#X", "# #"}, 'X', Items.leather, '#', Items.iron_ingot});

        //Sub-Item Recipes
        inst.addRecipe(new ItemStack(Items.gold_nugget, 9), new Object[] {"#", '#', Items.gold_ingot});
        inst.addRecipe(new ItemStack(Items.diamond), new Object[] {"###", "###", "###", '#', ECItemRegistry.diamondShard});
        inst.addRecipe(new ItemStack(ECItemRegistry.diamondShard, 9), new Object[] {"#", '#', Items.diamond});
        inst.addRecipe(new ItemStack(Items.emerald), new Object[] {"###", "###", "###", '#', ECItemRegistry.emeraldShard});
        inst.addRecipe(new ItemStack(ECItemRegistry.emeraldShard, 9), new Object[] {"#", '#', Items.emerald});
        inst.addRecipe(new ItemStack(Items.iron_ingot), new Object[] {"###", "###", "###", '#', ECItemRegistry.ironNugget});
        inst.addRecipe(new ItemStack(ECItemRegistry.ironNugget, 9), new Object[] {"#", '#', Items.iron_ingot});

        //Tools
        inst.addRecipe(new ItemStack(ECItemRegistry.flintPickaxe), new Object[] {new String[] {"XXX", " # ", " # "}, '#', Items.stick, 'X', Items.flint});
        inst.addRecipe(new ItemStack(ECItemRegistry.flintAxe), new Object[] {new String[] {"XXX", "X# ", " # "}, '#', Items.stick, 'X', Items.flint});
        inst.addRecipe(new ItemStack(ECItemRegistry.flintShovel), new Object[] {new String[] {" X ", " # ", " # "}, '#', Items.stick, 'X', Items.flint});
        inst.addRecipe(new ItemStack(ECItemRegistry.flintHoe), new Object[] {new String[] {"XX ", " # ", " # "}, '#', Items.stick, 'X', Items.flint});
        inst.addRecipe(new ItemStack(ECItemRegistry.ironHammer), new Object[] {new String[] {"XXY", " #Y", " # "}, '#', Items.stick, 'X', Items.iron_ingot, 'Y', ECItemRegistry.ironNugget });
        inst.addRecipe(new ItemStack(ECItemRegistry.ironChisel), new Object[] {new String[] {"Y", "#", "#"}, '#', Items.stick, 'Y', ECItemRegistry.ironNugget });
        inst.addRecipe(new ItemStack(ECItemRegistry.ironKnife), new Object[] {new String[] {"Y", "#"}, '#', Items.stick, 'Y', ECItemRegistry.ironNugget });
    }
    public static void RemoveVanillaItems()
    {
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

        Iterator<IRecipe> Leash = recipes.iterator();

        while (Leash.hasNext()) {
            ItemStack is = Leash.next().getRecipeOutput();
            if (is != null)
            {
                boolean rem = false;
                Item item = is.getItem();
                Block block = null;
                if (ItemBlock.class.isInstance(item))
                    block = ((ItemBlock)item).getBlock();

                if (item == Items.wooden_axe)
                    rem = true;
                if (item == Items.wooden_pickaxe)
                    rem = true;
                if (item == Items.wooden_sword)
                    rem = true;
                if (item == Items.stone_axe)
                    rem = true;
                if (item == Items.stone_pickaxe)
                    rem = true;
                if (item == Items.stone_sword)
                    rem = true;

                if (item == Items.dye && item.getMetadata(15) == 15)
                {
                    continue;
                }

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
