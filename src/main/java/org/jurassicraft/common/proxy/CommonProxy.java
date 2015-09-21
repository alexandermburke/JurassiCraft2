package org.jurassicraft.common.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.event.CommonEventHandler;
import org.jurassicraft.common.handler.JCGuiHandler;
import org.jurassicraft.common.world.WorldGenerator;

public class CommonProxy
{
    public void preInit()
    {
        JurassiCraft.entityRegistry.register();
        JurassiCraft.plantRegistry.register();
        JurassiCraft.creativeTabRegistry.register();
        JurassiCraft.itemRegistry.register();
        JurassiCraft.blockRegistry.register();
        JurassiCraft.recipeRegistry.register();
        JurassiCraft.networkManager.register();
        JurassiCraft.appRegistry.register();
        JurassiCraft.achievements.register();
        JurassiCraft.storageTypeRegistry.register();

        GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);

        NetworkRegistry.INSTANCE.registerGuiHandler(JurassiCraft.instance, new JCGuiHandler());

        CommonEventHandler eventHandler = new CommonEventHandler();

        FMLCommonHandler.instance().bus().register(eventHandler);
        MinecraftForge.EVENT_BUS.register(eventHandler);
    }

    public void postInit()
    {

    }

    public void init()
    {

    }

    public EntityPlayer getPlayer()
    {
        return null;
    }

    /**
     * Returns a side-appropriate EntityPlayer for use during message handling
     */
    public EntityPlayer getPlayerEntityFromContext(MessageContext ctx)
    {
        return ctx.getServerHandler().playerEntity;
    }
}
