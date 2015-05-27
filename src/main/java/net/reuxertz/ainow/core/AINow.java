package net.reuxertz.ainow.core;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.reuxertz.ainow.registry.ItemRegistry;

@Mod(modid="ainow", name="AINow", version="beta")
public class AINow
{
    public static final String ModID = "ainow";
    public static Random RND = new Random(0L);

    @Mod.Instance("ainow")
    public static AINow Instance;
    @SidedProxy(serverSide = "net.reuxertz.ainow.core.ServerProxy", clientSide = "net.reuxertz.ainow.core.ClientProxy")
    public static ServerProxy proxy;

    public static net.reuxertz.ainow.registry.ItemRegistry ItemRegistry = new ItemRegistry();
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        MinecraftForge.EVENT_BUS.register(new AINowForgeEventHandler());
        FMLCommonHandler.instance().bus().register(new AINowFMLEventHandler());

        Instance = this;
        RND = new Random(0L);

        try {
            AINow.ItemRegistry.init();
            AINow.ItemRegistry.gameRegistry();
        }
        catch (Exception ex) { }

    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent e)
    {


    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {

        //This is where IContentHandler postinit function should run
        if (net.reuxertz.ainow.core.ClientProxy.class.isInstance(proxy)) {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            ItemModelMesher itemModelMesher = renderItem.getItemModelMesher();
            AINow.ItemRegistry.postinit(itemModelMesher);
        }
    }
}
