package net.reuxertz.ecoapi.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.List;

public class BlockReplaceConditions
{
    public List<Class> biomeClasses;
    public int lowerHeight = 0, upperHeight = 256;
    public IBlockState oldBlockState, newBlockState;
    public Block oldBlock;
    public BlockReplaceConditions nextConditions;
    public boolean notFlagBiome = false;

    public BlockReplaceConditions setHeight(int low, int high)
    {
        this.lowerHeight = Math.max(0, low);
        this.upperHeight = Math.min(255, high);
        return this;
    }

    public boolean isBiomeSatisfied(World w, Chunk c, int x, int z)
    {
        BlockPos pos = new BlockPos(x, 64, z);
        Class found = null;
        if (biomeClasses != null)
        {
            for (Class bc : biomeClasses)
            {
                if (this.notFlagBiome)
                {
                    if (c.getBiome(pos, w.getWorldChunkManager()).getBiomeClass() == bc)
                    {
                        found = bc;
                        break;
                    }
                }
                else
                {
                    if (c.getBiome(pos, w.getWorldChunkManager()).getBiomeClass() == bc)
                    {
                        found = bc;
                        break;
                    }
                }

            }


            if (this.notFlagBiome)
            {
                if (found != null)
                    return false;
            }
            else
            {
                if (found == null)
                    return false;
            }
        }

        return true;
    }
    public boolean positionSatisfied(World w, Chunk c, IBlockState state, BlockPos pos)
    {
        return true;
    }

    public BlockReplaceConditions setNotFlagBiome()
    {
        this.notFlagBiome = true;
        return this;
    }

    public BlockReplaceConditions(IBlockState oldblock, IBlockState newBlock)
    {
        this.oldBlockState = oldblock;
        this.newBlockState = newBlock;
    }
    public BlockReplaceConditions(Block oldblock, IBlockState newBlock)
    {
        this.oldBlock = oldblock;
        this.newBlockState = newBlock;
    }
    public BlockReplaceConditions(Block oldblock, IBlockState newBlock, Class[] biomeClasses)
    {
        this(oldblock, newBlock);

        if (biomeClasses != null)
        {
            this.biomeClasses = new ArrayList<Class>();
            for (Class c : biomeClasses)
                this.biomeClasses.add(c);
        }
    }
    public BlockReplaceConditions(Block oldblock, IBlockState newBlock, Class[] biomeClasses, BlockReplaceConditions nextCond)
    {
        this(oldblock, newBlock, biomeClasses);
        this.nextConditions = nextCond;
    }

    public void attemptPerform(World w, Chunk c, IBlockState state, BlockPos pos)
    {
        this._attemptPerform(w, c, state, pos);

        if (this.nextConditions != null)
            this.nextConditions.attemptPerform(w, c, state, pos);
    }

    protected void _attemptPerform(World w, Chunk c, IBlockState state, BlockPos pos)
    {
        this._attemptPerform(w, c, state, pos, true);
    }
    protected void _attemptPerform(World w, Chunk c, IBlockState state, BlockPos pos, boolean cont)
    {
        if (pos.getY() < lowerHeight || pos.getY() > upperHeight)
            return;

        boolean setState = false;

        if (this.oldBlockState != null && this.oldBlockState.getBlock() == state.getBlock() &&
                this.oldBlockState.getBlock().getMetaFromState(this.oldBlockState) == state.getBlock().getMetaFromState(state))
            setState = true;

        if (this.oldBlock != null && this.oldBlock == state.getBlock())
            setState = true;

        if (!setState)
            return;


        if (state.getBlock() == Blocks.water && !setState)
        {
            int a = 7;
        }

        if (setState && (w == null || pos == null || this.newBlockState == null))
        {
            int a = 7;
        }

        if (setState && this.positionSatisfied(w, c, state, pos))
        {
            w.setBlockState(pos, this.newBlockState, 3);

            if (cont)
            {
                //this._attemptPerform(w, c, state, pos.south(), false);
                //this._attemptPerform(w, c, state, pos.north(), false);
                //this._attemptPerform(w, c, state, pos.east(), false);
                //this._attemptPerform(w, c, state, pos.west(), false);
            }
        }
    }
}
