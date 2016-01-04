package org.jurassicraft.common.world.jurdstrees.algorythms;

public class InsPCoord
{

    private final int x;
    private final int y;
    private final int z;
    private final int level;
    private final int code;
    private final int trunk;
    private int built;
    private final int rotation;
    private final int leaves;
    private final int type;

    public InsPCoord(int code, int type, int x, int y, int z, int level, int trunk, int rotation, int leaves)
    {

        this.code = code;
        this.level = level;
        this.trunk = trunk;
        this.built = 0;
        this.rotation = rotation;
        this.leaves = leaves;
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;

    }

    public int getCode()
    {
        return code;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getZ()
    {
        return z;
    }

    public int getLevel()
    {
        return level;
    }

    public int getTrunk()
    {
        return trunk;
    }

    public int getBuilt()
    {
        return built;
    }

    public void turnBuilt()
    {
        built = 1;
    }

    public int getRotation()
    {
        return rotation;
    }

    public int getLeaves()
    {
        return leaves;
    }

    public int getType()
    {
        return type;
    }

    public enum InsPType
    {

        branch, trunk, root, leaves;

        public static int getTypeIndex(InsPType type)
        {

            switch (type)
            {
                case trunk:
                    return 0;
                case branch:
                    return 1;
                case leaves:
                    return 2;
                case root:
                    return 3;

                default:
                    return 0;

            }

        }

    }

}
