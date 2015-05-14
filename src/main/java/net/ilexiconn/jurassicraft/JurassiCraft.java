package net.ilexiconn.jurassicraft;

import java.io.IOException;

import net.ilexiconn.jurassicraft.block.JCBlockRegistry;
import net.ilexiconn.jurassicraft.creativetab.JCCreativeTabs;
import net.ilexiconn.jurassicraft.entity.base.JCEntityRegistry;
import net.ilexiconn.jurassicraft.item.JCItemRegistry;
import net.ilexiconn.jurassicraft.proxy.ServerProxy;
import net.ilexiconn.llibrary.ContentHandlerList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = "jurassicraft", name = "JurassiCraft", version = "${version}")
public class JurassiCraft
{
    @SidedProxy(serverSide = "net.ilexiconn.jurassicraft.proxy.ServerProxy", clientSide = "net.ilexiconn.jurassicraft.proxy.ClientProxy")
    public static ServerProxy proxy;
    public static SimpleNetworkWrapper wrapper;

    @Instance("jurassicraft")
    public static JurassiCraft instance;

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event) throws IOException
    {
        ContentHandlerList.createList(new JCCreativeTabs(), new JCItemRegistry(), new JCBlockRegistry(), new JCEntityRegistry()).init();
        proxy.init();

        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel("jurassicraft");
    }

    @Mod.EventHandler
    public void init(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }
}