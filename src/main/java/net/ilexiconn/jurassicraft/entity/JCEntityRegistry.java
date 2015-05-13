package net.ilexiconn.jurassicraft.entity;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javassist.ClassPool;
import javassist.CtClass;
import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.item.ItemDinosaurSpawnEgg;
import net.ilexiconn.llibrary.IContentHandler;
import net.ilexiconn.llibrary.entity.EntityHelper;
import net.ilexiconn.llibrary.json.JsonFactory;
import net.minecraft.entity.EntityLiving;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class JCEntityRegistry implements IContentHandler
{
    public static final String jsonLocation = "/assets/jurassicraft/entities/";
    private static Map<Class<? extends EntityLiving>, Creature> creatures = Maps.newHashMap();
    public List<String> jsonFiles = Lists.newArrayList();

    public static Creature getCreatureFromClass(Class<? extends EntityLiving> clazz)
    {
        return creatures.get(clazz);
    }

    public void init()
    {
        try
        {
            File jsons = new File(JurassiCraft.class.getResource(jsonLocation).toURI());

            for (File file : jsons.listFiles())
            {
                if (!file.isDirectory())
                {
                    registerJson(file.getName());
                }
            }
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
    }

    public void gameRegistry() throws Exception
    {
        for (String json : jsonFiles)
        {
            Creature jsonCreature = JsonFactory.getGson().fromJson(new InputStreamReader(JurassiCraft.class.getResourceAsStream(json)), Creature.class);

            registerEntity(jsonCreature);
        }
    }

    public void registerJson(String name)
    {
        jsonFiles.add(jsonLocation + name);
    }

    public void registerEntity(Creature creature)
    {
        if (creature.shouldRegister())
        {
            try
            {
                Class<? extends EntityLiving> clazz = EntityDinosaur.class;

                String entityClass = creature.getEntityClass();

                if (entityClass != null)
                {
                    clazz = (Class<? extends EntityLiving>) Class.forName(entityClass);
                }
                else
                {
                    ClassPool pool = ClassPool.getDefault();

                    CtClass dinoCreature = pool.makeClass("net.ilexiconn.jurassicraft.entity.EntityJurassicraft" + creature.getName());

                    try
                    {
                        dinoCreature.setSuperclass(pool.get("net.ilexiconn.jurassicraft.entity.EntityDinosaur"));
                        clazz = dinoCreature.toClass();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                creatures.put(clazz, creature);
                EntityHelper.registerEntity(creature.getName(), clazz);
                ItemDinosaurSpawnEgg.creatures.add(clazz);
                JurassiCraft.proxy.registerEntityRenderer(clazz, creature);
            }
            catch (ClassNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public void registerEgg(Creature creature, int primaryColor, int secondaryColor)
    {

    }

    public static Class<? extends EntityLiving> getCreatureClassByName(String creatureName)
    {
        for (Entry<Class<? extends EntityLiving>, Creature> creature : creatures.entrySet())
        {
            if (creature.getValue().getName().equalsIgnoreCase(creatureName))
            {
                return creature.getKey();
            }
        }

        return null;
    }

    public static Map<Class<? extends EntityLiving>, Creature> getCreatures()
    {
        return creatures;
    }
}
