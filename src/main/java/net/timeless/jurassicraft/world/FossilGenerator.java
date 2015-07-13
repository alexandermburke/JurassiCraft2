package net.timeless.jurassicraft.world;

import java.util.List;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.block.BlockFossil;
import net.timeless.jurassicraft.dinosaur.Dinosaur;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.period.EnumTimePeriod;

public class FossilGenerator implements IWorldGenerator
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
        for (int i = 0; i < 32; i++)
        {
            int randPosX = chunkX + random.nextInt(16);
            int randPosY = random.nextInt(64);
            int randPosZ = chunkZ + random.nextInt(16);

            EnumTimePeriod period = EnumTimePeriod.CRETACEOUS;

            List<Dinosaur> dinos = JCEntityRegistry.getDinosaursFromPeriod(period);

            Dinosaur dinosaur = dinos.get(random.nextInt(dinos.size()));
            int meta = JurassiCraft.blockRegistry.getMetadata(dinosaur);

            new WorldGenMinable(JurassiCraft.blockRegistry.getFossilBlock(dinosaur).getDefaultState().withProperty(BlockFossil.VARIANT, meta), 5).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
        }
    }
}