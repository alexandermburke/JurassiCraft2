package net.ilexiconn.jurassicraft.entity;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.ilexiconn.jurassicraft.json.JsonCreature;
import net.ilexiconn.llibrary.IContentHandler;
import net.ilexiconn.llibrary.entity.EntityHelper;
import net.ilexiconn.llibrary.json.JsonFactory;
import net.minecraft.entity.EntityLiving;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class JCEntityRegistry implements IContentHandler
{
	public static final String jsonLocation = "/assets/jurassicraft/entities/";
	private static Map<Class<? extends EntityLiving>, JsonCreature> creatures = Maps.newHashMap();
	public List<String> jsonFiles = Lists.newArrayList();

	public static JsonCreature getCreatureFromClass(Class<? extends EntityLiving> clazz)
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
			JsonCreature jsonCreature = JsonFactory.getGson().fromJson(new InputStreamReader(JurassiCraft.class.getResourceAsStream(json)), JsonCreature.class);

			registerEntity(jsonCreature);
		}
	}

	public void registerJson(String name)
	{
		jsonFiles.add(jsonLocation + name);
	}

	public void registerEntity(JsonCreature creature)
	{
		if (creature.shouldRegister())
		{
			try
			{
				Class<? extends EntityLiving> clazz;

				clazz = EntityDinosaur.class;
				
				String entityClass = creature.getEntityClass();
				
				if(entityClass != null)
				{
					clazz = (Class<? extends EntityLiving>) Class.forName(entityClass);
				}
				
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
}