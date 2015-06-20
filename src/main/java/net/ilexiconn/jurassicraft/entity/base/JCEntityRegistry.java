package net.ilexiconn.jurassicraft.entity.base;

import java.util.HashMap;
import java.util.List;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.dinosaur.Dinosaur;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurAchillobator;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurCarnotaurus;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurCompsognathus;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurDilophosaurus;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurGallimimus;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurGiganotosaurus;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurIndominusRex;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurMajungasaurus;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurRugops;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurSpinosaurus;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurStegosaurus;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurTyrannosaurusRex;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurVelociraptor;
import net.ilexiconn.llibrary.common.content.IContentHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import com.google.common.collect.Lists;

public class JCEntityRegistry implements IContentHandler
{
    private static List<Dinosaur> dinosaurs = Lists.newArrayList();
    private static HashMap<Integer, List<Dinosaur>> dinosaursFromPeriod = new HashMap<Integer, List<Dinosaur>>();

    public void init()
    {
        registerDinosaur(new DinosaurAchillobator());
        registerDinosaur(new DinosaurCarnotaurus());
        registerDinosaur(new DinosaurCompsognathus());
        registerDinosaur(new DinosaurDilophosaurus());
        registerDinosaur(new DinosaurGallimimus());
        registerDinosaur(new DinosaurGiganotosaurus());
        registerDinosaur(new DinosaurIndominusRex());
        registerDinosaur(new DinosaurMajungasaurus());
        registerDinosaur(new DinosaurRugops());
        registerDinosaur(new DinosaurSpinosaurus());
        registerDinosaur(new DinosaurStegosaurus());
        registerDinosaur(new DinosaurTyrannosaurusRex());
        registerDinosaur(new DinosaurVelociraptor());
        
        //Always register a new dinosaur after last one in list, otherwise all items with metadata will be shifted by one (UNLESS it is before the release of JC2)
    }

    public void gameRegistry() throws Exception
    {
        for (Dinosaur dino : dinosaurs)
        {
            registerEntity(dino);
        }
    }

    public void registerEntity(Dinosaur dino)
    {
        if (dino.shouldRegister())
        {
            Class<? extends EntityDinosaur> entityClass = dino.getDinosaurClass();

            int entityId = EntityRegistry.findGlobalUniqueEntityId();
            String dinoName = dino.getName().toLowerCase().replaceAll(" ", "_");

            EntityRegistry.registerGlobalEntityID(entityClass, dinoName, entityId);
            EntityRegistry.registerModEntity(entityClass, dinoName, entityId, JurassiCraft.instance, 1024, 1, true);

            JurassiCraft.proxy.registerEntityRenderer(entityClass, dino);
        }
    }

    public static void registerDinosaur(Dinosaur dino)
    {
        dinosaurs.add(dino);
        int period = dino.getPeriod();

        List<Dinosaur> dinoList = dinosaursFromPeriod.get(period);

        if (dinoList != null)
        {
            dinoList.add(dino);
            dinosaursFromPeriod.remove(period);
            dinosaursFromPeriod.put(period, dinoList);
        }
        else
        {
            List<Dinosaur> newDinoList = Lists.newArrayList();
            newDinoList.add(dino);
            dinosaursFromPeriod.put(period, newDinoList);
        }
    }

    public static Dinosaur getDinosaurById(int id)
    {
        if (id > dinosaurs.size() - 1)
        {
            id = 0;
        }

        return dinosaurs.get(id);
    }

    public static List<Dinosaur> getDinosaurs()
    {
        return dinosaurs;
    }

    public static List<Dinosaur> getDinosaursFromPeriod(int periodID)
    {
        return dinosaursFromPeriod.get(periodID);
    }

    public static Dinosaur getDinosaurByClass(Class<? extends EntityDinosaur> clazz)
    {
        for (Dinosaur dino : dinosaurs)
        {
            if (dino.getDinosaurClass().equals(clazz))
            {
                return dino;
            }
        }

        return null;
    }
}
