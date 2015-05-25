package net.ilexiconn.jurassicraft.proxy;

import com.google.common.collect.Maps;
import net.ilexiconn.jurassicraft.block.JCBlockRegistry;
import net.ilexiconn.jurassicraft.client.render.entity.RenderDinosaur;
import net.ilexiconn.jurassicraft.dinosaur.Dinosaur;
import net.ilexiconn.jurassicraft.item.JCItemRegistry;
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

import java.util.Map;
import java.util.Map.Entry;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy
{
    private Map<Class<? extends Entity>, Dinosaur> renderersToRegister = Maps.newHashMap();

    public void init()
    {
        super.init();
    }

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


        itemModelMesher.register(JCItemRegistry.spawn_egg, new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation("jurassicraft:dino_spawn_egg", "inventory");
            }
        });

        itemModelMesher.register(JCItemRegistry.plaster_and_bandage, new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation("jurassicraft:plaster_and_bandage", "inventory");
            }
        });
        /*itemModelMesher.register(JCItemRegistry.plaster_and_bandage, new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation("jurassicraft:plaster_and_bandage", "inventory");
            }
        });*/
        //registerItemRenderer(itemModelMesher, JCItemRegistry.plaster_and_bandage, "jurassicraft:plaster_and_bandage", "inventory");

        itemModelMesher.register(Item.getItemFromBlock(JCBlockRegistry.fossil), new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation("jurassicraft:block_fossil", "inventory");
            }
        });

        itemModelMesher.register(Item.getItemFromBlock(JCBlockRegistry.encased_fossil), new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation("jurassicraft:block_encased_fossil", "inventory");
            }
        });

        itemModelMesher.register(Item.getItemFromBlock(JCBlockRegistry.cleaning_station), new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation("jurassicraft:block_cleaning_station", "inventory");
            }
        });
    }

    /**
     * Should we use it?
     */
    public void registerItemRenderer(ItemModelMesher itemModelMesher, Item item, final String path, final String type)
    {
        itemModelMesher.register(item, new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation(path, type);
            }
        });
    }

    public void registerEntityRenderer(Class<? extends Entity> clazz, Dinosaur creature)
    {
        super.registerEntityRenderer(clazz, creature);

        renderersToRegister.put(clazz, creature);
    }
}