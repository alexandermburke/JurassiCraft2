package net.ilexiconn.jurassicraft.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.client.render.entity.RenderDinosaur;
import net.ilexiconn.jurassicraft.entity.json.JsonCreature;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.Timer;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy
{
    private Timer mcTimer;

    public void init()
    {
        mcTimer = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), JurassiCraft.timer);
    }

    public void registerEntityRenderer(Class<? extends Entity> clazz, JsonCreature creature)
    {
        try
        {
            RenderingRegistry.registerEntityRenderingHandler(clazz, new RenderDinosaur(creature));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public float getPartialTick()
    {
        return mcTimer.renderPartialTicks;
    }
}