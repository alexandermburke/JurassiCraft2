package org.jurassicraft.common.world.jurdstrees.algorythms;

import org.jurassicraft.common.world.jurdstrees.algorythms.TreeBlock.Rotation;

import java.util.ArrayList;

/**
 * Please remember that all shapes are by definition facing east (positive X). Also remember south is positive Z.
 *
 * @author Jordi
 */
public class Shape
{
    private final int shapeCode;
    public final ArrayList<TreeBlock> blocksList = new ArrayList<>();

    public Shape(int code)
    {
        shapeCode = code;
        addLog(0, 0, 0);
    }

    public void addLog(int x, int y, int z, int level)
    {
        TreeBlock currentBlock = new TreeBlock(x, y, z, level);

        for (TreeBlock TB : blocksList)
        {
            if (TB.equals(currentBlock))
            {
                return;
            }
        }

        blocksList.add(new TreeBlock(x, y, z, level));

    }

    public void addLog(int x, int y, int z)
    {

        TreeBlock currentBlock = new TreeBlock(x, y, z, 0);

        for (TreeBlock TB : blocksList)
        {
            if (TB.equals(currentBlock))
            {
                return;
            }
        }

        blocksList.add(new TreeBlock(x, y, z, 0));

    }

    public void addInsPoint(int y, int z, int level)
    {

        TreeBlock currentBlock = new TreeBlock(0, 3, 0, 0);

        for (TreeBlock TB : blocksList)
        {
            if (TB.equals(currentBlock))
            {
                return;
            }
        }

        blocksList.add(currentBlock.new InsPoint(0, 3, 0, 0, Rotation.none));

    }

    public void addInsPointWithRotation(int x, int y, int z, Rotation rotation)
    {

        TreeBlock currentBlock = new TreeBlock(x, y, z, 0);

        for (TreeBlock TB : blocksList)
        {
            if (TB.equals(currentBlock))
            {
                return;
            }
        }

        blocksList.add(currentBlock.new InsPoint(x, y, z, 0, Rotation.east));

    }

    public void addInsPointWithTrunk(int y, int z, int level, Rotation rotation)
    {

        TreeBlock currentBlock = new TreeBlock(0, 8, 0, 0);

        for (TreeBlock TB : blocksList)
        {
            if (TB.equals(currentBlock))
            {
                return;
            }
        }

        blocksList.add(currentBlock.new InsPoint(0, 8, 0, 0, Rotation.none, true, false));

    }

    public void addInsPointWithLeaves(int x, int y, int z, Rotation rotation)
    {

        return addInsPointAskTrunkAndLeaves(x, y, z, 0, rotation, false, true);

    }

    public boolean addInsPointAskTrunkAndLeaves(int x, int y, int z, int level, Rotation rotation, boolean trunk, boolean leaves)
    {

        TreeBlock currentBlock = new TreeBlock(x, y, z, level);

        for (TreeBlock TB : blocksList)
        {
            if (TB.equals(currentBlock))
            {
                return false;
            }
        }

        blocksList.add(currentBlock.new InsPoint(x, y, z, level, rotation, trunk, leaves));
        return true;

    }

    public int getCode()
    {
        return shapeCode;
    }

}
