package net.timeless.jurassicraft.common.plant;

import java.util.ArrayList;
import java.util.List;

public class JCPlantRegistry
{
    private static List<Plant> plants = new ArrayList<>();

    public static final Plant small_royal_fern = new PlantSmallRoyalFern();

    public void register()
    {
        registerPlant(small_royal_fern);
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
        if(!plants.contains(plant))
        {
            plants.add(plant);
        }
    }
}
