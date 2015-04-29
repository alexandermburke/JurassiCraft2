package net.ilexiconn.jurassicraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.ilexiconn.jurassicraft.block.JCBlockRegistry;
import net.ilexiconn.jurassicraft.creativetab.JCCreativeTabs;
import net.ilexiconn.jurassicraft.entity.JCEntityRegistry;
import net.ilexiconn.jurassicraft.item.JCItemRegistry;
import net.ilexiconn.jurassicraft.message.MessageAnimation;
import net.ilexiconn.jurassicraft.proxy.ServerProxy;
import net.ilexiconn.llibrary.ContentHandlerList;

import java.io.IOException;

@Mod(modid = "jurassicraft", name = "JurassiCraft", version = "${version}")
public class JurassiCraft
{
    public static final String[] timer = new String[]{"field_71428_T", "S", "timer"};
    @SidedProxy(serverSide = "net.ilexiconn.jurassicraft.proxy.ServerProxy", clientSide = "net.ilexiconn.jurassicraft.proxy.ClientProxy")
    public static ServerProxy proxy;
    public static SimpleNetworkWrapper wrapper;

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event) throws IOException
    {
        ContentHandlerList.createList(new JCCreativeTabs(), new JCItemRegistry(), new JCBlockRegistry(), new JCEntityRegistry()).init();
        proxy.init();

        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel("jurassicraft");
        wrapper.registerMessage(MessageAnimation.class, MessageAnimation.class, 0, Side.CLIENT);
    }
}