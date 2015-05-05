package net.ilexiconn.jurassicraft.proxy;

import java.util.Map;
import java.util.Map.Entry;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.client.render.entity.RenderDinosaur;
import net.ilexiconn.jurassicraft.json.JsonCreature;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.Timer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.Maps;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy
{
    private Timer mcTimer;

    private Map<Class<? extends Entity>, JsonCreature> renderersToRegister = Maps.newHashMap();

    public void init()
    {
        super.init();
        
        mcTimer = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), JurassiCraft.timer);
    }

    public void postInit()
    {
        super.postInit();
        
        for (Entry<Class<? extends Entity>, JsonCreature> entry : renderersToRegister.entrySet())
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

    public void registerEntityRenderer(Class<? extends Entity> clazz, JsonCreature creature)
    {
        super.registerEntityRenderer(clazz, creature);
        
        renderersToRegister.put(clazz, creature);
    }

    public float getPartialTick()
    {
        return mcTimer.renderPartialTicks;
    }
}