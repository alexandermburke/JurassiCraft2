package net.ilexiconn.jurassicraft;

import net.ilexiconn.jurassicraft.block.JCBlockRegistry;
import net.ilexiconn.jurassicraft.creativetab.JCCreativeTabs;
import net.ilexiconn.jurassicraft.entity.base.JCEntityRegistry;
import net.ilexiconn.jurassicraft.item.JCItemRegistry;
import net.ilexiconn.jurassicraft.proxy.ClientProxy;
import net.ilexiconn.jurassicraft.proxy.ServerProxy;
import net.ilexiconn.llibrary.common.content.ContentHandlerList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

import java.io.IOException;

@Mod(modid = "jurassicraft", name = "JurassiCraft", version = "${version}", dependencies = "required-after:llibrary@[0.1.0-1.8,)")
public class JurassiCraft
{
    @SidedProxy(serverSide = "net.ilexiconn.jurassicraft.proxy.ServerProxy", clientSide = "net.ilexiconn.jurassicraft.proxy.ClientProxy")
    public static ServerProxy proxy;
    public static SimpleNetworkWrapper wrapper;

    @Instance("jurassicraft")
    public static JurassiCraft instance;

    public static boolean debug;

    //This is to remember to JCItemRegistry for the post init function
    //Later this should be called by IConentHandler in a postinit() to automate
    public static JCItemRegistry jcItemRegistry;


    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event) throws IOException
    {
        this.jcItemRegistry = new JCItemRegistry();
        ContentHandlerList.createList(new JCCreativeTabs(), new JCItemRegistry(), new JCBlockRegistry(), new JCEntityRegistry()).init();
        proxy.init();



        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel("jurassicraft");
    }

    @Mod.EventHandler
    public void init(FMLPostInitializationEvent event)
    {
        proxy.postInit();

        //This is where IContentHandler postinit function should run
        if (ClientProxy.class.isInstance(proxy)) {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            ItemModelMesher itemModelMesher = renderItem.getItemModelMesher();
            this.jcItemRegistry.postinit(itemModelMesher);
        }
    }
}