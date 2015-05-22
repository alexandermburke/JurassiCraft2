package net.reuxertz.ainow.core;

import java.util.Random;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;

@Mod(modid="ainow", name="AINow", version="beta")
public class AINow
{
    public static final String ModID = "ainow";
    public static Random RND = new Random(0L);
    public boolean DebugMode;
    @Mod.Instance("ainow")
    public static AINow Instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        MinecraftForge.EVENT_BUS.register(new AINowForgeEventHandler());
        FMLCommonHandler.instance().bus().register(new AINowFMLEventHandler());


        Instance = this;
        RND = new Random(0L);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {}
}
