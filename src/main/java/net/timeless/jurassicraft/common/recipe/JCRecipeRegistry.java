package net.timeless.jurassicraft.common.recipe;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.timeless.jurassicraft.common.block.JCBlockRegistry;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

public class JCRecipeRegistry
{
    public void register()
    {
        for (Dinosaur dinosaur : JCEntityRegistry.getDinosaurs())
        {
            if (dinosaur.shouldRegister())
            {
                int meta = JCEntityRegistry.getDinosaurId(dinosaur);

                GameRegistry.addSmelting(new ItemStack(JCItemRegistry.dino_meat, 1, meta), new ItemStack(JCItemRegistry.dino_steak, 1, meta), 5F);
            }
        }

        int i = 0;

        for (Block block : JCBlockRegistry.planks)
        {
            GameRegistry.addShapelessRecipe(new ItemStack(block, 4), JCBlockRegistry.woods[i]);

            i++;
        }

        GameRegistry.addSmelting(new ItemStack(JCBlockRegistry.gypsum_cobblestone), new ItemStack(JCBlockRegistry.gypsum_stone), 1.5F);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.iron_blades),
                "I I",
                " S ",
                "I I",
                'I', Items.iron_ingot, 'S', Items.stick);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.iron_rod),
                "ISI",
                "ISI",
                "ISI",
                'I', Items.iron_ingot, 'S', Items.stick);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.fossil_grinder),
                "lBl",
                "rRr",
                "IPI",
                'I', Items.iron_ingot, 'R', JCItemRegistry.iron_rod, 'B', JCItemRegistry.iron_blades, 'r', Items.redstone, 'l', new ItemStack(Items.dye, 1, 4), 'P', Blocks.piston);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.plaster_and_bandage, 9),
                "PPP",
                "PWP",
                "PPP",
                'P', Items.paper, 'W', Blocks.wool);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.cage_small, 1),
                "III",
                "BBB",
                "III",
                'I', Items.iron_ingot, 'B', Blocks.iron_bars);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.gypsum_bricks, 4),
                "SS",
                "SS",
                'S', JCBlockRegistry.gypsum_stone);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.cleaning_station),
                "iii",
                "RGR",
                "IBI",
                'i', Items.iron_ingot, 'B', Items.bucket, 'G', Blocks.glass_pane, 'R', Items.redstone, 'I', Blocks.iron_block);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.reinforced_stone, 8),
                "PPP",
                "PWP",
                "PPP",
                'P', Blocks.stone, 'W', Items.iron_ingot);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.reinforced_bricks, 8),
                "PPP",
                "PWP",
                "PPP",
                'P', Blocks.stonebrick, 'W', Items.iron_ingot);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.reinforced_bricks, 4),
                "SS",
                "SS",
                'S', JCBlockRegistry.reinforced_stone);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.empty_test_tube),
                "I",
                "G",
                "G",
                'G', Blocks.glass_pane, 'I', Items.iron_ingot);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.petri_dish),
                "G G",
                "GGG",
                'G', Blocks.glass_pane);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.blue_print),
                "BBB",
                "BPB",
                "BBB",
                'B', new ItemStack(Items.dye, 1, 4), 'P', Items.paper);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.empty_syringe),
                "  I",
                "IG ",
                "II ",
                'G', Blocks.glass_pane, 'I', Items.iron_ingot);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.empty_syringe),
                "I  ",
                " GI",
                " II",
                'G', Blocks.glass_pane, 'I', Items.iron_ingot);

        GameRegistry.addSmelting(new ItemStack(Items.potionitem, 1, 0), new ItemStack(JCItemRegistry.dna_base), 1.0F);
    }
}
