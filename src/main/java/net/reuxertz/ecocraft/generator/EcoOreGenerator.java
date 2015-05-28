package net.reuxertz.ecocraft.generator;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.reuxertz.ecocraft.core.EcoRegistry;

import java.util.Random;

public class EcoOreGenerator extends BaseWorldGenerator {

    @Override
    protected void generateSurface(World world, Random rand, int chunkX, int chunkZ, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        for(int k = 0; k < 50; k++){
            int firstBlockXCoord = chunkX * 16 + rand.nextInt(16);
            int firstBlockYCoord = rand.nextInt(80);
            int firstBlockZCoord = chunkZ * 16 + rand.nextInt(16);

            (new WorldGenMinable(EcoRegistry.copperOre.getDefaultState(), 13)).generate(world, rand, new BlockPos(firstBlockXCoord, firstBlockYCoord, firstBlockZCoord));
        }
    }
}
