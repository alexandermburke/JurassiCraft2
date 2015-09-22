package org.jurassicraft.client.render;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.gui.app.GuiAppRegistry;
import org.jurassicraft.client.render.block.SpecialRendererDNAExtractor;
import org.jurassicraft.client.render.entity.RenderBluePrint;
import org.jurassicraft.client.render.entity.RenderCageSmall;
import org.jurassicraft.client.render.entity.RenderHelicopter;
import org.jurassicraft.client.render.entity.RenderJurassiCraftSign;
import org.jurassicraft.client.render.renderdef.*;
import org.jurassicraft.common.block.BlockEncasedFossil;
import org.jurassicraft.common.block.BlockFossil;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.block.tree.EnumType;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.entity.item.EntityBluePrint;
import org.jurassicraft.common.entity.item.EntityCageSmall;
import org.jurassicraft.common.entity.item.EntityJurassiCraftSign;
import org.jurassicraft.common.item.JCItemRegistry;
import org.jurassicraft.common.plant.JCPlantRegistry;
import org.jurassicraft.common.plant.Plant;
import org.jurassicraft.common.tileentity.TileDNAExtractor;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;

import java.util.Map;

@SideOnly(Side.CLIENT)
public class JCRenderingRegistry
{
    private static Map<Dinosaur, RenderDinosaurDefinition> renderDefs = Maps.newHashMap();

    public void preInit()
    {
        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            String dinoName = dino.getName(0).toLowerCase().replaceAll(" ", "_");

            ModelBakery.addVariantName(JCItemRegistry.skull, "jurassicraft:fossil/skull_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.dna, "jurassicraft:dna/dna_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.egg, "jurassicraft:egg/egg_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.dino_meat, "jurassicraft:meat/meat_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.dino_steak, "jurassicraft:meat/steak_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.soft_tissue, "jurassicraft:soft_tissue/soft_tissue_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.syringe, "jurassicraft:syringe/syringe_" + dinoName);
        }

        for (Plant plant : JCPlantRegistry.getPlants())
        {
            String name = plant.getName().toLowerCase().replaceAll(" ", "_");

            ModelBakery.addVariantName(JCItemRegistry.plant_dna, "jurassicraft:dna/plants/dna_" + name);
        }

        for (EnumDyeColor color : EnumDyeColor.values())
        {
            ModelBakery.addVariantName(Item.getItemFromBlock(JCBlockRegistry.cultivate_bottom), "jurassicraft:cultivate/cultivate_bottom_" + color.getName().toLowerCase());
        }

        ModelBakery.addVariantName(JCItemRegistry.amber, "jurassicraft:amber_aphid");
        ModelBakery.addVariantName(JCItemRegistry.amber, "jurassicraft:amber_mosquito");

