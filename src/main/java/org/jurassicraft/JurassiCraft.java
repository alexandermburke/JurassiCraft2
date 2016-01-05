package org.jurassicraft;

import net.ilexiconn.bookwiki.BookWiki;
import net.ilexiconn.bookwiki.BookWikiContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import org.apache.logging.log4j.Logger;
import org.jurassicraft.common.achievements.JCAchievements;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.configuration.JCConfigurations;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.food.FoodHelper;
import org.jurassicraft.common.item.JCItemRegistry;
import org.jurassicraft.common.message.JCNetworkManager;
import org.jurassicraft.common.paleopad.AppRegistry;
import org.jurassicraft.common.plant.JCPlantRegistry;
import org.jurassicraft.common.proxy.CommonProxy;
import org.jurassicraft.common.recipe.JCRecipeRegistry;
import org.jurassicraft.common.storagedisc.StorageTypeRegistry;
import org.jurassicraft.common.world.islanublar.WorldTypeIslaNublar;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Mod(modid = JurassiCraft.MODID, name = JurassiCraft.MODNAME, version = JurassiCraft.VERSION, guiFactory = "org.jurassicraft.client.gui.config.GUIFactory", dependencies = "required-after:llibrary@[0.6.2,)")
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
    public static long timerNanoseconds;

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
    public static JCConfigurations configurations = new JCConfigurations();

    public static WorldTypeIslaNublar worldTypeIslaNublar = new WorldTypeIslaNublar();

    // set up configuration properties (will be read from config file in preInit)
    public static File configFile;
    public static Configuration config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        logger.info("Loading JurassiCraft...");
        timerNanoseconds = System.nanoTime();
        proxy.preInit(event);
        logger.debug("Finished pre-init for JurassiCraft!");

        FoodHelper.init();

        try {
            BookWiki bookWiki = BookWiki.create(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(MODID, "bookwiki/bookwiki_test.json")).getInputStream()));
            logger.info("==================== BookWiki TEST ====================");
            for (BookWikiContainer.Category category : bookWiki.getContainer().getCategories()) {
                logger.info("========= Category =========");
                logger.info(category.getName());
                logger.info(category.getIcon().getDisplayName());
                logger.info(category.getDefaultPage());
            }
            for (BookWikiContainer.Page page : bookWiki.getContainer().getPages()) {
                logger.info("=========== Page ===========");
                logger.info(page.getTitle());
                logger.info(page.getCategory());
                logger.info(page.getCategory().getName());
            }
            for (BookWikiContainer.Recipe recipe : bookWiki.getContainer().getRecipes()) {
                logger.info("========== Recipe ==========");
                logger.info(recipe.getID());
                logger.info(recipe.isShapeless());
                logger.info(recipe.getRecipe());
                logger.info(recipe.getResult().getDisplayName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public boolean isDebugging()
    {
        return "${version}".equals("${" + "version" + "}");
    }

    public long getNanoTimeInterval()
    {
        long interval = System.nanoTime() - timerNanoseconds;
        timerNanoseconds = 0;
        return interval;
    }
}
