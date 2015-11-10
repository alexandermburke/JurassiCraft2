package org.jurassicraft.common.configuration;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jurassicraft.JurassiCraft;

/**
 * @author jabelar
 */
public class JCConfigurations
{
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

        syncConfig();
    }

    /*
     * sync the configuration want it public so you can handle case of changes made in-game
     */
    public void syncConfig()
    {
        JurassiCraft.config.load();
        JurassiCraft.spawnJurassiCraftMobsNaturally = JurassiCraft.config.get(Configuration.CATEGORY_GENERAL, "JurassiCraft Mobs Spawn Naturally", true, "Allow JurassiCraft entities to spawn naturally during world generation").getBoolean(true);
        JurassiCraft.instance.getLogger().info("Config spawnDinosNaturally = " + JurassiCraft.spawnJurassiCraftMobsNaturally);
        JurassiCraft.spawnVanillaMobsNaturally = JurassiCraft.config.get(Configuration.CATEGORY_GENERAL, "Vanilla Mobs Spawn Naturally", true, "Allow vanilla mobs to spawn naturally during world generation").getBoolean(true);
        JurassiCraft.instance.getLogger().info("Config spawnVanillaMobsNaturally = " + JurassiCraft.spawnVanillaMobsNaturally);
        JurassiCraft.spawnOtherMobsModsNaturally = JurassiCraft.config.get(Configuration.CATEGORY_GENERAL, "Other Mods' Mobs Spawn Naturally", true, "Allow mobs from other mods to spawn naturally during world generation").getBoolean(true);
        JurassiCraft.instance.getLogger().info("Config spawnOtherMobsModsNaturally = " + JurassiCraft.spawnOtherMobsModsNaturally);

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
            return;
        syncConfig();
    }
}
