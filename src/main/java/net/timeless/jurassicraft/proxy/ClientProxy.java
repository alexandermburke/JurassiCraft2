package net.timeless.jurassicraft.proxy;

import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.block.JCBlockRegistry;
import net.timeless.jurassicraft.client.render.entity.RenderDinosaur;
import net.timeless.jurassicraft.client.render.entity.RenderDinosaurMultilayer;
import net.timeless.jurassicraft.dinosaur.Dinosaur;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.item.JCItemRegistry;

import com.google.common.collect.Maps;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    private Map<Class<? extends Entity>, Dinosaur> renderersToRegister = Maps.newHashMap();

    @Override
    public void init()
    {
        super.init();

        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            String dinoName = dino.getName().toLowerCase().replaceAll(" ", "_");

            ModelBakery.addVariantName(JCItemRegistry.fossil, "jurassicraft:fossil/fossil_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.dna, "jurassicraft:dna/dna_" + dinoName);
            ModelBakery.addVariantName(JCItemRegistry.egg, "jurassicraft:egg/egg_" + dinoName);
        }
    }

    @Override
    public void postInit()
    {
        super.postInit();

        for (Entry<Class<? extends Entity>, Dinosaur> entry : renderersToRegister.entrySet())
        {
            if(entry.getValue().getMaleOverlayTextures().length > 0)
                RenderingRegistry.registerEntityRenderingHandler(entry.getKey(), new RenderDinosaurMultilayer(entry.getValue()));
            else
                RenderingRegistry.registerEntityRenderingHandler(entry.getKey(), new RenderDinosaur(entry.getValue()));
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
        // this.registerFossilRenderer(itemModelMesher);

        // Blocks
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.fossil, "fossil_block", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.encased_fossil, "encased_fossil", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.cleaning_station, "cleaning_station", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.fossil_grinder, "fossil_grinder", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.dna_sequencer, "dna_sequencer", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.dna_synthesizer, "dna_synthesizer", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.embryonic_machine, "embryonic_machine", "inventory");
        this.registerBlockRenderer(modelMesher, JCBlockRegistry.embryo_insemination_machine, "embryo_insemination_machine", "inventory");
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
     * Registers an item
     */
    public void registerItemRenderer(ItemModelMesher itemModelMesher, Item item, int meta, String path, String type)
    {
        itemModelMesher.register(item, meta, new ModelResourceLocation(JurassiCraft.modid + ":" + path, type));
    }

    /**
     * Registers a block
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

    @Override
    public void registerEntityRenderer(Class<? extends Entity> clazz, Dinosaur creature)
    {
        super.registerEntityRenderer(clazz, creature);

        renderersToRegister.put(clazz, creature);
    }
}