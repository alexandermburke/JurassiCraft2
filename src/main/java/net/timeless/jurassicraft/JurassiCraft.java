package net.timeless.jurassicraft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.timeless.jurassicraft.handler.GuiHandlerRegistry;
import net.timeless.jurassicraft.proxy.CommonProxy;
import org.apache.logging.log4j.Logger;

@Mod(modid = JurassiCraft.MODID, name = JurassiCraft.MODNAME, version = JurassiCraft.VERSION)
public class JurassiCraft
{
    public static final String MODID = "jurassicraft";
    public static final String MODNAME = "JurassiCraft";
    public static final String VERSION = "0.4a";

    @Instance(JurassiCraft.MODID)
    public static JurassiCraft instance;

    @SidedProxy(clientSide = "net.timeless.jurassicraft.proxy.ClientProxy", serverSide = "net.timeless.jurassicraft.proxy.ServerProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper wrapper;
    public static boolean debug;
    private Logger logger;


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
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandlerRegistry());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
        logger.info("Successfully loaded JurassicCraft!");
    }

    /**
     * Prepends the mod id to a string
     *
     * @param name
     * @return eg "jurassicraft:name"
     */
    public static String prependModID(String name)
    {
        return JurassiCraft.MODID + ":" + name;
    }

    /**
     * Prepends the mod id and folder to a string
     *
     * @param name
     * @return eg "jurassicraft:folder/name"
     */
    public static String prependModIDandFolder(String folder, String name)
    {
        return JurassiCraft.MODID + ":" + folder + "/" + name;
    }

    public Logger getLogger()
    {
        return logger;
    }
}