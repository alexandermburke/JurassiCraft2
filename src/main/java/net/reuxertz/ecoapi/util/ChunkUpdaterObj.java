package net.reuxertz.ecoapi.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.reuxertz.ecoapi.EcoAPI;

public abstract class ChunkUpdaterObj
{
    protected float probability = .05f;
    protected int minHeight = 0, maxHeight = 256;

    public abstract void updateBlock(World w, IBlockState state, BlockPos pos);

    public ChunkUpdaterObj(float probability, int minHeight, int maxHeight)
    {
        this();
        this.probability = probability;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }
    public ChunkUpdaterObj()
    {

    }

    public void updateChunk(Chunk c)
    {
        for (int x = 0; x < 16; x++)
            for (int z = 0; z < 16; z++)
                if (EcoAPI.RND.nextDouble() < this.probability)
                    for (int y = this.minHeight; y < this.maxHeight; y++)
                    {
                        BlockPos pos = new BlockPos(c.xPosition * 16, y, c.zPosition * 16);
                        this.updateBlock(c.getWorld(), c.getBlockState(pos), pos);
                    }
    }
}
