package net.timeless.jurassicraft.common.plant;

import java.util.ArrayList;
import java.util.List;

public class JCPlantRegistry
{
    private static List<Plant> plants = new ArrayList<Plant>();

    public static final Plant small_royal_fern = new PlantSmallRoyalFern();
    public static final Plant calamites = new PlantCalamites();
    public static final Plant small_chain_fern = new PlantSmallChainFern();
    public static final Plant small_cycad = new PlantSmallCycad();
    public static final Plant ginkgo = new PlantGinkgo();
    public static final Plant bennettitalean_cycadeoidea = new PlantBennettitaleanCycadeoidea();
    public static final Plant cry_pansy = new PlantCryPansy();

    public void register()
    {
        registerPlant(small_royal_fern);
        registerPlant(calamites);
        registerPlant(small_chain_fern);
        registerPlant(small_cycad);
        registerPlant(ginkgo);
        registerPlant(bennettitalean_cycadeoidea);
        registerPlant(cry_pansy);
    }

    public static Plant getPlantById(int id)
    {
        if (id >= plants.size() || id < 0)
            return null;

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
