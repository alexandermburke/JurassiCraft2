package net.reuxertz.ecoapi.util;

import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class ChunkRadixObj
{
    public PosRadixObj chunkRadix = new PosRadixObj();

    public Object get(Chunk c, BlockPos p)
    {
        return chunkRadix.get(new BlockPos(c.xPosition, 0, c.zPosition));
    }
    public Object getTry(Chunk c, BlockPos p)
    {
        if (this.contains(c, p))
            return this.get(c, p);

        return null;
    }

    public void put(Chunk c, BlockPos p, Object o)
    {
        PosRadixObj npro = new PosRadixObj();
        npro.put(p, o);
        this.chunkRadix.put(new BlockPos(c.xPosition, 0, c.zPosition), npro);
    }
    public void remove(Chunk c, BlockPos p, Object o)
    {
        if (!chunkRadix.contains(new BlockPos(c.xPosition, 0, c.zPosition)))
            return;

        this.chunkRadix.remove(p);
    }

    public boolean contains(Chunk c, BlockPos p)
    {
        if (chunkRadix.contains(new BlockPos(c.xPosition, 0, c.zPosition)))
            return (((PosRadixObj)chunkRadix.get(new BlockPos(c.xPosition, 0, c.zPosition))).contains(p));

        return false;
    }
}
