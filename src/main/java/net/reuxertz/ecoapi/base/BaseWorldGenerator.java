package net.reuxertz.ecoapi.base;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public abstract class BaseWorldGenerator implements IWorldGenerator
{
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.getDimensionId())
        {
            case -1:
                generateNether(world, random, chunkX, chunkZ, chunkGenerator, chunkProvider);
                break;
            case 0:
                generateSurface(world, random, chunkX, chunkZ, chunkGenerator, chunkProvider);
                break;
            case 1:
                generateEnd(world, random, chunkX, chunkZ, chunkGenerator, chunkProvider);
                break;
        }
    }

    protected void generateEnd(World world, Random rand, int chunkX, int chunkZ, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {

    }

    protected void generateSurface(World world, Random rand, int chunkX, int chunkZ, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {

    }

    protected void generateNether(World world, Random rand, int chunkX, int chunkZ, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {

    }
}
