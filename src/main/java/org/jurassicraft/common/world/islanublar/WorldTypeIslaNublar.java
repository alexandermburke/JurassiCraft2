package org.jurassicraft.common.world.islanublar;

import net.ilexiconn.llibrary.common.world.gen.ChunkProviderHeightmap;
import net.ilexiconn.llibrary.common.world.gen.WorldChunkManagerHeightmap;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;

public class WorldTypeIslaNublar extends WorldType
{
    private final IslaNublarGeneration generator;

    public WorldTypeIslaNublar()
    {
        super("isla_nublar");
        this.generator = new IslaNublarGeneration();
    }

    public net.minecraft.world.biome.WorldChunkManager getChunkManager(World world)
    {
        return new WorldChunkManagerHeightmap(world, generator);
    }

    public net.minecraft.world.chunk.IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderHeightmap(world, world.getSeed(), generator);
    }
}
