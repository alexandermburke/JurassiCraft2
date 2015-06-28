package net.timeless.jurassicraft.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.dinosaur.Dinosaur;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;

public class JCItemRegistry
{
    public static ItemPlasterAndBandage plaster_and_bandage;
    public static ItemDinosaurSpawnEgg spawn_egg;
    public static ItemFossil fossil;

    public static ItemDNA dna;
    public static ItemDinosaurEgg egg;
    public static ItemPaleoPad paleo_pad;

    public static void preInitCommon()
    {
        plaster_and_bandage = new ItemPlasterAndBandage();
        registerItem(plaster_and_bandage);

        spawn_egg = new ItemDinosaurSpawnEgg();
        registerItem(spawn_egg);

        fossil = new ItemFossil();
        registerItem(fossil);

        dna = new ItemDNA();
        registerItem(dna);

        egg = new ItemDinosaurEgg();
        registerItem(egg);

        paleo_pad = new ItemPaleoPad();
        registerItem(paleo_pad);

    }

    public static void initCommon()
    {

    }

    public static void postInitCommon()
    {

    }

    public static void preInitClientOnly()
    {
        registerDinosaurRelatedMultiItemVariantNames(spawn_egg, "spawn_egg");

        registerDinosaurRelatedMultiItemVariantNames(fossil, "fossil");

        registerDinosaurRelatedMultiItemVariantNames(dna, "dna");

        registerDinosaurRelatedMultiItemVariantNames(egg, "egg");
    }

    public static void initClientOnly()
    {
        registerItemRender(plaster_and_bandage);

        registerDinosaurRelatedMultiItemRender(spawn_egg);

        registerDinosaurRelatedMultiItemRender(fossil);

        registerDinosaurRelatedMultiItemRender(dna);

        registerDinosaurRelatedMultiItemRender(egg);

        registerItemRender(paleo_pad);
    }

    public static void postInitClientOnly()
    {

    }

    /**
     * Registers a simple item.
     */
    private static void registerItem(Item item)
    {
        String itemUnlocalizedName = item.getUnlocalizedName().substring(5);
        GameRegistry.registerItem(item, itemUnlocalizedName);
    }

    /**
     * Registers a simple item render.
     */
    private static void registerItemRender(Item item)
    {
        String itemUnlocalizedName = item.getUnlocalizedName().substring(5);
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(JurassiCraft.prependModID(itemUnlocalizedName), "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, itemModelResourceLocation);
    }

    /**
     * Registers the variant names of any dinosaur related item.
     */
    private static void registerDinosaurRelatedMultiItemVariantNames(Item item, String folder)
    {
        String itemUnlocalizedName = item.getUnlocalizedName().substring(5);
        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            if (dino.shouldRegister())
            {
                String dinoName = dino.getName().toLowerCase().replaceAll(" ", "_");
                ModelBakery.addVariantName(item, JurassiCraft.prependModIDandFolder(folder, itemUnlocalizedName + "_" + dinoName));
            }
        }
    }

    /**
     * Registers any dinosaur related item render.
     */
    private static void registerDinosaurRelatedMultiItemRender(Item item)
    {
        String itemUnlocalizedName = item.getUnlocalizedName().substring(5);
        for (int i = 0; i < JCEntityRegistry.getDinosaurs().size(); i++)
        {
            Dinosaur dino = JCEntityRegistry.getDinosaurs().get(i);
            if (dino.shouldRegister())
            {
                String itemModelName = itemUnlocalizedName + "_" + dino.getName().toLowerCase();
                ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(JurassiCraft.prependModID(itemModelName), "inventory");
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, i, itemModelResourceLocation);
            }
        }
    }
}
