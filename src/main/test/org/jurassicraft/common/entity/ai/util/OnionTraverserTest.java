package org.jurassicraft.common.entity.ai.util;

import net.minecraft.util.BlockPos;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

public class OnionTraverserTest
{
    @Test
    public void testIterator()
    {
        OnionTraverser traverser = new OnionTraverser(new BlockPos(100,100,100), 1);
        Queue<BlockPos> queue = get3x3BlockList();
        for (BlockPos pos : traverser)
        {
            BlockPos valid = queue.poll();
            assertEquals("Same", valid, pos);
            //System.out.println("valid=" + valid + ", pos=" + pos);
        }
        assertTrue(queue.isEmpty());
    }

    // Build blocks
    private Queue<BlockPos> get3x3BlockList()
    {
        Queue<BlockPos> list = new LinkedList<BlockPos>();

        list.add(new BlockPos(100, 100, 100));

        // Middle layer
        list.add(new BlockPos( 99, 100, 99));
        list.add(new BlockPos(100, 100, 99));
        list.add(new BlockPos(101, 100, 99));

        list.add(new BlockPos(101, 100, 100));
        list.add(new BlockPos(101, 100, 101));

        list.add(new BlockPos(100, 100, 101));
        list.add(new BlockPos( 99, 100, 101));

        list.add(new BlockPos( 99, 100, 100));

        // Bottom
        list.add(new BlockPos( 99, 99, 99));
        list.add(new BlockPos(100, 99, 99));
        list.add(new BlockPos(101, 99, 99));

        list.add(new BlockPos(101, 99, 100));
        list.add(new BlockPos(100, 99, 100));
        list.add(new BlockPos( 99, 99, 100));

        list.add(new BlockPos( 99, 99, 101));
        list.add(new BlockPos(100, 99, 101));
        list.add(new BlockPos(101, 99, 101));

        // Bottom
        list.add(new BlockPos( 99, 101, 99));
        list.add(new BlockPos(100, 101, 99));
        list.add(new BlockPos(101, 101, 99));

        list.add(new BlockPos(101, 101, 100));
        list.add(new BlockPos(100, 101, 100));
        list.add(new BlockPos( 99, 101, 100));

        list.add(new BlockPos( 99, 101, 101));
        list.add(new BlockPos(100, 101, 101));
        list.add(new BlockPos(101, 101, 101));

        return list;
    }
}