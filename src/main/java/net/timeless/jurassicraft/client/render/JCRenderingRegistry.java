package net.timeless.jurassicraft.client.render;

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
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.block.JCBlockRegistry;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefAchillobator;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefAnklyosaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefCarnotaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefCompsognathus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefDilophosaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefGallimimus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefGiganotosaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefIndominusRex;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefMajungasaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefParasaurolophus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefPteranodon;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefRugops;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefSegisaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefSpinosaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefStegosaurus;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefTyrannosaurusRex;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDefVelociraptor;
import net.timeless.jurassicraft.client.dinosaur.renderdef.RenderDinosaurDefinition;
import net.timeless.jurassicraft.client.render.entity.RenderDinosaur;
import net.timeless.jurassicraft.client.render.entity.RenderDinosaurMultilayer;
import net.timeless.jurassicraft.dinosaur.Dinosaur;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.item.JCItemRegistry;

import com.google.common.collect.Maps;

public class JCRenderingRegistry
{
    private static Map<Dinosaur, RenderDinosaurDefinition> renderDefs = Maps.newHashMap();

    public void preInit()
    {
        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            String dinoName = dino.getName().toLowerCase().replaceAll(" ", "_");

            ModelBakery.addVariantName(JCItemRegistry.fossil, "jurassicraft:fossil/fossil_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.dna, "jurassicraft:dna/dna_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.egg, "jurassicraft:egg/egg_" + dinoName);
        }
    }

    public void init()
    {
        registerRenderDef(new RenderDefAchillobator());
        registerRenderDef(new RenderDefAnklyosaurus());
        registerRenderDef(new RenderDefCarnotaurus());
        registerRenderDef(new RenderDefCompsognathus());
        registerRenderDef(new RenderDefDilophosaurus());
        registerRenderDef(new RenderDefGallimimus());
        registerRenderDef(new RenderDefGiganotosaurus());
        registerRenderDef(new RenderDefIndominusRex());
        registerRenderDef(new RenderDefMajungasaurus());
        registerRenderDef(new RenderDefParasaurolophus());
        registerRenderDef(new RenderDefPteranodon());
        registerRenderDef(new RenderDefRugops());
        registerRenderDef(new RenderDefSegisaurus());
        registerRenderDef(new RenderDefSpinosaurus());
        registerRenderDef(new RenderDefStegosaurus());
        registerRenderDef(new RenderDefTyrannosaurusRex());
        registerRenderDef(new RenderDefVelociraptor());

        // Blocks
        ItemModelMesher modelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

        this.registerBlockRenderer(modelMesher, JCBlockRegistry.fossil, "fossil_block", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.encased_fossil, "encased_fossil", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.cleaning_station, "cleaning_station", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.fossil_grinder, "fossil_grinder", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.dna_sequencer, "dna_sequencer", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.dna_synthesizer, "dna_synthesizer", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.embryonic_machine, "embryonic_machine", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.embryo_insemination_machine, "embryo_insemination_machine", "inventory");
    }

    public void postInit()
    {
        for (Dinosaur dinosaur : JCEntityRegistry.getDinosaurs())
        {
            RenderDinosaurDefinition renderDef = renderDefs.get(dinosaur);

            String[] maleOverlayTextures = dinosaur.getMaleOverlayTextures();

            if (maleOverlayTextures != null && maleOverlayTextures.length > 0)
                RenderingRegistry.registerEntityRenderingHandler(dinosaur.getDinosaurClass(), new RenderDinosaurMultilayer(dinosaur, renderDef));
            else
                RenderingRegistry.registerEntityRenderingHandler(dinosaur.getDinosaurClass(), new RenderDinosaur(dinosaur, renderDef));
        }

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        ItemModelMesher modelMesher = renderItem.getItemModelMesher();

        // Items
        this.registerItemRenderer(modelMesher, JCItemRegistry.plaster_and_bandage, "plaster_and_bandage", "inventory");
        this.registerItemRenderer(modelMesher, JCItemRegistry.spawn_egg, "dino_spawn_egg", "inventory");

        int meta = 0;

        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            String dinoName = dino.getName().toLowerCase().replaceAll(" ", "_");

            this.registerItemRenderer(modelMesher, JCItemRegistry.dna, meta, "dna/dna_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.fossil, meta, "fossil/fossil_" + dinoName, "inventory");
            this.registerItemRenderer(modelMesher, JCItemRegistry.egg, meta, "egg/egg_" + dinoName, "inventory");

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
     * Registers an item renderer with meta
     */
    public void registerItemRenderer(ItemModelMesher itemModelMesher, Item item, int meta, String path, String type)
    {
        itemModelMesher.register(item, meta, new ModelResourceLocation(JurassiCraft.modid + ":" + path, type));
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
}
