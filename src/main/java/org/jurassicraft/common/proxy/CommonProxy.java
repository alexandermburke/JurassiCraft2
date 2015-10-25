package org.jurassicraft.common.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.api.IHybrid;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.event.CommonEventHandler;
import org.jurassicraft.common.handler.JCGuiHandler;
import org.jurassicraft.common.item.JCItemRegistry;
import org.jurassicraft.common.world.WorldGenerator;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        JurassiCraft.entityRegistry.register();
        JurassiCraft.plantRegistry.register();
        JurassiCraft.creativeTabRegistry.register();
        JurassiCraft.itemRegistry.register();
        JurassiCraft.blockRegistry.register();
        JurassiCraft.recipeRegistry.register();
        JurassiCraft.networkManager.register();
        JurassiCraft.appRegistry.register();
        JurassiCraft.achievements.register();
        JurassiCraft.storageTypeRegistry.register();
        JurassiCraft.configurations.initConfig(event);

//        addChestGenItems();

        GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);

        NetworkRegistry.INSTANCE.registerGuiHandler(JurassiCraft.instance, new JCGuiHandler());

        CommonEventHandler eventHandler = new CommonEventHandler();

        FMLCommonHandler.instance().bus().register(eventHandler);
        MinecraftForge.EVENT_BUS.register(eventHandler);
    }

    private void addChestGenItems()
    {
        ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(JCItemRegistry.amber, 1, 0), 1, 2, 30));
        ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(JCItemRegistry.amber, 1, 1), 1, 2, 30));

        List<Dinosaur> dinosaurs = new ArrayList<Dinosaur>(JCEntityRegistry.getDinosaurs());

        Map<Dinosaur, Integer> ids = new HashMap<Dinosaur, Integer>();

        int id = 0;

        for (Dinosaur dino : dinosaurs)
        {
            ids.put(dino, id);

            id++;
        }

        Collections.sort(dinosaurs);

        for (Dinosaur dino : dinosaurs)
        {
            if (dino.shouldRegister() && !(dino instanceof IHybrid))
            {
                ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(JCItemRegistry.skull, 1, ids.get(dino)), 1, 6, 80));
            }
        }
    }

    public void postInit(FMLPostInitializationEvent event)
    {
        /*
         * Remove null entries from biomeList.
         */
        BiomeGenBase[] allBiomes = Iterators.toArray(Iterators.filter(Iterators.forArray(BiomeGenBase.getBiomeGenArray()), Predicates.notNull()), BiomeGenBase.class);

        for (Object object : EntityList.classToStringMapping.entrySet())
        {
            Map.Entry<Class, String> entry = (Map.Entry<Class, String>) object;

            Class entityClass = entry.getKey();

            if (entityClass.toString().contains(JurassiCraft.MODID))
            {
                if (!JurassiCraft.spawnJurassiCraftMobsNaturally)
                {
                    EntityRegistry.removeSpawn(entityClass, EnumCreatureType.AMBIENT, allBiomes);
                    EntityRegistry.removeSpawn(entityClass, EnumCreatureType.CREATURE, allBiomes);
                    EntityRegistry.removeSpawn(entityClass, EnumCreatureType.MONSTER, allBiomes);
                    EntityRegistry.removeSpawn(entityClass, EnumCreatureType.WATER_CREATURE, allBiomes);
                }
            }
            else if(entityClass.toString().contains("minecraft"))
            {
                if (!JurassiCraft.spawnVanillaMobsNaturally)
                {
                    EntityRegistry.removeSpawn(entityClass, EnumCreatureType.AMBIENT, allBiomes);
                    EntityRegistry.removeSpawn(entityClass, EnumCreatureType.CREATURE, allBiomes);
                    EntityRegistry.removeSpawn(entityClass, EnumCreatureType.MONSTER, allBiomes);
                    EntityRegistry.removeSpawn(entityClass, EnumCreatureType.WATER_CREATURE, allBiomes);
                }
            }
            else
            {
                if (!JurassiCraft.spawnOtherMobsModsNaturally)
                {
                    EntityRegistry.removeSpawn(entityClass, EnumCreatureType.AMBIENT, allBiomes);
                    EntityRegistry.removeSpawn(entityClass, EnumCreatureType.CREATURE, allBiomes);
                    EntityRegistry.removeSpawn(entityClass, EnumCreatureType.MONSTER, allBiomes);
                    EntityRegistry.removeSpawn(entityClass, EnumCreatureType.WATER_CREATURE, allBiomes);
                }
            }
        }
    }

    public void init(FMLInitializationEvent event)
    {

    }

    public EntityPlayer getPlayer()
    {
        return null;
    }

    /**
     * Returns a side-appropriate EntityPlayer for use during message handling
     */
    public EntityPlayer getPlayerEntityFromContext(MessageContext ctx)
    {
        return ctx.getServerHandler().playerEntity;
    }

    public void registerRenderSubBlock(Block block)
    {

    }
}
