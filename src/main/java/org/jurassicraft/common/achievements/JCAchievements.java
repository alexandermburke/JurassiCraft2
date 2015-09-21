package org.jurassicraft.common.achievements;

import net.minecraftforge.common.AchievementPage;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.item.JCItemRegistry;

public class JCAchievements
{
    public static JCAchievement jurassicraft;
    public static JCAchievement fossils;
    public static JCAchievement paleontology;
    public static JCAchievement amber;
    public static JCAchievement cleaningStation;
    public static JCAchievement fossilGrinder;
    public static JCAchievement reinforcedStone;

    public static AchievementPage jurassicraftPage;

    public void register()
    {
        jurassicraft = (JCAchievement) (new JCAchievement("mod", 0, 0, JCItemRegistry.skull, null)).initIndependentStat();
        paleontology = new JCAchievement("paleontology", 2, 1, JCItemRegistry.plaster_and_bandage, jurassicraft);
        fossils = new JCAchievement("fossils", 3, 3, JCBlockRegistry.encased_fossils.get(0), paleontology);
        amber = new JCAchievement("amber", 2, -2, JCItemRegistry.amber, jurassicraft);
        cleaningStation = new JCAchievement("cleaningStation", -1, 2, JCBlockRegistry.cleaning_station, jurassicraft);
        fossilGrinder = new JCAchievement("fossilGrinder", -2, -1, JCBlockRegistry.fossil_grinder, jurassicraft);
        reinforcedStone = new JCAchievement("reinforcedStone", 4, -1, JCBlockRegistry.reinforced_stone, jurassicraft);

        jurassicraftPage = new AchievementPage("JurassiCraft", jurassicraft, paleontology, fossils, amber, cleaningStation, fossilGrinder, reinforcedStone);

        AchievementPage.registerAchievementPage(jurassicraftPage);
    }
}
