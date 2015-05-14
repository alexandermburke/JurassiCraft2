package net.ilexiconn.jurassicraft.proxy;

import java.util.Map;
import java.util.Map.Entry;

import net.ilexiconn.jurassicraft.client.render.entity.RenderDinosaur;
import net.ilexiconn.jurassicraft.dinosaur.Dinosaur;
import net.ilexiconn.jurassicraft.item.JCItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.Maps;

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
    }

    public void registerEntityRenderer(Class<? extends Entity> clazz, Dinosaur creature)
    {
        super.registerEntityRenderer(clazz, creature);

        renderersToRegister.put(clazz, creature);
    }
}