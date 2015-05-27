package net.reuxertz.ecocraft.core;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.reuxertz.ecocraft.core.proxy.ClientProxy;
import net.reuxertz.ecocraft.core.proxy.ServerProxy;
import net.reuxertz.ecocraft.core.registry.ECItemRegistry;

@Mod(modid="ecocraft", name="EcoCraft", version="beta")
public class EcoCraft
{
    public static final String ModID = "ecocraft";
    public static Random RND = new Random(0L);

    @Mod.Instance("ecocraft")
    public static EcoCraft Instance;
    @SidedProxy(serverSide = "net.reuxertz.ecocraft.core.proxy.ServerProxy", clientSide = "net.reuxertz.ecocraft.core.proxy.ClientProxy")
    public static ServerProxy proxy;

    public static ECItemRegistry ItemRegistry = new ECItemRegistry();
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        //Events
        MinecraftForge.EVENT_BUS.register(new EcoForgeHandler());

        //Mod Fields
        Instance = this;
        RND = new Random(0L);

        //Item Registry
        ItemRegistry.register();

        //Crafting Recipes
        EcoCrafting.InitCraftingRecipes();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent e)
    {


    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {

        //This is where IContentHandler postinit function should run
        if (ClientProxy.class.isInstance(proxy)) {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            ItemModelMesher itemModelMesher = renderItem.getItemModelMesher();
            EcoCraft.ItemRegistry.postinit(itemModelMesher);
        }
    }

}
