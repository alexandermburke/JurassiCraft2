package org.jurassicraft.common.world.dimension;

import com.google.common.collect.Lists;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;

import java.util.List;

class WorldChunkManagerJurassic extends WorldChunkManager
{

    private final List<BiomeGenBase> biomesToSpawnIn;
    private final BiomeCache biomeCache;
    // private final GenLayer biomeGenLayer;

    private WorldChunkManagerJurassic()
    {
        super();
        // biomeGenLayer = GenLayerJurassic.initializeAllBiomeGenerators(seed);
        // this.genBiomes = agenlayer[0];
        // this.biomeIndexLayer = agenlayer[1];
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = Lists.newArrayList(WorldChunkManager.allowedBiomes);
    }

    public WorldChunkManagerJurassic(World worldIn)
    {
        this();
    }

    @Override
    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
    }
}
