package org.jurassicraft.common.plant;

import net.ilexiconn.llibrary.common.content.IContentHandler;

import java.util.ArrayList;
import java.util.List;

public class JCPlantRegistry implements IContentHandler
{
    private static List<Plant> plants = new ArrayList<Plant>();

    public static Plant small_royal_fern;
    public static Plant calamites;
    public static Plant small_chain_fern;
    public static Plant small_cycad;
    public static Plant ginkgo;
    public static Plant bennettitalean_cycadeoidea;
    public static Plant cry_pansy;
    public static Plant scaly_tree_fern;
    public static Plant cycad_zamites;
    public static Plant dicksonia;

    @Override
    public void init()
    {
        small_royal_fern = new PlantSmallRoyalFern();
        calamites = new PlantCalamites();
        small_chain_fern = new PlantSmallChainFern();
        small_cycad = new PlantSmallCycad();
        ginkgo = new PlantGinkgo();
        bennettitalean_cycadeoidea = new PlantBennettitaleanCycadeoidea();
        cry_pansy = new PlantCryPansy();
        scaly_tree_fern = new PlantScalyTreeFern();
        cycad_zamites = new PlantZamites();
        dicksonia = new PlantDicksonia();
    }

    @Override
    public void gameRegistry() throws Exception
    {
        registerPlant(small_royal_fern);
        registerPlant(calamites);
        registerPlant(small_chain_fern);
        registerPlant(small_cycad);
        registerPlant(ginkgo);
        registerPlant(bennettitalean_cycadeoidea);
        registerPlant(cry_pansy);
        registerPlant(scaly_tree_fern);
        registerPlant(cycad_zamites);
        registerPlant(dicksonia);
    }

    public static Plant getPlantById(int id)
    {
        if (id >= plants.size() || id < 0)
        {
            return null;
        }

        return plants.get(id);
    }

    public static int getPlantId(Plant plant)
    {
        return plants.indexOf(plant);
    }

    public static List<Plant> getPlants()
    {
        return plants;
    }

    public void registerPlant(Plant plant)
    {
        if (!plants.contains(plant))
        {
            plants.add(plant);
        }
    }
}
