package net.timeless.jurassicraft.common.world.dimension;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.timeless.jurassicraft.common.world.dimension.layer.GenLayerJurassic;

import java.util.ArrayList;
import java.util.List;

public class WorldChunkManagerJurassic extends WorldChunkManager
{

    private final List biomesToSpawnIn;
    private final BiomeCache biomeCache;
//    private final GenLayer biomeGenLayer;

    public WorldChunkManagerJurassic(long seed)
    {
        super();
//        biomeGenLayer = GenLayerJurassic.initializeAllBiomeGenerators(seed);
//        this.genBiomes = agenlayer[0];
//        this.biomeIndexLayer = agenlayer[1];
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList(allowedBiomes);
    }

    public WorldChunkManagerJurassic(World worldIn)
    {
        this(worldIn.getSeed());
    }

    @Override
    public void cleanupCache() {
        this.biomeCache.cleanupCache();
    }
}