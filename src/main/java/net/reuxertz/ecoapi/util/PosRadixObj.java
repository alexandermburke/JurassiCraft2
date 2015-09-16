package net.reuxertz.ecoapi.util;


import net.minecraft.util.BlockPos;

import java.util.HashMap;

public class PosRadixObj
{
    public HashMap<Integer, HashMap<Integer, HashMap<Integer, Object>>> radixArray =
            new HashMap<Integer, HashMap<Integer, HashMap<Integer, Object>>>();

    public PosRadixObj()
    {

    }

    public Object get(BlockPos pos)
    {
        return this.radixArray.get(pos.getX()).get(pos.getY()).get(pos.getZ());
    }
    public Object getTry(BlockPos pos)
    {
        if (this.contains(pos))
            return this.get(pos);

        return null;
    }

    public boolean contains(BlockPos pos)
    {
        if (!radixArray.containsKey(pos.getX()))
            return false;
        if (!radixArray.containsKey(pos.getY()))
            return false;
        return radixArray.containsKey(pos.getZ());
    }

    public void put(BlockPos pos, Object o)
    {
        if (!radixArray.containsKey(pos.getX()))
            radixArray.put(pos.getX(), new HashMap<Integer, HashMap<Integer, Object>>());

        //HashMap<Integer, HashMap<Integer, Object>> yArray = radixArray.get(pos.getX());

        if (!radixArray.get(pos.getX()).containsKey(pos.getY()))
            radixArray.get(pos.getX()).put(pos.getY(), new HashMap<Integer, Object>());

        //HashMap<Integer, Object> zArray = radixArray.get(pos.getX()).get(pos.getY());

        //if (!zArray.containsKey(pos.getZ()))
        radixArray.get(pos.getX()).get(pos.getY()).put(pos.getZ(), o);
    }

    public void remove(BlockPos pos)
    {
        if (radixArray.containsKey(pos.getX()))
        {
            if (radixArray.get(pos.getX()).containsKey(pos.getY()))
            {
                if (radixArray.get(pos.getX()).get(pos.getY()).containsKey(pos.getZ()))
                {
                    radixArray.get(pos.getX()).get(pos.getY()).remove(pos.getZ());
                }

                if (radixArray.get(pos.getX()).get(pos.getY()).size() == 0)
                    radixArray.get(pos.getX()).remove(pos.getY());
            }

            if (radixArray.get(pos.getX()).size() == 0)
                radixArray.remove(pos.getX());
        }
    }

}
