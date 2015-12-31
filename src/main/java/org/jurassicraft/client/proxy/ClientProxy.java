package org.jurassicraft.client.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.client.event.ClientEventHandler;
import org.jurassicraft.client.render.JCRenderingRegistry;
import org.jurassicraft.common.proxy.CommonProxy;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    public static JCRenderingRegistry renderingRegistry = new JCRenderingRegistry();

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);

        ClientEventHandler eventHandler = new ClientEventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);

        renderingRegistry.preInit();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);

        renderingRegistry.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);

        renderingRegistry.postInit();
    }

    @Override
    public EntityPlayer getPlayer()
    {
        return Minecraft.getMinecraft().thePlayer;
    }

    /**
     * Returns a side-appropriate EntityPlayer for use during message handling
     */
    @Override
    public EntityPlayer getPlayerEntityFromContext(MessageContext ctx)
    {
        // Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
        // your packets will not work because you will be getting a client
        // player even when you are on the server! Sounds absurd, but it's true.

        // Solution is to double-check side before returning the player:
        return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntityFromContext(ctx));
    }

    @Override
    public void scheduleTask(MessageContext ctx, Runnable runnable)
    {
        Minecraft.getMinecraft().addScheduledTask(runnable);
    }
}
