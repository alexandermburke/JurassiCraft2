package net.ilexiconn.jurassicraft.proxy;

import com.google.common.collect.Maps;

import net.ilexiconn.jurassicraft.client.render.entity.RenderDinosaur;
import net.ilexiconn.jurassicraft.entity.Creature;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;
import java.util.Map.Entry;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy
{
    private Map<Class<? extends Entity>, Creature> renderersToRegister = Maps.newHashMap();

    public void init()
    {
        super.init();
    }

    public void postInit()
    {
        super.postInit();

        for (Entry<Class<? extends Entity>, Creature> entry : renderersToRegister.entrySet())
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
    }

    public void registerEntityRenderer(Class<? extends Entity> clazz, Creature creature)
    {
        super.registerEntityRenderer(clazz, creature);

        renderersToRegister.put(clazz, creature);
    }
}