package net.ilexiconn.jurassicraft.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.entity.json.JsonCreature;
import net.ilexiconn.jurassicraft.json.JsonFactory;
import net.ilexiconn.llibrary.IContentHandler;
import net.ilexiconn.llibrary.entity.EntityHelper;
import net.minecraft.entity.Entity;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class JCEntityRegistry implements IContentHandler
{
    public List<String> jsonFiles = Lists.newArrayList();
    private static Map<Class, JsonCreature> creatures = Maps.newHashMap();

    public void init()
    {
        registerJson("test.json");

        /**
         * Register all the creatures here
         */
    }

    public void gameRegistry() throws Exception
    {
        for (String json : jsonFiles) registerEntity(JsonFactory.getGson().fromJson(new InputStreamReader(JurassiCraft.class.getResourceAsStream("/jurassicraft/json/" + json)), JsonCreature.class));
    }

    public void registerJson(String name)
    {
        jsonFiles.add(name);
    }

    public void registerEntity(JsonCreature creature)
    {
        try
        {
            Class<? extends Entity> clazz = (Class<? extends Entity>) Class.forName("net.ilexiconn.jurassicraft.entity.Entity" + creature.getName());
            creatures.put(clazz, creature);
            EntityHelper.registerEntity(creature.getName(), clazz, creature.getEggPrimaryColor(), creature.getEggSecondaryColor());
            JurassiCraft.proxy.registerEntityRenderer(clazz, creature);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static JsonCreature getCreatureFromClass(Class c)
    {
        return creatures.get(c);
    }
}