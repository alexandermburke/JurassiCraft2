package net.ilexiconn.jurassicraft.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.entity.json.JsonCreature;
import net.ilexiconn.jurassicraft.json.JsonFactory;
import net.ilexiconn.llibrary.IContentHandler;
import net.ilexiconn.llibrary.entity.EntityHelper;
import net.minecraft.entity.Entity;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class JCEntityRegistry implements IContentHandler
{
    public List<String> jsonFiles = Lists.newArrayList();
    private static Map<Class, JsonCreature> creatures = Maps.newHashMap();
    
    public static final String jsonLocation = "/assets/jurassicraft/json/";
    
    public void init()
    {
        try 
        {
            File jsons = new File(JurassiCraft.class.getResource(jsonLocation).toURI());
            
            for (File file : jsons.listFiles())
            {
                registerJson(file.getName());
            }
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
    }
    
    public void gameRegistry() throws Exception
    {
        for (String json : jsonFiles) registerEntity(JsonFactory.getGson().fromJson(new InputStreamReader(JurassiCraft.class.getResourceAsStream(json)), JsonCreature.class));
    }
    
    public void registerJson(String name)
    {
        jsonFiles.add(jsonLocation + name);
    }
    
    public void registerEntity(JsonCreature creature)
    {
        if(creature.shouldRegister())
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
    }
    
    public static JsonCreature getCreatureFromClass(Class c)
    {
        return creatures.get(c);
    }
}