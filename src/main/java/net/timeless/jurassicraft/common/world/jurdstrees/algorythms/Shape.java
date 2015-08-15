package net.timeless.jurassicraft.common.world.jurdstrees.algorythms;

import net.timeless.jurassicraft.common.world.jurdstrees.algorythms.TreeBlock.Rotation;

import java.util.ArrayList;

/**
 * Please remember that all shapes are by definition facing east (positive X). Also remember south is positive Z.
 *
 * @author Jordi
 */
public class Shape
{

    public ArrayList<TreeBlock> blocksList = new ArrayList<TreeBlock>();
    private int shapeCode;

    public Shape(int code)
    {
        shapeCode = code;
        addLog(0, 0, 0);
    }

    public boolean addLog(int x, int y, int z, int level)
    {

        TreeBlock currentBlock = new TreeBlock(x, y, z, level);

        for (TreeBlock TB : blocksList)
        {
            if (TB.equals(currentBlock))
                return false;
        }
        blocksList.add(new TreeBlock(x, y, z, level));
        return true;

    }

    public boolean addLog(int x, int y, int z)
    {

        TreeBlock currentBlock = new TreeBlock(x, y, z, 0);

        for (TreeBlock TB : blocksList)
        {
            if (TB.equals(currentBlock))
                return false;
        }

        blocksList.add(new TreeBlock(x, y, z, 0));
        return true;

    }

    public boolean addInsPoint(int x, int y, int z, int level)
    {

        TreeBlock currentBlock = new TreeBlock(x, y, z, level);

        for (TreeBlock TB : blocksList)
        {
            if (TB.equals(currentBlock))
                return false;
        }

        blocksList.add(currentBlock.new InsPoint(x, y, z, level, Rotation.none));
        return true;

    }

    public boolean addInsPointWithRotation(int x, int y, int z, int level, Rotation rotation)
    {

        TreeBlock currentBlock = new TreeBlock(x, y, z, level);

        for (TreeBlock TB : blocksList)
        {
            if (TB.equals(currentBlock))
                return false;
        }

        blocksList.add(currentBlock.new InsPoint(x, y, z, level, rotation));
        return true;

    }

    public boolean addInsPointWithTrunk(int x, int y, int z, int level, Rotation rotation)
    {

        TreeBlock currentBlock = new TreeBlock(x, y, z, level);

        for (TreeBlock TB : blocksList)
        {
            if (TB.equals(currentBlock))
                return false;
        }

        blocksList.add(currentBlock.new InsPoint(x, y, z, level, rotation, true, false));
        return true;

    }

    public boolean addInsPointWithLeaves(int x, int y, int z, Rotation rotation)
    {

        return addInsPointAskTrunkAndLeaves(x, y, z, 0, rotation, false, true);

    }

    public boolean addInsPointAskTrunkAndLeaves(int x, int y, int z, int level, Rotation rotation, boolean trunk, boolean leaves)
    {

        TreeBlock currentBlock = new TreeBlock(x, y, z, level);

        for (TreeBlock TB : blocksList)
        {
            if (TB.equals(currentBlock))
                return false;
        }

        blocksList.add(currentBlock.new InsPoint(x, y, z, level, rotation, trunk, leaves));
        return true;

    }

    public int getCode()
    {
        return shapeCode;
    }

}
