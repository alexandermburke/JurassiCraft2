package net.timeless.jurassicraft.common.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.event.CommonEventHandler;
import net.timeless.jurassicraft.common.handler.JCGuiHandler;
import net.timeless.jurassicraft.common.world.WorldGenerator;

public class CommonProxy
{
    public void preInit()
    {
        JurassiCraft.entityRegistry.register();
        JurassiCraft.creativeTabRegistry.register();
        JurassiCraft.itemRegistry.register();
        JurassiCraft.blockRegistry.register();
        JurassiCraft.recipeRegistry.register();
        JurassiCraft.networkManager.register();

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
}
