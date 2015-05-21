package net.reuxertz.ainow.core;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Random;

@Mod(modid = AINow.ModID, name = AINow.ModID, version = "beta")
public class AINow {

    public static AINow Instance;
    public static final String ModID = "ainow";
    public static Random RND = new Random();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        //Register Bus Events
        MinecraftForge.EVENT_BUS.register(new AINowForgeEventHandler());

        //Register Instance
        AINow.Instance = this;
        AINow.RND = new Random(0);
    }
}
