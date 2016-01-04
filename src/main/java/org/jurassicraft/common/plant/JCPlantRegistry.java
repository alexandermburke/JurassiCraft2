package org.jurassicraft.common.plant;

import java.util.ArrayList;
import java.util.List;

public class JCPlantRegistry
{
    private static final List<Plant> plants = new ArrayList<>();

    public static final Plant small_royal_fern = new PlantSmallRoyalFern();
    private static final Plant calamites = new PlantCalamites();
    private static final Plant small_chain_fern = new PlantSmallChainFern();
    private static final Plant small_cycad = new PlantSmallCycad();
    private static final Plant ginkgo = new PlantGinkgo();
    private static final Plant bennettitalean_cycadeoidea = new PlantBennettitaleanCycadeoidea();
    private static final Plant cry_pansy = new PlantCryPansy();
    private static final Plant scaly_tree_fern = new PlantScalyTreeFern();
    private static final Plant cycad_zamites = new PlantZamites();
    private static final Plant dicksonia = new PlantDicksonia();

    public void register()
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

    private void registerPlant(Plant plant)
    {
        if (!plants.contains(plant))
        {
            plants.add(plant);
        }
    }
}
