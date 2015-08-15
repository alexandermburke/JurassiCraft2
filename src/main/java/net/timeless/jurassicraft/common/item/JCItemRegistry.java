package net.timeless.jurassicraft.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.reuxertz.ecoapi.EcoAPI;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;

public class JCItemRegistry
{
    public static ItemPlasterAndBandage plaster_and_bandage;
    public static ItemDinosaurSpawnEgg spawn_egg;
    public static ItemSkull skull;

    public static ItemDNA dna;
    public static ItemDinosaurEgg egg;
    public static ItemPaleoTab paleo_tab;
    public static ItemSoftTissue soft_tissue;

    public static ItemDinosaurMeat dino_meat;
    public static ItemDinosaurSteak dino_steak;

    public static ItemBluePrint blue_print;
    public static ItemJurassiCraftSign jc_sign;

    public static ItemBasic amber;
    public static ItemBasic petri_dish;
    public static ItemBasic empty_test_tube;

    public static ItemSyringe syringe;
    public static ItemBasic empty_syringe;

    public static ItemEntityRemover entityRemover;

    public static ItemBasic storage_disc;
    public static ItemBasic dna_base;

    public static ItemCage cage_small;

    public void register()
    {
        plaster_and_bandage = new ItemPlasterAndBandage();
        spawn_egg = new ItemDinosaurSpawnEgg();
        skull = new ItemSkull();
        dna = new ItemDNA();
        egg = new ItemDinosaurEgg();
        paleo_tab = new ItemPaleoTab();
        dino_meat = new ItemDinosaurMeat();
        dino_steak = new ItemDinosaurSteak();
        blue_print = new ItemBluePrint();
        jc_sign = new ItemJurassiCraftSign();
        soft_tissue = new ItemSoftTissue();
        amber = new ItemBasic("Amber", JCCreativeTabs.items);
        petri_dish = new ItemBasic("Petri Dish", JCCreativeTabs.items);
        empty_test_tube = new ItemBasic("Empty Test Tube", JCCreativeTabs.dna);
        syringe = new ItemSyringe();
        empty_syringe = new ItemBasic("Empty Syringe", JCCreativeTabs.dna);
        entityRemover = new ItemEntityRemover();
        storage_disc = new ItemBasic("Storage Disc", JCCreativeTabs.items);
        dna_base = new ItemBasic("DNA Base Material", JCCreativeTabs.items);
        cage_small = new ItemCage();

        registerItem(blue_print, "Blue Print");
        registerItem(jc_sign, "JurassiCraft Sign");
        registerItem(plaster_and_bandage, "Plaster And Bandage");
        registerItem(spawn_egg, "Dino Spawn Egg");
        registerItem(skull, "Skull");
        registerItem(dna, "DNA");
        registerItem(egg, "Dino Egg");
        registerItem(paleo_tab, "Paleo Tab");
        registerItem(soft_tissue, "Soft Tissue");
        registerItem(amber, "Amber");
        registerItem(petri_dish, "Petri Dish");
        registerItem(empty_test_tube, "Empty Test Tube");
        registerItem(syringe, "Syringe");
        registerItem(empty_syringe, "Empty Syringe");

        registerItem(dino_meat, "Dinosaur Meat");
        registerItem(dino_steak, "Dinosaur Steak");
        registerItem(entityRemover, "Entity Remover");
        registerItem(storage_disc, "Storage Disc");
        registerItem(dna_base, "DNA Base Material");
        registerItem(cage_small, "Cage Small");

        for (int i = 0; i < JCEntityRegistry.getDinosaurs().size(); i++)
        {
            EcoAPI.registerEcologicalRoleFoodItem(EcoAPI.carnivore, new ItemStack(JCItemRegistry.dino_meat, 1, i));
            EcoAPI.registerEcologicalRoleFoodItem(EcoAPI.carnivore, new ItemStack(JCItemRegistry.dino_steak, 1, i));
            EcoAPI.registerEntityClassDropItems(JCEntityRegistry.getDinosaurs().get(i).getDinosaurClass(), new ItemStack[] { new ItemStack(JCItemRegistry.dino_meat, 1, i), new ItemStack(JCItemRegistry.dino_steak, 1, i) });
        }
    }

    public void registerItem(Item item, String name)
    {
        GameRegistry.registerItem(item, name.toLowerCase().replaceAll(" ", "_"));
    }
}
