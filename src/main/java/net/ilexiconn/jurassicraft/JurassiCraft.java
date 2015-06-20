package net.ilexiconn.jurassicraft;

import java.io.IOException;

import net.ilexiconn.jurassicraft.block.JCBlockRegistry;
import net.ilexiconn.jurassicraft.creativetab.JCCreativeTabs;
import net.ilexiconn.jurassicraft.entity.base.JCEntityRegistry;
import net.ilexiconn.jurassicraft.handler.GuiHandler;
import net.ilexiconn.jurassicraft.item.JCItemRegistry;
import net.ilexiconn.jurassicraft.packets.MessageCleaningTable;
import net.ilexiconn.jurassicraft.proxy.CommonProxy;
import net.ilexiconn.llibrary.common.content.ContentHandlerList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import org.apache.logging.log4j.Logger;

@Mod(modid = JurassiCraft.modid, name = "JurassiCraft", version = "${version}", dependencies = "required-after:llibrary@[0.1.0-1.8,)")
public class JurassiCraft
{
    @SidedProxy(serverSide = "net.ilexiconn.jurassicraft.proxy.ServerProxy", clientSide = "net.ilexiconn.jurassicraft.proxy.ClientProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper wrapper;

    public static final String modid = "jurassicraft";

    @Instance(JurassiCraft.modid)
    public static JurassiCraft instance;

    public static boolean debug;
    private Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) throws IOException
    {
        logger = event.getModLog();
        logger.info("Loading Jurassicraft...");
        ContentHandlerList.createList(new JCEntityRegistry(), new JCCreativeTabs(), new JCItemRegistry(), new JCBlockRegistry()).init();
        proxy.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        JurassiCraft.wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(JurassiCraft.modid);
        JurassiCraft.wrapper.registerMessage(MessageCleaningTable.Handler.class, MessageCleaningTable.class, 0, Side.SERVER);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
        logger.info("Successfully loaded Jurassicraft!");
    }

    public Logger getLogger()
    {
        return logger;
    }
}