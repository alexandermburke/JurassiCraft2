package net.timeless.jurassicraft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.timeless.jurassicraft.block.JCBlockRegistry;
import net.timeless.jurassicraft.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.item.JCItemRegistry;
import net.timeless.jurassicraft.proxy.CommonProxy;

import org.apache.logging.log4j.Logger;

@Mod(modid = JurassiCraft.modid, name = "JurassiCraft", version = "${version}", dependencies = "required-after:llibrary@[0.1.0-1.8,)")
public class JurassiCraft
{
    @SidedProxy(serverSide = "net.timeless.jurassicraft.proxy.CommonProxy", clientSide = "net.timeless.jurassicraft.proxy.ClientProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper wrapper;

    public static final String modid = "jurassicraft";

    @Instance(JurassiCraft.modid)
    public static JurassiCraft instance;

    public static boolean debug;
    private Logger logger;

    public static JCEntityRegistry entityRegistry = new JCEntityRegistry();
    public static JCCreativeTabs creativeTabRegistry = new JCCreativeTabs();
    public static JCItemRegistry itemRegistry = new JCItemRegistry();
    public static JCBlockRegistry blockRegistry = new JCBlockRegistry();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        logger.info("Loading JurassiCraft...");

        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();

        logger.info("Successfully loaded JurassicCraft!");
    }

    public Logger getLogger()
    {
        return logger;
    }
}