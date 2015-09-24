package org.jurassicraft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.jurassicraft.common.achievements.JCAchievements;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.item.JCItemRegistry;
import org.jurassicraft.common.message.JCNetworkManager;
import org.jurassicraft.common.paleopad.AppRegistry;
import org.jurassicraft.common.plant.JCPlantRegistry;
import org.jurassicraft.common.proxy.CommonProxy;
import org.jurassicraft.common.recipe.JCRecipeRegistry;
import org.jurassicraft.common.storagedisc.StorageTypeRegistry;

@Mod(modid = JurassiCraft.MODID, name = "JurassiCraft", version = "${version}")
public class JurassiCraft
{
    @SidedProxy(serverSide = "org.jurassicraft.common.proxy.CommonProxy", clientSide = "org.jurassicraft.client.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static final String MODID = "jurassicraft";

    @Instance(JurassiCraft.MODID)
    public static JurassiCraft instance;
    public static long timer;

    private Logger logger;

    public static JCEntityRegistry entityRegistry = new JCEntityRegistry();
    public static JCPlantRegistry plantRegistry = new JCPlantRegistry();
    public static JCCreativeTabs creativeTabRegistry = new JCCreativeTabs();
    public static JCItemRegistry itemRegistry = new JCItemRegistry();
    public static JCBlockRegistry blockRegistry = new JCBlockRegistry();
    public static JCRecipeRegistry recipeRegistry = new JCRecipeRegistry();
    public static JCNetworkManager networkManager = new JCNetworkManager();
    public static AppRegistry appRegistry = new AppRegistry();
    public static JCAchievements achievements = new JCAchievements();
    public static StorageTypeRegistry storageTypeRegistry = new StorageTypeRegistry();

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

    public boolean isDebugging()
    {
        return "${version}".equals("${" + "version" + "}");
    }
}