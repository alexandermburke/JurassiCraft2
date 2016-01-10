package org.jurassicraft.common.item;

import net.ilexiconn.bookwiki.server.item.BookWikiItem;
import net.ilexiconn.llibrary.common.content.IContentHandler;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.item.bones.ItemFossil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JCItemRegistry implements IContentHandler
{
    public static ItemPlasterAndBandage plaster_and_bandage;
    public static ItemDinosaurSpawnEgg spawn_egg;

    public static ItemDNA dna;
    public static ItemDinosaurEgg egg;
    public static ItemPaleoPad paleo_pad;
    public static ItemSoftTissue soft_tissue;

    public static ItemDinosaurMeat dino_meat;
    public static ItemDinosaurSteak dino_steak;

    public static ItemBluePrint blue_print;
    public static ItemPaddockSign paddock_sign;
    public static ItemJurassiCraftSign jc_sign;

    public static ItemAmber amber;
    public static ItemBasic petri_dish;
    public static ItemBasic empty_test_tube;

    public static ItemSyringe syringe;
    public static ItemEmptySyringe empty_syringe;

    public static ItemEntityRemover entityRemover;

    public static ItemStorageDisc storage_disc;
    public static ItemBasic dna_base;

    public static ItemCage cage_small;

    public static ItemPlantDNA plant_dna;

    public static ItemBasic sea_lamprey;

    public static ItemBasic iron_blades;
    public static ItemBasic iron_rod;
    public static ItemBasic disc_reader;
    public static ItemBasic laser;

    public static ItemBasic needle;

    public static ItemGrowthSerum growth_serum;

    public static ItemBasic plant_cells;
    public static ItemPlantCallus plant_callus;
    public static ItemBasic plant_cells_petri_dish;
    public static ItemHelicopter helicopter_spawner;
    public static ItemBasic tracker;

    public static ItemJCMusicDisc disc_jurassicraft_theme;
    public static ItemJCMusicDisc disc_troodons_and_raptors;
    public static ItemJCMusicDisc disc_dont_move_a_muscle;

    public static ItemActionFigure action_figure;

    public static ItemBasic amber_keychain;
    public static ItemBasic amber_cane;
    public static ItemBasic mr_dna_keychain;

    public static ItemBasic basic_circuit;
    public static ItemBasic advanced_circuit;

    public static ItemBasic iron_nugget;

    // Debug items
    public static Item dino_scanner;

    public static BookWikiItem book_wiki;

    public static Map<String, ItemFossil> fossils = new HashMap<String, ItemFossil>();
    public static Map<String, List<Dinosaur>> fossilDinosaurs = new HashMap<String, List<Dinosaur>>();

    // TODO more complex crafting components, eg circuit boards

    @Override
    public void init()
    {
        plaster_and_bandage = new ItemPlasterAndBandage();
        spawn_egg = new ItemDinosaurSpawnEgg();
        dna = new ItemDNA();
        egg = new ItemDinosaurEgg();
        paleo_pad = new ItemPaleoPad();
        dino_meat = new ItemDinosaurMeat();
        dino_steak = new ItemDinosaurSteak();
        blue_print = new ItemBluePrint();
        paddock_sign = new ItemPaddockSign();
        jc_sign = new ItemJurassiCraftSign();
        soft_tissue = new ItemSoftTissue();
        amber = new ItemAmber();
        petri_dish = new ItemBasic("Petri Dish", JCCreativeTabs.items);
        empty_test_tube = new ItemBasic("Empty Test Tube", JCCreativeTabs.items);
        syringe = new ItemSyringe();
        empty_syringe = new ItemEmptySyringe();
        entityRemover = new ItemEntityRemover();
        storage_disc = new ItemStorageDisc();
        disc_reader = new ItemBasic("Disc Reader", JCCreativeTabs.items);
        laser = new ItemBasic("Laser", JCCreativeTabs.items);
        dna_base = new ItemBasic("DNA Base Material", JCCreativeTabs.items);
        cage_small = new ItemCage();
        plant_dna = new ItemPlantDNA();
        sea_lamprey = new ItemBasic("Sea Lamprey", JCCreativeTabs.items);
        iron_blades = new ItemBasic("Iron Blades", JCCreativeTabs.items);
        iron_rod = new ItemBasic("Iron Rod", JCCreativeTabs.items);
        growth_serum = new ItemGrowthSerum();
        needle = new ItemBasic("Needle", JCCreativeTabs.items);
        plant_cells = new ItemBasic("Plant Cells", JCCreativeTabs.items);
        plant_callus = new ItemPlantCallus();
        plant_cells_petri_dish = new ItemBasic("Plant Cells Petri Dish", JCCreativeTabs.items);
        tracker = new ItemBasic("Tracker", JCCreativeTabs.items);
        action_figure = new ItemActionFigure();
        dino_scanner = new ItemDinoScanner();

        amber_cane = new ItemBasic("Amber Cane", JCCreativeTabs.merchandise);
        amber_cane.setFull3D();
        amber_cane.setMaxStackSize(1);
        amber_keychain = new ItemBasic("Amber Keychain", JCCreativeTabs.merchandise);
        mr_dna_keychain = new ItemBasic("Mr DNA Keychain", JCCreativeTabs.merchandise);

        helicopter_spawner = new ItemHelicopter();

        disc_jurassicraft_theme = new ItemJCMusicDisc("jurassicraft_theme");
        disc_troodons_and_raptors = new ItemJCMusicDisc("troodons_and_raptors");
        disc_dont_move_a_muscle = new ItemJCMusicDisc("dont_move_a_muscle");

        basic_circuit = new ItemBasic("Basic Circuit", JCCreativeTabs.items);
        advanced_circuit = new ItemBasic("Advanced Circuit", JCCreativeTabs.items);

        iron_nugget = new ItemBasic("Iron Nugget", JCCreativeTabs.items);

        for (Dinosaur dinosaur : JCEntityRegistry.getRegisteredDinosaurs())
        {
            String[] boneTypes = dinosaur.getBones();

            for (String boneType : boneTypes)
            {
                if (!fossils.containsKey(boneType))
                {
                    ItemFossil fossil = new ItemFossil(boneType);
                    fossils.put(boneType, fossil);
                    registerItem(fossil, boneType);
                }

                List<Dinosaur> dinosaursWithType = fossilDinosaurs.get(boneType);

                if (dinosaursWithType == null)
                {
                    dinosaursWithType = new ArrayList<Dinosaur>();
                }

                dinosaursWithType.add(dinosaur);

                fossilDinosaurs.put(boneType, dinosaursWithType);
            }
        }
    }

    @Override
    public void gameRegistry() throws Exception
    {
        registerItem(amber, "Amber");
        registerItem(sea_lamprey, "Sea Lamprey");
        registerItem(plaster_and_bandage, "Plaster And Bandage");
        registerItem(empty_test_tube, "Empty Test Tube");
        registerItem(empty_syringe, "Empty Syringe");
        registerItem(growth_serum, "Growth Serum");
        registerItem(storage_disc, "Storage Disc");
        registerItem(disc_reader, "Disc Reader");
        registerItem(laser, "Laser");
        registerItem(dna_base, "DNA Base Material");
        registerItem(petri_dish, "Petri Dish");
        registerItem(plant_cells_petri_dish, "Plant Cells Petri Dish");
        registerItem(blue_print, "Blue Print");
        registerItem(paddock_sign, "Paddock Sign");
        registerItem(cage_small, "Cage Small");
        // registerItem(jc_sign, "JurassiCraft Sign");
        registerItem(spawn_egg, "Dino Spawn Egg");
        registerItem(dna, "DNA");
        registerItem(egg, "Dino Egg");
        registerItem(paleo_pad, "Paleo Pad");
        registerItem(soft_tissue, "Soft Tissue");
        registerItem(syringe, "Syringe");
        registerItem(plant_dna, "Plant DNA");
        registerItem(iron_blades, "Iron Blades");
        registerItem(iron_rod, "Iron Rod");
        registerItem(needle, "Needle");
        registerItem(plant_cells, "Plant Cells");
        registerItem(plant_callus, "Plant Callus");
        registerItem(tracker, "Tracker");
        registerItem(basic_circuit, "Basic Circuit");
        registerItem(advanced_circuit, "Advanced Circuit");
        registerItemOreDict(iron_nugget, "Iron Nugget", "nuggetIron");

        registerItem(dino_meat, "Dinosaur Meat");
        registerItem(dino_steak, "Dinosaur Steak");

        registerItem(helicopter_spawner, "Helicopter Spawner");

        registerItem(disc_jurassicraft_theme, "Disc JurassiCraft Theme");
        registerItem(disc_troodons_and_raptors, "Disc Troodons And Raptors");
        registerItem(disc_dont_move_a_muscle, "Disc Don't Move A Muscle");

        registerItem(amber_cane, "Amber Cane");
        registerItem(amber_keychain, "Amber Keychain");
        registerItem(mr_dna_keychain, "Mr DNA Keychain");

        registerItem(action_figure, "Action Figure");

        registerItem(dino_scanner, "Dino Scanner");

        // registerItem(entityRemover, "Entity Remover");

        // for (int i = 0; i < JCEntityRegistry.getDinosaurs().size(); i++)
        // {
        // EcoAPI.registerEcologicalRoleFoodItem(EcoAPI.carnivore, new ItemStack(JCItemRegistry.dino_meat, 1, i));
        // EcoAPI.registerEcologicalRoleFoodItem(EcoAPI.carnivore, new ItemStack(JCItemRegistry.dino_steak, 1, i));
        // EcoAPI.registerEntityClassDropItems(JCEntityRegistry.getDinosaurs().get(i).getDinosaurClass(), new ItemStack[]{new ItemStack(JCItemRegistry.dino_meat, 1, i), new ItemStack(JCItemRegistry.dino_steak, 1, i)});
        // }
    }

    public void registerItemOreDict(Item item, String name, String oreDict)
    {
        registerItem(item, name);
        OreDictionary.registerOre(oreDict, item);
    }

    public void registerItem(Item item, String name)
    {
        GameRegistry.registerItem(item, name.toLowerCase().replaceAll(" ", "_").replaceAll("'", ""));
    }
}
