package org.jurassicraft;

import net.ilexiconn.bookwiki.BookWiki;
import net.ilexiconn.bookwiki.api.BookWikiAPI;
import net.ilexiconn.bookwiki.server.component.ColorComponent;
import net.ilexiconn.bookwiki.server.component.RecipeComponent;
import net.ilexiconn.llibrary.common.message.AbstractMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;
import org.jurassicraft.common.achievements.JCAchievements;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.configuration.JCConfigurations;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.food.FoodHelper;
import org.jurassicraft.common.item.JCItemRegistry;
import org.jurassicraft.common.message.*;
import org.jurassicraft.common.paleopad.AppRegistry;
import org.jurassicraft.common.plant.JCPlantRegistry;
import org.jurassicraft.common.proxy.CommonProxy;
import org.jurassicraft.common.recipe.JCRecipeRegistry;
import org.jurassicraft.common.storagedisc.StorageTypeRegistry;
import org.jurassicraft.common.world.islanublar.WorldTypeIslaNublar;

import java.io.File;
import java.io.InputStreamReader;

@Mod(modid = JurassiCraft.MODID, name = JurassiCraft.MODNAME, version = JurassiCraft.VERSION, guiFactory = "org.jurassicraft.client.gui.config.GUIFactory", dependencies = "required-after:llibrary@[0.7.1,)")
public class JurassiCraft
{
    @SidedProxy(serverSide = "org.jurassicraft.common.proxy.CommonProxy", clientSide = "org.jurassicraft.client.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static final String MODID = "jurassicraft";
    public static final String MODNAME = "JurassiCraft";
    public static final String VERSION = "2.0.0-pre";

    @Instance(JurassiCraft.MODID)
    public static JurassiCraft instance;
    public static long timerTicks;
    public static SimpleNetworkWrapper networkWrapper;

    private Logger logger;

    public static JCEntityRegistry entityRegistry = new JCEntityRegistry();
    public static JCPlantRegistry plantRegistry = new JCPlantRegistry();
    public static JCCreativeTabs creativeTabRegistry = new JCCreativeTabs();
    public static JCItemRegistry itemRegistry = new JCItemRegistry();
    public static JCBlockRegistry blockRegistry = new JCBlockRegistry();
    public static JCRecipeRegistry recipeRegistry = new JCRecipeRegistry();
    public static AppRegistry appRegistry = new AppRegistry();
    public static JCAchievements achievements = new JCAchievements();
    public static StorageTypeRegistry storageTypeRegistry = new StorageTypeRegistry();
    public static JCConfigurations configurations = new JCConfigurations();

    public static WorldTypeIslaNublar worldTypeIslaNublar = new WorldTypeIslaNublar();

    // set up configuration properties (will be read from config file in preInit)
    public static File configFile;
    public static Configuration config;

    public static BookWiki bookWiki;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        logger.info("Loading JurassiCraft...");

        int id = 0;
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("jurassicraft");
        AbstractMessage.registerMessage(networkWrapper, MessageSyncPaleoPad.class, id++, Side.CLIENT);
        AbstractMessage.registerMessage(networkWrapper, MessageSyncPaleoPad.class, id++, Side.SERVER);
        AbstractMessage.registerMessage(networkWrapper, MessageRequestFile.class, id++, Side.CLIENT);
        AbstractMessage.registerMessage(networkWrapper, MessageRequestFile.class, id++, Side.SERVER);
        AbstractMessage.registerMessage(networkWrapper, MessageSendFile.class, id++, Side.CLIENT);
        AbstractMessage.registerMessage(networkWrapper, MessageSendFile.class, id++, Side.SERVER);
        AbstractMessage.registerMessage(networkWrapper, MessagePlacePaddockSign.class, id++, Side.SERVER);
        AbstractMessage.registerMessage(networkWrapper, MessageChangeTemperature.class, id++, Side.CLIENT);
        AbstractMessage.registerMessage(networkWrapper, MessageChangeTemperature.class, id++, Side.SERVER);
        AbstractMessage.registerMessage(networkWrapper, MessageHelicopterEngine.class, id++, Side.CLIENT);
        AbstractMessage.registerMessage(networkWrapper, MessageHelicopterEngine.class, id++, Side.SERVER);
        AbstractMessage.registerMessage(networkWrapper, MessageHelicopterDirection.class, id++, Side.CLIENT);
        AbstractMessage.registerMessage(networkWrapper, MessageHelicopterDirection.class, id++, Side.SERVER);
        AbstractMessage.registerMessage(networkWrapper, MessageHelicopterModules.class, id++, Side.CLIENT);
        AbstractMessage.registerMessage(networkWrapper, MessageHelicopterModules.class, id++, Side.SERVER);

        proxy.preInit(event);
        logger.debug("Finished pre-init for JurassiCraft!");

        FoodHelper.init();

        BookWikiAPI.registerComponent(new ColorComponent());
        BookWikiAPI.registerComponent(new RecipeComponent());
        bookWiki = BookWiki.create(instance, new InputStreamReader(JurassiCraft.class.getResourceAsStream("/assets/jurassicraft/bookwiki/bookwiki.json")));
        bookWiki.registerItem();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
        logger.debug("Finished init for JurassiCraft!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
        logger.info("Finished loaded JurassiCraft!");
    }

    @Mod.EventHandler
    public void serverStart(FMLServerStartedEvent event)
    {
        GameRules gameRules = MinecraftServer.getServer().worldServerForDimension(0).getGameRules();

        if (!gameRules.hasRule("dinoMetabolism"))
        {
            gameRules.addGameRule("dinoMetabolism", "true", GameRules.ValueType.BOOLEAN_VALUE);
        }

        if (!gameRules.hasRule("dinoGrowth"))
        {
            gameRules.addGameRule("dinoGrowth", "true", GameRules.ValueType.BOOLEAN_VALUE);
        }
    }

    public Logger getLogger()
    {
        return logger;
    }
}
