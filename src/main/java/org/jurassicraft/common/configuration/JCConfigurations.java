package org.jurassicraft.common.configuration;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jurassicraft.JurassiCraft;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jabelar
 */
public class JCConfigurations
{
    private static boolean isInit = false;

    private static final void checkInit()
    {
        if (!isInit)
        {
            throw new IllegalStateException("Configuration not yet initialized.");
        }
    }

    private static Property spawnJurassiCraftMobsNaturally;

    public static boolean spawnJurassiCraftMobsNaturally()
    {
        checkInit();
        return spawnJurassiCraftMobsNaturally.getBoolean(false);
    }

    private static Property spawnVanillaMobsNaturally;

    public static boolean spawnVanillaMobsNaturally()
    {
        checkInit();
        return spawnVanillaMobsNaturally.getBoolean(true);
    }

    private static Property spawnOtherMobsModsNaturally;

    public static boolean spawnOtherMobsModsNaturally()
    {
        checkInit();
        return spawnVanillaMobsNaturally.getBoolean(true);
    }

    public static List<IConfigElement> getAllConfigurableOptions()
    {
        List<IConfigElement> list = new ArrayList<>();
        list.add(new ConfigElement(spawnJurassiCraftMobsNaturally));
        list.add(new ConfigElement(spawnVanillaMobsNaturally));
        list.add(new ConfigElement(spawnOtherMobsModsNaturally));
        return list;
    }

    /**
     * Process the configuration
     *
     * @param event
     */
    public void initConfig(FMLPreInitializationEvent event)
    {
        // might need to use suggestedConfigFile (event.getSuggestedConfigFile) location to publish
        JurassiCraft.configFile = event.getSuggestedConfigurationFile();
        JurassiCraft.instance.getLogger().debug(JurassiCraft.MODNAME + " config path = " + JurassiCraft.configFile.getAbsolutePath());
        JurassiCraft.instance.getLogger().debug("Config file exists = " + JurassiCraft.configFile.canRead());

        JurassiCraft.config = new Configuration(JurassiCraft.configFile);
        JurassiCraft.config.load();

        syncConfig();
        isInit = true;
    }

    /*
     * sync the configuration want it public so you can handle case of changes made in-game
     */
    private void syncConfig()
    {
        spawnJurassiCraftMobsNaturally = JurassiCraft.config.get(Configuration.CATEGORY_GENERAL, "JurassiCraft Mobs Spawn Naturally", false, "Allow JurassiCraft entities to spawn naturally during world generation");
        spawnJurassiCraftMobsNaturally.getBoolean(false); // Init
        spawnJurassiCraftMobsNaturally.setRequiresMcRestart(true);
        spawnVanillaMobsNaturally = JurassiCraft.config.get(Configuration.CATEGORY_GENERAL, "Vanilla Mobs Spawn Naturally", true, "Allow vanilla mobs to spawn naturally during world generation");
        spawnVanillaMobsNaturally.getBoolean(true); // Init
        spawnVanillaMobsNaturally.setRequiresMcRestart(true);
        spawnOtherMobsModsNaturally = JurassiCraft.config.get(Configuration.CATEGORY_GENERAL, "Other Mods' Mobs Spawn Naturally", true, "Allow mobs from other mods to spawn naturally during world generation");
        spawnOtherMobsModsNaturally.getBoolean(true); // Init
        spawnOtherMobsModsNaturally.setRequiresMcRestart(true);

        // save is useful for the first run where config might not exist, and doesn't hurt
        if (JurassiCraft.config.hasChanged())
        {
            JurassiCraft.config.save();
        }
    }

    @SubscribeEvent
    public void onConfigChange(OnConfigChangedEvent occe)
    {
        if (!occe.modID.equals(JurassiCraft.MODID))
        {
            return;
        }
        syncConfig();
    }
}