        ModelBakery.addVariantName(JCItemRegistry.cage_small, "jurassicraft:cage_small");
        ModelBakery.addVariantName(JCItemRegistry.cage_small, "jurassicraft:cage_small_marine");
    }

    public void init()
    {
        registerRenderDef(new RenderDefAchillobator());
        registerRenderDef(new RenderDefAnkylosaurus());
        registerRenderDef(new RenderDefBrachiosaurus());
        registerRenderDef(new RenderDefCarnotaurus());
        registerRenderDef(new RenderDefCoelacanth());
        registerRenderDef(new RenderDefCompsognathus());
        registerRenderDef(new RenderDefDilophosaurus());
        registerRenderDef(new RenderDefDunkleosteus());
        registerRenderDef(new RenderDefGallimimus());
        registerRenderDef(new RenderDefGiganotosaurus());
        registerRenderDef(new RenderDefHypsilophodon());
        registerRenderDef(new RenderDefIndominusRex());
        registerRenderDef(new RenderDefMajungasaurus());
        registerRenderDef(new RenderDefParasaurolophus());
        registerRenderDef(new RenderDefPteranodon());
        registerRenderDef(new RenderDefRugops());
        registerRenderDef(new RenderDefSegisaurus());
        registerRenderDef(new RenderDefSpinosaurus());
        registerRenderDef(new RenderDefStegosaurus());
        registerRenderDef(new RenderDefTriceratops());
        registerRenderDef(new RenderDefTyrannosaurusRex());
        registerRenderDef(new RenderDefVelociraptor());
        registerRenderDef(new RenderDefDodo());
        registerRenderDef(new RenderDefLeptictidium());
        registerRenderDef(new RenderDefMicroceratus());
        registerRenderDef(new RenderDefOviraptor());
        registerRenderDef(new RenderDefApatosaurus());
        registerRenderDef(new RenderDefOthnielia());
        registerRenderDef(new RenderDefDimorphodon());
        registerRenderDef(new RenderDefTylosaurus());
        registerRenderDef(new RenderDefLudodactylus());
        registerRenderDef(new RenderDefProtoceratops());
        registerRenderDef(new RenderDefTropeognathus());
        registerRenderDef(new RenderDefLeaellynasaura());
        registerRenderDef(new RenderDefHerrerasaurus());
        registerRenderDef(new RenderDefVelociraptorBlue());
        registerRenderDef(new RenderDefVelociraptorCharlie());
        registerRenderDef(new RenderDefVelociraptorDelta());
        registerRenderDef(new RenderDefVelociraptorEcho());
        registerRenderDef(new RenderDefTherizinosaurus());
        registerRenderDef(new RenderDefMegapiranha());
        registerRenderDef(new RenderDefBaryonyx());
        registerRenderDef(new RenderDefCearadactylus());

        GuiAppRegistry.register();

        // Blocks
        ItemModelMesher modelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

        int i = 0;

        for (BlockEncasedFossil fossil : JCBlockRegistry.encased_fossils)
        {
            this.registerBlockRenderer(modelMesher, fossil, "encased_fossil_" + i, "inventory");

            i++;
        }

        i = 0;

        for (BlockFossil fossil : JCBlockRegistry.fossils)
        {
            this.registerBlockRenderer(modelMesher, fossil, "fossil_block_" + i, "inventory");

            i++;
        }

        for (i = 0; i < JCBlockRegistry.numOfTrees; i++)
        {
            String name = EnumType.getMetaLookup()[i].getName();
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.leaves[i], name + "_leaves", "inventory");
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.saplings[i], name + "_sapling", "inventory");
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.planks[i], name + "_planks", "inventory");
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.woods[i], name + "_log", "inventory");
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.stairs[i], name + "_stairs", "inventory");
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.slabs[i], name + "_slab", "inventory");
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.doubleSlabs[i], name + "_double_sab", "inventory");
        }

        for (EnumDyeColor color : EnumDyeColor.values())
        {
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.cultivate_bottom, color.ordinal(), "cultivate/cultivate_bottom_" + color.getName().toLowerCase(), "inventory");
        }

        this.registerBlockRenderer(modelMesher, JCBlockRegistry.scaly_tree_fern, "scaly_tree_fern", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.small_royal_fern, "small_royal_fern", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.small_chain_fern, "small_chain_fern", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.small_cycad, "small_cycad", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.bennettitalean_cycadeoidea, "bennettitalean_cycadeoidea", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.cry_pansy, "cry_pansy", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.cycad_zamites, "cycad_zamites", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.dicksonia, "dicksonia", "inventory");

        this.registerBlockRenderer(modelMesher, JCBlockRegistry.reinforced_stone, "reinforced_stone", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.reinforced_bricks, "reinforced_bricks", "inventory");

        this.registerBlockRenderer(modelMesher, JCBlockRegistry.cultivate_bottom, "cultivate_bottom", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.cultivate_top, "cultivate_top", "inventory");

        this.registerBlockRenderer(modelMesher, JCBlockRegistry.amber_ore, "amber_ore", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.ice_shard, "ice_shard", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.cleaning_station, "cleaning_station", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.fossil_grinder, "fossil_grinder", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.dna_sequencer, "dna_sequencer", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.dna_synthesizer, "dna_synthesizer", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.embryonic_machine, "embryonic_machine", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.embryo_calcification_machine, "embryo_calcification_machine", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.incubator, "incubator", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.dna_extractor, "dna_extractor", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.gypsum_stone, "gypsum_stone", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.gypsum_cobblestone, "gypsum_cobblestone", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.gypsum_bricks, "gypsum_bricks", "inventory");
    }

    public void postInit()
    {
        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
            RenderingRegistry.registerEntityRenderingHandler(dino.getDinosaurClass(), renderDefs.get(dino).getRenderer(0));

        RenderingRegistry.registerEntityRenderingHandler(EntityCageSmall.class, new RenderCageSmall());
        RenderingRegistry.registerEntityRenderingHandler(EntityBluePrint.class, new RenderBluePrint());
        RenderingRegistry.registerEntityRenderingHandler(EntityJurassiCraftSign.class, new RenderJurassiCraftSign());
        RenderingRegistry.registerEntityRenderingHandler(EntityHelicopterBase.class, new RenderHelicopter());

        ClientRegistry.bindTileEntitySpecialRenderer(TileDNAExtractor.class, new SpecialRendererDNAExtractor());

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        ItemModelMesher modelMesher = renderItem.getItemModelMesher();

        // Items
        this.registerItemRenderer(modelMesher, JCItemRegistry.tracker, "tracker", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.plant_cells_petri_dish, "plant_cells_petri_dish", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.plant_cells, "plant_cells", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.plant_callus, "plant_callus", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.needle, "needle", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.growth_serum, "growth_serum", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.iron_rod, "iron_rod", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.iron_blades, "iron_blades", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.cage_small, 0, "cage_small", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.cage_small, 1, "cage_small_marine", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.petri_dish, "petri_dish", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.amber, "amber", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.plaster_and_bandage, "plaster_and_bandage", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.spawn_egg, "dino_spawn_egg", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.paleo_pad, "paleo_pad", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.blue_print, "blue_print", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.jc_sign, "jurassicraft_sign", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.empty_test_tube, "empty_test_tube", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.empty_syringe, "empty_syringe", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.entityRemover, "entity_remover", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.storage_disc, "storage_disc", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.dna_base, "dna_base_material", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.sea_lamprey, "sea_lamprey", "inventory");

        this.registerItemRenderer(modelMesher, JCItemRegistry.amber, 0, "amber_mosquito", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.amber, 1, "amber_aphid", "inventory");

        int meta = 0;

        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            String dinoName = dino.getName(0).toLowerCase().replaceAll(" ", "_");

            this.registerItemRenderer(modelMesher, JCItemRegistry.dna, meta, "dna/dna_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.skull, meta, "fossil/skull_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.egg, meta, "egg/egg_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.dino_meat, meta, "meat/meat_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.dino_steak, meta, "meat/steak_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.soft_tissue, meta, "soft_tissue/soft_tissue_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.syringe, meta, "syringe/syringe_" + dinoName, "inventory");

            meta++;
        }

        meta = 0;

        for (Plant plant : JCPlantRegistry.getPlants())
        {
            String name = plant.getName().toLowerCase().replaceAll(" ", "_");

            this.registerItemRenderer(modelMesher, JCItemRegistry.plant_dna, meta, "dna/plants/dna_" + name, "inventory");

            meta++;
        }
    }

    /**
     * Registers an item renderer
     */
    public void registerItemRenderer(ItemModelMesher itemModelMesher, Item item, final String path, final String type)
    {
        itemModelMesher.register(item, new ItemMeshDefinition()
        {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation(JurassiCraft.MODID + ":" + path, type);
            }
        });
    }

    /**
     * Registers an item renderer with metadata
     */
    public void registerItemRenderer(ItemModelMesher itemModelMesher, Item item, int meta, String path, String type)
    {
        itemModelMesher.register(item, meta, new ModelResourceLocation(JurassiCraft.MODID + ":" + path, type));
    }

    /**
     * Registers an block renderer with metadata
     */
    public void registerBlockRenderer(ItemModelMesher itemModelMesher, Block block, int meta, String path, String type)
    {
        itemModelMesher.register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(JurassiCraft.MODID + ":" + path, type));
    }

    /**
     * Registers a block renderer
     */
    public void registerBlockRenderer(ItemModelMesher itemModelMesher, Block block, final String path, final String type)
    {
        itemModelMesher.register(Item.getItemFromBlock(block), new ItemMeshDefinition()
        {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation(JurassiCraft.MODID + ":" + path, type);
            }
        });
    }

    private void registerRenderDef(RenderDinosaurDefinition renderDef)
    {
        renderDefs.put(renderDef.getDinosaur(), renderDef);
    }

    public RenderDinosaurDefinition getRenderDef(Dinosaur dino)
    {
        return renderDefs.get(dino);
    }
}
