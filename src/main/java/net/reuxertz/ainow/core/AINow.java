package net.reuxertz.ainow.core;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.reuxertz.ainow.item.ItemRegistry;
import java.util.Random;

@Mod(modid = "ainow", name = "AINow", version = "beta")
public class AINow {

    public static final String ModID = "ainow";
    public static Random RND = new Random(0);
    public boolean DebugMode;

    @Mod.Instance(AINow.ModID)
    public static AINow Instance;

    //Mod Events
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        //Register Bus Events
        MinecraftForge.EVENT_BUS.register(new AINowForgeEventHandler());
        FMLCommonHandler.instance().bus().register(new AINowFMLEventHandler());

        //Register Instance
        AINow.Instance = this;
        AINow.RND = new Random(0);
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {

        //Register Items
        ItemRegistry.RegisterCreativeItems();
    }
}