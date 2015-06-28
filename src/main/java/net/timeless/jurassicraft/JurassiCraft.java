package net.timeless.jurassicraft;

import net.ilexiconn.llibrary.common.content.ContentHandlerList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.timeless.jurassicraft.block.JCBlockRegistry;
import net.timeless.jurassicraft.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.handler.GuiHandler;
import net.timeless.jurassicraft.item.JCItemRegistry;
import net.timeless.jurassicraft.packets.MessageCleaningTable;
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

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        logger.info("Loading JurassiCraft...");
        ContentHandlerList.createList(new JCEntityRegistry(), new JCCreativeTabs(), new JCItemRegistry(), new JCBlockRegistry()).init();
        proxy.preInit();
        
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        JurassiCraft.wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(JurassiCraft.modid);
        JurassiCraft.wrapper.registerMessage(MessageCleaningTable.Handler.class, MessageCleaningTable.class, 0, Side.SERVER);
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