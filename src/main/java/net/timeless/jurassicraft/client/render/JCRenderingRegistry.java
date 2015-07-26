package net.timeless.jurassicraft.client.render;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefAchillobator;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefAnkylosaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefBrachiosaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefCarnotaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefCoelacanth;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefCompsognathus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefDilophosaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefDodo;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefDunkleosteus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefGallimimus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefGiganotosaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefHypsilophodon;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefIndominusRex;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefMajungasaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefParasaurolophus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefPteranodon;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefRugops;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefSegisaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefSpinosaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefStegosaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefTriceratops;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefTyrannosaurusRex;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefVelociraptor;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDinosaurDefinition;
import net.timeless.jurassicraft.client.render.entity.RenderBluePrint;
import net.timeless.jurassicraft.client.render.entity.RenderJurassiCraftSign;
import net.timeless.jurassicraft.common.block.BlockEncasedFossil;
import net.timeless.jurassicraft.common.block.BlockFossil;
import net.timeless.jurassicraft.common.block.JCBlockRegistry;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.entity.item.EntityBluePrint;
import net.timeless.jurassicraft.common.entity.item.EntityJurassiCraftSign;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

import com.google.common.collect.Maps;

@SideOnly(Side.CLIENT)
public class JCRenderingRegistry
{
    private static Map<Dinosaur, RenderDinosaurDefinition> renderDefs = Maps.newHashMap();

    public void preInit()
    {
        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            String dinoName = dino.getName().toLowerCase().replaceAll(" ", "_");

            ModelBakery.addVariantName(JCItemRegistry.skull, "jurassicraft:fossil/skull_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.dna, "jurassicraft:dna/dna_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.egg, "jurassicraft:egg/egg_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.dino_meat, "jurassicraft:meat/meat_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.dino_steak, "jurassicraft:meat/steak_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.soft_tissue, "jurassicraft:soft_tissue/soft_tissue_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.syringe, "jurassicraft:syringe/syringe_" + dinoName);
        }

        for (int i = 0; i < 2; i++) //This adds the variant names... because mc is evil
        {
            ModelBakery.addVariantName(Item.getItemFromBlock(JCBlockRegistry.jc_log), "jurassicraft:jc_log_" + i);
        }
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

        for (i = 0; i < 2; i++)
        {
            this.registerBlockRenderer(modelMesher, JCBlockRegistry.jc_log, i, "jc_log_" + i, "inventory"); //Here i register one for each type with the metadata as a parmaeter
        }

        this.registerBlockRenderer(modelMesher, JCBlockRegistry.amber_ore, "amber_ore", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.cleaning_station, "cleaning_station", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.fossil_grinder, "fossil_grinder", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.dna_sequencer, "dna_sequencer", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.dna_synthesizer, "dna_synthesizer", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.embryonic_machine, "embryonic_machine", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.embryo_insemination_machine, "embryo_insemination_machine", "inventory");
    }

    public void postInit()
    {
        List<Dinosaur> dinosaurs = JCEntityRegistry.getDinosaurs();

        Map<Dinosaur, Integer> ids = new HashMap<Dinosaur, Integer>();

        int id = 0;

        for (Dinosaur dino : dinosaurs)
        {
            ids.put(dino, id);

            id++;
        }

        Collections.sort(dinosaurs);

        for (Dinosaur dino : dinosaurs)
            RenderingRegistry.registerEntityRenderingHandler(dino.getDinosaurClass(), renderDefs.get(dino).getRenderer());

        RenderingRegistry.registerEntityRenderingHandler(EntityBluePrint.class, new RenderBluePrint());
        RenderingRegistry.registerEntityRenderingHandler(EntityJurassiCraftSign.class, new RenderJurassiCraftSign());

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        ItemModelMesher modelMesher = renderItem.getItemModelMesher();

        // Items
        this.registerItemRenderer(modelMesher, JCItemRegistry.petri_dish, "petri_dish", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.amber, "amber", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.plaster_and_bandage, "plaster_and_bandage", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.spawn_egg, "dino_spawn_egg", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.paleo_pad, "paleo_pad", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.blue_print, "blue_print", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.jc_sign, "jurassicraft_sign", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.empty_test_tube, "empty_test_tube", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.empty_syringe, "empty_syringe", "inventory");

        int meta = 0;

        for (Dinosaur dino : dinosaurs)
        {
            String dinoName = dino.getName().toLowerCase().replaceAll(" ", "_");

            this.registerItemRenderer(modelMesher, JCItemRegistry.dna, meta, "dna/dna_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.skull, meta, "fossil/skull_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.egg, meta, "egg/egg_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.dino_meat, meta, "meat/meat_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.dino_steak, meta, "meat/steak_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.soft_tissue, meta, "soft_tissue/soft_tissue_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.syringe, meta, "syringe/syringe_" + dinoName, "inventory");

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
                return new ModelResourceLocation(JurassiCraft.modid + ":" + path, type);
            }
        });
    }

    /**
     * Registers an item renderer with metadata
     */
    public void registerItemRenderer(ItemModelMesher itemModelMesher, Item item, int meta, String path, String type)
    {
        itemModelMesher.register(item, meta, new ModelResourceLocation(JurassiCraft.modid + ":" + path, type));
    }

    /**
     * Registers an block renderer with metadata
     */
    public void registerBlockRenderer(ItemModelMesher itemModelMesher, Block block, int meta, String path, String type)
    {
        itemModelMesher.register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(JurassiCraft.modid + ":" + path, type));
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
                return new ModelResourceLocation(JurassiCraft.modid + ":" + path, type);
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
