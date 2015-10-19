package org.jurassicraft.common.recipe;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.item.JCItemRegistry;
import org.jurassicraft.common.paleopad.dinopedia.DinoPediaRegistry;

import java.util.HashMap;

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

            addRecipe(new ItemStack(JCBlockRegistry.stairs[i], 4),
                    "w  ",
                    "ww ",
                    "www",
                    'w', block);

            addRecipe(new ItemStack(JCBlockRegistry.stairs[i], 4),
                    "  w",
                    " ww",
                    "www",
                    'w', block);

            addRecipe(new ItemStack(JCBlockRegistry.slabs[i], 6),
                    "www",
                    'w', block);

            i++;
        }

        GameRegistry.addSmelting(new ItemStack(JCBlockRegistry.gypsum_cobblestone), new ItemStack(JCBlockRegistry.gypsum_stone), 1.5F);

        addRecipe(new ItemStack(JCItemRegistry.iron_blades),
                "I I",
                " S ",
                "I I",
                'I', Items.iron_ingot, 'S', Items.stick);

        addRecipe(new ItemStack(JCItemRegistry.iron_rod),
                "ISI",
                "ISI",
                "ISI",
                'I', Items.iron_ingot, 'S', Items.stick);

        addRecipe(new ItemStack(JCBlockRegistry.fossil_grinder),
                "lBl",
                "rRr",
                "IPI",
                'I', Items.iron_ingot, 'R', JCItemRegistry.iron_rod, 'B', JCItemRegistry.iron_blades, 'r', Items.redstone, 'l', new ItemStack(Items.dye, 1, 4), 'P', Blocks.piston);

        addRecipe(new ItemStack(JCItemRegistry.plaster_and_bandage, 9),
                "PPP",
                "PWP",
                "PPP",
                'P', Items.paper, 'W', Blocks.wool);

        addRecipe(new ItemStack(JCItemRegistry.cage_small, 1),
                "III",
                "BBB",
                "III",
                'I', Items.iron_ingot, 'B', Blocks.iron_bars);

        addRecipe(new ItemStack(JCItemRegistry.cage_small, 1, 1),
                "III",
                "GBG",
                "III",
                'I', Items.iron_ingot, 'G', Blocks.glass_pane, 'B', Items.water_bucket);

        addRecipe(new ItemStack(JCBlockRegistry.gypsum_bricks, 4),
                "SS",
                "SS",
                'S', JCBlockRegistry.gypsum_stone);

        addRecipe(new ItemStack(JCBlockRegistry.cleaning_station),
                "iii",
                "RGR",
                "IBI",
                'i', Items.iron_ingot, 'B', Items.bucket, 'G', Blocks.glass_pane, 'R', Items.redstone, 'I', Blocks.iron_block);

        addRecipe(new ItemStack(JCBlockRegistry.reinforced_stone, 8),
                "PPP",
                "PWP",
                "PPP",
                'P', Blocks.stone, 'W', Items.iron_ingot);

        addRecipe(new ItemStack(JCBlockRegistry.reinforced_bricks, 8),
                "PPP",
                "PWP",
                "PPP",
                'P', Blocks.stonebrick, 'W', Items.iron_ingot);

        addRecipe(new ItemStack(JCBlockRegistry.reinforced_bricks, 4),
                "SS",
                "SS",
                'S', JCBlockRegistry.reinforced_stone);

        addRecipe(new ItemStack(JCItemRegistry.empty_test_tube),
                "I",
                "G",
                "G",
                'G', Blocks.glass_pane, 'I', Items.iron_ingot);

        addRecipe(new ItemStack(JCItemRegistry.petri_dish),
                "G G",
                "GGG",
                'G', Blocks.glass_pane);

        addRecipe(new ItemStack(JCItemRegistry.blue_print),
                "BBB",
                "BPB",
                "BBB",
                'B', new ItemStack(Items.dye, 1, 4), 'P', Items.paper);

        addRecipe(new ItemStack(JCItemRegistry.empty_syringe),
                "  N",
                "IG ",
                "II ",
                'G', Blocks.glass_pane, 'I', Items.iron_ingot, 'N', JCItemRegistry.needle);

        addRecipe(new ItemStack(JCItemRegistry.empty_syringe),
                "N  ",
                " GI",
                " II",
                'G', Blocks.glass_pane, 'I', Items.iron_ingot, 'N', JCItemRegistry.needle);

        GameRegistry.addSmelting(new ItemStack(Items.potionitem, 1, 0), new ItemStack(JCItemRegistry.dna_base), 1.0F);

        addRecipe(new ItemStack(JCItemRegistry.needle),
                "GIG",
                "GIG",
                " I ",
                'G', Blocks.glass_pane, 'I', Items.iron_ingot);

        addRecipe(new ItemStack(JCBlockRegistry.dna_extractor),
                "III",
                "INI",
                "RSR",
                'S', JCBlockRegistry.dna_sequencer, 'I', Items.iron_ingot, 'R', Items.redstone, 'N', JCItemRegistry.needle);

        addRecipe(new ItemStack(JCBlockRegistry.dna_sequencer),
                "RDR",
                "GNG",
                "III",
                'G', Items.gold_ingot, 'I', Items.iron_ingot, 'N', JCItemRegistry.needle, 'D', JCItemRegistry.disc_reader, 'R', Items.redstone);

        addRecipe(new ItemStack(JCBlockRegistry.dna_synthesizer),
                "GDG",
                "RBR",
                "ITI",
                'G', Items.gold_ingot, 'I', Items.iron_ingot, 'B', Items.bucket, 'R', Items.redstone, 'D', JCItemRegistry.disc_reader);

        for (i = 0; i < 16; i++)
        {
            addRecipe(new ItemStack(JCBlockRegistry.cultivate_bottom, 1, i),
                    "GGG",
                    "GWG",
                    "III",
                    'G', new ItemStack(Blocks.stained_glass_pane, 1, i), 'W', Items.water_bucket, 'I', Items.iron_ingot);
        }

        addRecipe(new ItemStack(JCBlockRegistry.embryonic_machine),
                "GIG",
                "GIG",
                "III",
                'G', Blocks.glass_pane, 'I', Items.iron_ingot);

        addRecipe(new ItemStack(JCBlockRegistry.embryo_calcification_machine),
                "GIG",
                "GSG",
                "III",
                'G', Blocks.glass_pane, 'I', Items.iron_ingot, 'S', JCItemRegistry.needle);

        addRecipe(new ItemStack(JCBlockRegistry.incubator),
                "GGG",
                "RRR",
                "III",
                'I', Items.iron_ingot, 'R', Items.redstone, 'G', Blocks.glass);

        addRecipe(new ItemStack(JCBlockRegistry.dna_combinator),
                "IDI",
                "GRG",
                "III",
                'G', Blocks.glass, 'I', Items.iron_ingot, 'R', Items.redstone, 'D', JCItemRegistry.disc_reader);

        addRecipe(new ItemStack(JCBlockRegistry.dna_hybridizer),
                "IRI",
                "GIG",
                "IRI",
                'G', Blocks.glass, 'I', Items.iron_ingot, 'R', Items.redstone);

        addRecipe(new ItemStack(JCItemRegistry.laser),
                "PRP",
                "ILI",
                "IGI",
                'G', Blocks.glass, 'I', Items.iron_ingot, 'R', Items.redstone, 'P', Items.repeater, 'L', Blocks.redstone_lamp);

        addRecipe(new ItemStack(JCItemRegistry.disc_reader),
                "ILI",
                "RNR",
                "TPT",
                'N', JCItemRegistry.needle, 'I', Items.iron_ingot, 'R', Items.redstone, 'T', Blocks.redstone_torch, 'L', JCItemRegistry.laser, 'P', Blocks.piston);

        addRecipe(new ItemStack(JCItemRegistry.storage_disc),
                "III",
                "IGI",
                "III",
                'G', Items.gold_ingot, 'I', Items.iron_ingot);

        GameRegistry.addShapelessRecipe(new ItemStack(JCItemRegistry.plant_cells_petri_dish), JCItemRegistry.plant_cells, JCItemRegistry.petri_dish);

        addGrowthSerumRecipe(Items.cooked_beef);
        addGrowthSerumRecipe(Items.cooked_chicken);
        addGrowthSerumRecipe(Items.cooked_fish);
        addGrowthSerumRecipe(Items.cooked_mutton);
        addGrowthSerumRecipe(Items.cooked_porkchop);
        addGrowthSerumRecipe(Items.cooked_rabbit);

        for (i = 0; i < JCEntityRegistry.getDinosaurs().size(); i++)
        {
            addGrowthSerumRecipe(new ItemStack(JCItemRegistry.dino_steak, 1, i));
        }
    }

    private void addRecipe(ItemStack stack, Object... pars)
    {
        GameRegistry.addRecipe(stack, pars);

        getShapedRecipe(stack, pars);
        DinoPediaRegistry.addItemRecipe(stack, getShapedRecipe(stack, pars));
    }

    private ShapedRecipes getShapedRecipe(ItemStack stack, Object[] pars)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (pars[i] instanceof String[])
        {
            String[] astring = (String[]) pars[i++];

            for (String s1 : astring)
            {
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }
        else
        {
            while (pars[i] instanceof String)
            {
                String s2 = (String) pars[i++];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }

        HashMap<Character, ItemStack> hashmap;

        for (hashmap = Maps.newHashMap(); i < pars.length; i += 2)
        {
            Character character = (Character) pars[i];
            ItemStack characterStack = null;

            if (pars[i + 1] instanceof Item)
            {
                characterStack = new ItemStack((Item) pars[i + 1]);
            }
            else if (pars[i + 1] instanceof Block)
            {
                characterStack = new ItemStack((Block) pars[i + 1], 1, 32767);
            }
            else if (pars[i + 1] instanceof ItemStack)
            {
                characterStack = (ItemStack) pars[i + 1];
            }

            hashmap.put(character, characterStack);
        }

        ItemStack[] inputs = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1)
        {
            char character = s.charAt(i1);

            if (hashmap.containsKey(Character.valueOf(character)))
            {
                inputs[i1] = hashmap.get(Character.valueOf(character)).copy();
            }
            else
            {
                inputs[i1] = null;
            }
        }

        return new ShapedRecipes(j, k, inputs, stack);
    }

    private void addGrowthSerumRecipe(Item meat)
    {
        addGrowthSerumRecipe(new ItemStack(meat));
    }

    private void addGrowthSerumRecipe(ItemStack meat)
    {
        GameRegistry.addShapelessRecipe(new ItemStack(JCItemRegistry.growth_serum), Items.golden_carrot, JCItemRegistry.empty_syringe, Items.water_bucket, meat);
    }
}
