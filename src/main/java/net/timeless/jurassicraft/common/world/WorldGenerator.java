package net.timeless.jurassicraft.common.world;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.block.JCBlockRegistry;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.handler.FossilHandler;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

import java.util.List;
import java.util.Random;

public class WorldGenerator implements IWorldGenerator
{
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if (world.provider.getDimensionId() == 0)
        {
            generateOverworld(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    public void generateOverworld(World world, Random random, int chunkX, int chunkZ)
    {
        for (int i = 0; i < 20; i++)
        {
            int randPosX = random.nextInt(16) + (chunkX * 16);
            int randPosZ = random.nextInt(16) + (chunkZ * 16);

            int worldHeight = world.getHorizon(new BlockPos(randPosX, 0, randPosZ)).getY();

            if (worldHeight <= 0)
                worldHeight = 4;

            int randPosY = random.nextInt(worldHeight);

            BlockPos pos = new BlockPos(randPosX, randPosY, randPosZ);

            EnumTimePeriod period = FossilHandler.getTimePeriod(world, pos);

            List<Dinosaur> dinos = JCEntityRegistry.getDinosaursFromPeriod(period);

            Dinosaur dinosaur = dinos.get(random.nextInt(dinos.size()));
            int meta = JurassiCraft.blockRegistry.getMetadata(dinosaur);

            new WorldGenMinable(JurassiCraft.blockRegistry.getFossilBlock(dinosaur).getStateFromMeta(meta), 5).generate(world, random, pos);
        }

        int x = random.nextInt(16) + (chunkX * 16);
        int y = random.nextInt(20);
        int z = random.nextInt(16) + (chunkZ * 16);

        (new WorldGenMinable(JCBlockRegistry.amber_ore.getDefaultState(), 6)).generate(world, random, new BlockPos(x, y, z));
    }
}