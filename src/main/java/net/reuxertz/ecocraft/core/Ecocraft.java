package net.reuxertz.ecocraft.core;

import net.ilexiconn.jurassicraft.block.JCBlockRegistry;
import net.ilexiconn.jurassicraft.creativetab.JCCreativeTabs;
import net.ilexiconn.jurassicraft.entity.base.JCEntityRegistry;
import net.ilexiconn.jurassicraft.item.JCItemRegistry;
import net.ilexiconn.jurassicraft.proxy.*;
import net.ilexiconn.llibrary.common.content.ContentHandlerList;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.io.IOException;
import java.util.Random;

@Mod(modid="ecocraft", name="Ecocraft", version="beta")
public class Ecocraft
{
    public static final String ModID = "ecocraft";
    public static Random RND = new Random(0L);
    //@SidedProxy(serverSide = "net.ilexiconn.jurassicraft.proxy.ServerProxy", clientSide = "net.ilexiconn.jurassicraft.proxy.ClientProxy")
    //public static net.ilexiconn.jurassicraft.proxy.ServerProxy proxy;
    @Mod.Instance("ecocraft")
    public static Ecocraft Instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        Instance = this;
        RND = new Random(0L);

        //proxy.init();

    }
    @Mod.EventHandler
    public void init(FMLPostInitializationEvent event)
    {
        //proxy.postInit();
    }
}
