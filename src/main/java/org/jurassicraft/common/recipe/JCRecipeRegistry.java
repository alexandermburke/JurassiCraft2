package org.jurassicraft.common.recipe;

import net.ilexiconn.llibrary.common.content.IContentHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.item.JCItemRegistry;

public class JCRecipeRegistry implements IContentHandler
{
    @Override
    public void init()
    {

    }

    @Override
    public void gameRegistry() throws Exception
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

            GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.stairs[i], 4), "w  ", "ww ", "www", 'w', block);

            GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.stairs[i], 4), "  w", " ww", "www", 'w', block);

            GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.slabs[i], 6), "www", 'w', block);

            i++;
        }

        GameRegistry.addSmelting(new ItemStack(JCBlockRegistry.gypsum_cobblestone), new ItemStack(JCBlockRegistry.gypsum_stone), 1.5F);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.iron_blades), "I I", " S ", "I I", 'I', Items.iron_ingot, 'S', Items.stick);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.iron_rod), "ISI", "ISI", "ISI", 'I', Items.iron_ingot, 'S', Items.stick);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.fossil_grinder), "lBl", "rRr", "IPI", 'I', Items.iron_ingot, 'R', JCItemRegistry.iron_rod, 'B', JCItemRegistry.iron_blades, 'r', Items.redstone, 'l', new ItemStack(Items.dye, 1, 4), 'P', Blocks.piston);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.plaster_and_bandage, 9), "PPP", "PWP", "PPP", 'P', Items.paper, 'W', Blocks.wool);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.cage_small, 1), "III", "BBB", "III", 'I', Items.iron_ingot, 'B', Blocks.iron_bars);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.cage_small, 1, 1), "III", "GBG", "III", 'I', Items.iron_ingot, 'G', Blocks.glass_pane, 'B', Items.water_bucket);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.gypsum_bricks, 4), "SS", "SS", 'S', JCBlockRegistry.gypsum_stone);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.cleaning_station), "iii", "RGR", "IBI", 'i', Items.iron_ingot, 'B', Items.bucket, 'G', Blocks.glass_pane, 'R', Items.redstone, 'I', Blocks.iron_block);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.reinforced_stone, 8), "PPP", "PWP", "PPP", 'P', Blocks.stone, 'W', Items.iron_ingot);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.reinforced_bricks, 8), "PPP", "PWP", "PPP", 'P', Blocks.stonebrick, 'W', Items.iron_ingot);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.reinforced_bricks, 4), "SS", "SS", 'S', JCBlockRegistry.reinforced_stone);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.empty_test_tube), "I", "G", "G", 'G', Blocks.glass_pane, 'I', Items.iron_ingot);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.petri_dish), "G G", "GGG", 'G', Blocks.glass_pane);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.blue_print), "BBB", "BPB", "BBB", 'B', new ItemStack(Items.dye, 1, 4), 'P', Items.paper);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.empty_syringe), "  N", "IG ", "II ", 'G', Blocks.glass_pane, 'I', Items.iron_ingot, 'N', JCItemRegistry.needle);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.empty_syringe), "N  ", " GI", " II", 'G', Blocks.glass_pane, 'I', Items.iron_ingot, 'N', JCItemRegistry.needle);

        GameRegistry.addSmelting(new ItemStack(Items.potionitem, 1, 0), new ItemStack(JCItemRegistry.dna_base), 1.0F);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.needle), "GIG", "GIG", " I ", 'G', Blocks.glass_pane, 'I', Items.iron_ingot);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.dna_extractor), "III", "INI", "RSR", 'S', JCBlockRegistry.dna_sequencer, 'I', Items.iron_ingot, 'R', Items.redstone, 'N', JCItemRegistry.needle);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.dna_sequencer), "RDR", "GNG", "III", 'G', Items.gold_ingot, 'I', Items.iron_ingot, 'N', JCItemRegistry.needle, 'D', JCItemRegistry.disc_reader, 'R', Items.redstone);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.dna_synthesizer), "GDG", "RBR", "ITI", 'G', Items.gold_ingot, 'I', Items.iron_ingot, 'B', Items.bucket, 'R', Items.redstone, 'D', JCItemRegistry.disc_reader);

        for (i = 0; i < 16; i++)
        {
            GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.cultivate_bottom, 1, i), "GGG", "GWG", "III", 'G', new ItemStack(Blocks.stained_glass_pane, 1, i), 'W', Items.water_bucket, 'I', Items.iron_ingot);
        }

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.embryonic_machine), "GIG", "GIG", "III", 'G', Blocks.glass_pane, 'I', Items.iron_ingot);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.embryo_calcification_machine), "GIG", "GSG", "III", 'G', Blocks.glass_pane, 'I', Items.iron_ingot, 'S', JCItemRegistry.needle);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.incubator), "GGG", "RRR", "III", 'I', Items.iron_ingot, 'R', Items.redstone, 'G', Blocks.glass);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.dna_combinator), "IDI", "GRG", "III", 'G', Blocks.glass, 'I', Items.iron_ingot, 'R', Items.redstone, 'D', JCItemRegistry.disc_reader);

        GameRegistry.addRecipe(new ItemStack(JCBlockRegistry.dna_hybridizer), "IRI", "GIG", "IRI", 'G', Blocks.glass, 'I', Items.iron_ingot, 'R', Items.redstone);

        GameRegistry.addShapelessRecipe(new ItemStack(JCItemRegistry.plant_cells_petri_dish), JCItemRegistry.plant_cells, JCItemRegistry.petri_dish);

        GameRegistry.addRecipe(new ItemStack(JCItemRegistry.iron_nugget, 9), "i", 'i', Items.iron_ingot);
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(JCItemRegistry.basic_circuit, 2), "nuggetIron", "nuggetIron", "nuggetIron", "nuggetIron", Items.gold_nugget, Items.gold_nugget, Items.gold_nugget, Items.gold_nugget, Items.redstone));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(JCItemRegistry.advanced_circuit, 1), JCItemRegistry.basic_circuit, JCItemRegistry.basic_circuit, "nuggetIron", Items.gold_nugget, Items.redstone));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(JCItemRegistry.storage_disc), " I ", "ICI", " I ", 'I', "nuggetIron", 'C', JCItemRegistry.basic_circuit));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(JCItemRegistry.laser), "IDI", "ICI", "IRI", 'I', "nuggetIron", 'C', JCItemRegistry.basic_circuit, 'D', Items.diamond, 'R', Items.redstone));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(JCItemRegistry.disc_reader), "CLI", "i  ", "iib", 'i', "nuggetIron", 'C', JCItemRegistry.advanced_circuit, 'L', JCItemRegistry.laser, 'I', Items.iron_ingot, 'b', Blocks.stone_button));

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

    private void addGrowthSerumRecipe(Item meat)
    {
        addGrowthSerumRecipe(new ItemStack(meat));
    }

    private void addGrowthSerumRecipe(ItemStack meat)
    {
        GameRegistry.addShapelessRecipe(new ItemStack(JCItemRegistry.growth_serum), Items.golden_carrot, JCItemRegistry.empty_syringe, Items.water_bucket, meat);
    }
}
