package net.ilexiconn.jurassicraft.proxy;

import java.util.Map;
import java.util.Map.Entry;

import net.ilexiconn.jurassicraft.block.JCBlockRegistry;
import net.ilexiconn.jurassicraft.client.render.entity.RenderDinosaur;
import net.ilexiconn.jurassicraft.dinosaur.Dinosaur;
import net.ilexiconn.jurassicraft.entity.base.JCEntityRegistry;
import net.ilexiconn.jurassicraft.item.JCItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.Maps;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy
{
    private Map<Class<? extends Entity>, Dinosaur> renderersToRegister = Maps.newHashMap();

    @Override
    public void init()
    {
        super.init();
    }

    @Override
    public void postInit()
    {
        super.postInit();

        for (Entry<Class<? extends Entity>, Dinosaur> entry : renderersToRegister.entrySet())
        {
            try
            {
                RenderingRegistry.registerEntityRenderingHandler(entry.getKey(), new RenderDinosaur(entry.getValue()));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        ItemModelMesher itemModelMesher = renderItem.getItemModelMesher();

        // Items
        this.registerItemRenderer(itemModelMesher, JCItemRegistry.plaster_and_bandage, "jurassicraft:item_plaster_and_bandage", "inventory");
        this.registerItemRenderer(itemModelMesher, JCItemRegistry.spawn_egg, "jurassicraft:item_dino_spawn_egg", "inventory");
        this.registerItemRenderer(itemModelMesher, JCItemRegistry.fossil, "jurassicraft:item_fossil", "inventory");
        // this.registerFossilRenderer(itemModelMesher);

        // Blocks
        this.registerBlockRenderer(itemModelMesher, JCBlockRegistry.fossil, "jurassicraft:block_fossil", "inventory");
        this.registerBlockRenderer(itemModelMesher, JCBlockRegistry.encased_fossil, "jurassicraft:block_encased_fossil", "inventory");
        this.registerBlockRenderer(itemModelMesher, JCBlockRegistry.cleaning_station, "jurassicraft:block_cleaning_station", "inventory");
        this.registerBlockRenderer(itemModelMesher, JCBlockRegistry.fossil_grinder, "jurassicraft:block_fossil_grinder", "inventory");
        this.registerBlockRenderer(itemModelMesher, JCBlockRegistry.dna_sequencer, "jurassicraft:block_dna_sequencer", "inventory");
        this.registerBlockRenderer(itemModelMesher, JCBlockRegistry.dna_synthesizer, "jurassicraft:block_dna_synthesizer", "inventory");
        this.registerBlockRenderer(itemModelMesher, JCBlockRegistry.embryonic_machine, "jurassicraft:block_embryonic_machine", "inventory");
        this.registerBlockRenderer(itemModelMesher, JCBlockRegistry.embryo_insemination_machine, "jurassicraft:block_embryo_insemination_machine", "inventory");
    }

    /**
     * Registers an item
     */
    public void registerItemRenderer(ItemModelMesher itemModelMesher, Item item, final String path, final String type)
    {
        itemModelMesher.register(item, new ItemMeshDefinition()
        {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation(path, type);
            }
        });
    }

    /**
     * Registers all fossils
     */
    public void registerFossilRenderer(ItemModelMesher itemModelMesher)
    {
        int metadata = 0;
        for (final Dinosaur dinosaur : JCEntityRegistry.getDinosaurs())
        {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(JCItemRegistry.fossil, metadata, new ModelResourceLocation("jurassicraft:item_fossil_" + dinosaur.getName().replace(" ", "_").toLowerCase(), "inventory"));
            metadata++;
        }
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
                return new ModelResourceLocation(path, type);
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