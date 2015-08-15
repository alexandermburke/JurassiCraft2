package net.timeless.jurassicraft.common.world.jurdstrees.algorythms;

public class TreeBlock
{

    protected int level;
    protected int x, y, z;

    public TreeBlock(int x, int y, int z, int level)
    {

        this.level = level;
        this.x = x;
        this.y = y;
        this.z = z;

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

    public boolean equals(TreeBlock TB)
    {

        if (TB.getX() == x && TB.getY() == y && TB.getZ() == z)
            return true;

        return false;

    }

    public TreeBlock getCopy()
    {

        return new TreeBlock(x, y, z, level);

    }

    public enum Rotation
    {
        none, north, south, east, west;

        public static int getRotationIndex(Rotation rotation)
        {

            if (rotation == null)
                return 0;

            switch (rotation)
            {

                case east:
                    return 1;

                case north:
                    return 2;

                case south:
                    return 4;

                case west:
                    return 3;

                default:
                    return 0;

            }

        }

        public static Rotation getRotationFromIndex(int i)
        {

            switch (i)
            {

                case 0:
                    return Rotation.none;
                case 1:
                    return Rotation.east;
                case 2:
                    return Rotation.north;
                case 3:
                    return Rotation.west;
                case 4:
                    return Rotation.south;
                default:
                    return null;

            }

        }

        public static Rotation rotate90(Rotation rotation)
        {

            int index = getRotationIndex(rotation);

            if (index == 0)
                return Rotation.none;

            if (index < 4)
                index++;
            else
                index = 1;

            return getRotationFromIndex(index);
        }

        public static Rotation rotate180(Rotation rotation)
        {

            return rotate90(rotate90(rotation));

        }

        public static Rotation rotate270(Rotation rotation)
        {

            return rotate90(rotate180(rotation));

        }

    }

    public class InsPoint extends TreeBlock
    {

        public boolean allowTrunk = false;
        public boolean growLeaves;
        public Rotation rotation;

        public InsPoint(int x, int y, int z, int level, Rotation rot)
        {

            super(x, y, z, level);
            this.level = level;
            allowTrunk = false;
            growLeaves = false;
            rotation = rot;

        }

        public InsPoint(int x, int y, int z, int level, Rotation rot, boolean allowTrunk, boolean leaves)
        {

            super(x, y, z, level);
            this.level = level;
            this.allowTrunk = allowTrunk;
            growLeaves = leaves;
            this.rotation = rot;

        }


        public int getLevel()
        {
            return level;
        }

        public boolean getLeaves()
        {
            return growLeaves;
        }

    }

}
