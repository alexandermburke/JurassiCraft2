package net.reuxertz.ainow.core;

import java.util.Random;

import net.ilexiconn.jurassicraft.item.JCItemRegistry;
import net.ilexiconn.jurassicraft.proxy.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.reuxertz.ainow.api.ItemRegistry;

@Mod(modid="ainow", name="AINow", version="beta")
public class AINow
{
    public static final String ModID = "ainow";
    public static Random RND = new Random(0L);
    public boolean DebugMode;
    @Mod.Instance("ainow")
    public static AINow Instance;
    @SidedProxy(serverSide = "net.reuxertz.ainow.core.ServerProxy", clientSide = "net.reuxertz.ainow.core.ClientProxy")
    public static ServerProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        MinecraftForge.EVENT_BUS.register(new AINowForgeEventHandler());
        FMLCommonHandler.instance().bus().register(new AINowFMLEventHandler());

        Instance = this;
        RND = new Random(0L);

        ItemRegistry.RegisterItems();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {

        //ItemRegistry.registerItemRenderers();
    }
}
