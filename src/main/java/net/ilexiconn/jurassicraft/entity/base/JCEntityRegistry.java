package net.ilexiconn.jurassicraft.entity.base;

import com.google.common.collect.Lists;
import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.dinosaur.Dinosaur;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurSpinosaurus;
import net.ilexiconn.jurassicraft.dinosaur.DinosaurVelociraptor;
import net.ilexiconn.llibrary.common.content.IContentHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.List;

public class JCEntityRegistry implements IContentHandler
{
    private static List<Dinosaur> dinosaurs = Lists.newArrayList();

    public void init()
    {
        registerDinosaur(new DinosaurSpinosaurus());
        registerDinosaur(new DinosaurVelociraptor());
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
            String dinoName = dino.getName().toLowerCase();

            EntityRegistry.registerGlobalEntityID(entityClass, dinoName, entityId);
            EntityRegistry.registerModEntity(entityClass, dinoName, entityId, JurassiCraft.instance, 64, 1, true);

            JurassiCraft.proxy.registerEntityRenderer(entityClass, dino);
        }
    }

    public static void registerDinosaur(Dinosaur dino)
    {
        dinosaurs.add(dino);
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
