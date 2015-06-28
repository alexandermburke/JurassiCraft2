package net.timeless.jurassicraft.entity.base;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.client.render.entity.RenderDinosaur;
import net.timeless.jurassicraft.client.render.entity.RenderDinosaurMultilayer;
import net.timeless.jurassicraft.dinosaur.*;
import net.timeless.jurassicraft.period.EnumTimePeriod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JCEntityRegistry
{
    private static List<Dinosaur> dinosaurs = Lists.newArrayList();
    private static HashMap<EnumTimePeriod, List<Dinosaur>> dinosaursFromPeriod = new HashMap<>();
    private static Map<Class<? extends Entity>, Dinosaur> renderersToRegister = Maps.newHashMap();


    public static void preInitCommon()
    {
        registerDinosaur(new DinosaurAchillobator());
        registerDinosaur(new DinosaurCarnotaurus());
        registerDinosaur(new DinosaurCompsognathus());
        registerDinosaur(new DinosaurDilophosaurus());
        registerDinosaur(new DinosaurGallimimus());
        registerDinosaur(new DinosaurGiganotosaurus());
        registerDinosaur(new DinosaurIndominusRex());
        registerDinosaur(new DinosaurMajungasaurus());
        registerDinosaur(new DinosaurParasaurolophus());
        registerDinosaur(new DinosaurRugops());
        registerDinosaur(new DinosaurSpinosaurus());
        registerDinosaur(new DinosaurStegosaurus());
        registerDinosaur(new DinosaurTyrannosaurusRex());
        registerDinosaur(new DinosaurVelociraptor());

        for (Dinosaur dinosaur : dinosaurs)
            registerEntityDinosaur(dinosaur);
        // Always register a new dinosaur after last one in list, otherwise all items with metadata will be shifted by one (all dinosaurs will change form D:) (UNLESS it is before the release of JC2)
    }

    public static void initCommon()
    {

    }

    public static void postInitCommon()
    {

    }

    public static void preInitClientOnly()
    {

    }

    public static void initClientOnly()
    {
        for (Dinosaur dinosaur : dinosaurs)
            registerEntityDinosaurRenderer(dinosaur);
    }

    public static void postInitClientOnly()
    {

    }

    public static void registerDinosaur(Dinosaur dinosaur)
    {
        if (dinosaur.shouldRegister())
        {
            dinosaurs.add(dinosaur);
            EnumTimePeriod period = dinosaur.getPeriod();
            List<Dinosaur> dinoList = dinosaursFromPeriod.get(period);
            if (dinoList != null)
            {
                dinoList.add(dinosaur);
                dinosaursFromPeriod.remove(period);
                dinosaursFromPeriod.put(period, dinoList);
            }
            else
            {
                List<Dinosaur> newDinoList = Lists.newArrayList();
                newDinoList.add(dinosaur);
                dinosaursFromPeriod.put(period, newDinoList);
            }
        }
    }

    public static void registerEntityDinosaur(Dinosaur dinosaur)
    {
        if (dinosaur.shouldRegister())
        {
            Class<? extends EntityDinosaur> entityClass = dinosaur.getDinosaurClass();
            int uniqueEntityId = EntityRegistry.findGlobalUniqueEntityId();
            String name = dinosaur.getName().toLowerCase().replaceAll(" ", "_");

            EntityRegistry.registerGlobalEntityID(entityClass, name, uniqueEntityId);
            EntityRegistry.registerModEntity(entityClass, name, uniqueEntityId, JurassiCraft.instance, 1024, 1, true);
        }
    }

    public static void registerEntityDinosaurRenderer(Dinosaur dinosaur)
    {
        if (dinosaur.shouldRegister())
        {
            if (dinosaur.getMaleOverlayTextures().length > 0)
                RenderingRegistry.registerEntityRenderingHandler(dinosaur.getDinosaurClass(), new RenderDinosaurMultilayer(dinosaur));
            else
                RenderingRegistry.registerEntityRenderingHandler(dinosaur.getDinosaurClass(), new RenderDinosaur(dinosaur));
        }
    }

    public static List<Dinosaur> getDinosaurs()
    {
        return dinosaurs;
    }

    public static Dinosaur getDinosaurById(int id)
    {
        if (id > dinosaurs.size() - 1)
            id = 0;
        return dinosaurs.get(id);
    }

    public static List<Dinosaur> getDinosaursFromPeriod(EnumTimePeriod period)
    {
        return dinosaursFromPeriod.get(period);
    }

    public static Dinosaur getDinosaurByClass(Class<? extends EntityDinosaur> clazz)
    {
        for (Dinosaur dino : dinosaurs)
        {
            if (dino.getDinosaurClass().equals(clazz))
                return dino;
        }
        return null;
    }
}
