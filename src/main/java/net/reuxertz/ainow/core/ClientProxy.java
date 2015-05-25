package net.reuxertz.ainow.core;

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

    /**
     * Should we use it?
     */
    public void RegisterItemRenderer(ItemModelMesher itemModelMesher, Item item, final String path, final String type)
    {
        itemModelMesher.register(item, new ItemMeshDefinition()
        {
            public ModelResourceLocation getModelLocation(ItemStack stack)
            {
                return new ModelResourceLocation(path, type);
            }
        });
    }

    public void RegisterEntityRenderer(Class<? extends Entity> clazz, Dinosaur creature)
    {
        super.RegisterEntityRenderer(clazz, creature);

        renderersToRegister.put(clazz, creature);
    }
}