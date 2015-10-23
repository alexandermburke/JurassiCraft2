package org.jurassicraft.common.configuration;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
        // DEBUG
        System.out.println(JurassiCraft.MODNAME + " config path = " + JurassiCraft.configFile.getAbsolutePath());
        System.out.println("Config file exists = " + JurassiCraft.configFile.canRead());

        JurassiCraft.config = new Configuration(JurassiCraft.configFile);

        syncConfig();
    }

    /*
     * sync the configuration want it public so you can handle case of changes made in-game
     */
    public void syncConfig()
    {
        JurassiCraft.config.load();
        JurassiCraft.spawnDinosNaturally = JurassiCraft.config.get(Configuration.CATEGORY_GENERAL, "Dinosaurs Spawn Naturally", true, "Allow dinosaurs to spawn naturally during world generation").getBoolean(true);
        JurassiCraft.instance.getLogger().info("Config spawnDinosNaturally = " + JurassiCraft.spawnDinosNaturally);
        JurassiCraft.spawnNonDinoMobsNaturally = JurassiCraft.config.get(Configuration.CATEGORY_GENERAL, "Non-Dinosaur Mobs Spawn Naturally", true, "Allow vanilla mobs and mobs from other mods to spawn naturally during world generation").getBoolean(true);
        JurassiCraft.instance.getLogger().info("Config spawnVanillaMobsNaturally = " + JurassiCraft.spawnNonDinoMobsNaturally);

        // save is useful for the first run where config might not exist, and doesn't hurt
        JurassiCraft.config.save();
    }
}
