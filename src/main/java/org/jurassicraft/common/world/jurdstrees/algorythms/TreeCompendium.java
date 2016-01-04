package org.jurassicraft.common.world.jurdstrees.algorythms;

import org.jurassicraft.common.world.jurdstrees.algorythms.Feature.FeatureType;
import org.jurassicraft.common.world.jurdstrees.algorythms.TreeBlock.InsPoint;
import org.jurassicraft.common.world.jurdstrees.algorythms.TreeBlock.Rotation;

import java.util.ArrayList;

public class TreeCompendium
{
    private static final ArrayList<Tree> TreeList = new ArrayList<>();

    private static final ArrayList<Shape> ShapeList = new ArrayList<>();

    public static void addShapesToCompendium()
    {
        Shape shape;

        shape = new Shape(1); // basic shape with one block, 4 branch insert point and a trunk insert point

        shape.addInsPointWithLeaves(0, 1, 0, Rotation.none); // enables leaves, no rotation makes trunk leaves rotation
        shape.addLog(0, 2, 0);
        shape.addInsPoint(3, 0, 0); // enables branches
        shape.addInsPointWithLeaves(0, 4, 0, Rotation.none);// enables leaves, no rotation makes trunk leaves rotation
        shape.addLog(0, 5, 0);
        shape.addLog(0, 6, 0);
        shape.addInsPointWithLeaves(0, 7, 0, Rotation.none);
        shape.addInsPointWithTrunk(8, 0, 0, Rotation.none); // enables next trunk

        ShapeList.add(shape);

        shape = new Shape(2); // basic wood shape in a 3x3 pattern that has 2 levels that depend on height.

        shape.addLog(0, 0, 1, 3);
        shape.addLog(-1, 0, 0, 3);
        shape.addLog(1, 0, 0, 3);
        shape.addLog(0, 0, -1, 3);

        ShapeList.add(shape);

        shape = new Shape(3); // branch shape.

        shape.addLog(1, 0, 0);
        shape.addLog(2, 1, 0);
        shape.addInsPointWithLeaves(3, 1, 0, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(4, 1, -1);
        shape.addLog(4, 2, 1);
        shape.addLog(5, 0, -1);
        shape.addLog(5, 2, 1);
        shape.addLog(6, -1, 0);
        shape.addInsPointWithLeaves(6, 3, 2, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addInsPointWithLeaves(7, -1, 0, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addInsPointWithRotation(7, 3, 2, Rotation.east); // enables trunk, remember rotation.

        ShapeList.add(shape);

        shape = new Shape(4); // basic leaf shape for JC Tree.

        for (int i = 0; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                for (int k = -1; k <= 1; k++)
                {
                    shape.addLog(k, i, j);
                }
            }
        }

        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                shape.addLog(i, 2, j);
                // shape.addLog(i, -2, j);
                shape.addLog(i, j, 2);
                shape.addLog(i, j, -2);
            }
        }

        shape.addLog(2, 0, 0);
        shape.addLog(2, 1, 0);
        shape.addLog(2, -1, 0);
        shape.addLog(2, 0, 1);
        shape.addLog(2, 0, -1);
        shape.addLog(-2, 0, 0);
        shape.addLog(-2, 1, 0);
        shape.addLog(-2, -1, 0);
        shape.addLog(-2, 0, 1);
        shape.addLog(-2, 0, -1);

        ShapeList.add(shape);

        shape = new Shape(5); // Trunks leaves shape for JC Tree.

        shape.addLog(0, 1, 0);
        shape.addLog(0, -1, 0);
        shape.addLog(0, 0, 1);
        shape.addLog(0, 0, -1);
        shape.addLog(0, 2, 0);
        shape.addLog(0, -2, 0);
        shape.addLog(0, 0, 2);
        shape.addLog(0, 0, -2);
        shape.addLog(0, 1, 1);
        shape.addLog(0, 1, -1);
        shape.addLog(0, -1, 1);
        shape.addLog(0, -1, -1);
        shape.addLog(2, 1, 0);
        shape.addLog(2, -1, 0);
        shape.addLog(2, 0, 1);
        shape.addLog(2, 0, -1);
        shape.addLog(-2, 1, 0);
        shape.addLog(-2, -1, 0);
        shape.addLog(-2, 0, 1);
        shape.addLog(-2, 0, -1);

        ShapeList.add(shape);

        shape = new Shape(6); // basic branch shape, in line with axis.

        shape.addInsPointWithLeaves(1, 0, 0, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(2, 0, 0);
        shape.addInsPointWithLeaves(3, 2, -1, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(4, 0, -1);
        shape.addLog(4, 0, -2);
        shape.addLog(5, 1, -1);
        shape.addInsPointWithLeaves(5, 1, 0, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(6, 1, -2);
        shape.addInsPointWithLeaves(7, 1, -2, Rotation.east); // enables more branches, remember rotation.

        ShapeList.add(shape);

        shape = new Shape(7); // basic branch shape, slightly rotated.

        shape.addLog(1, 0, 1);
        shape.addLog(2, 0, 2);
        shape.addLog(2, 1, 3);
        shape.addInsPointWithLeaves(2, 1, 4, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(3, 1, 5);
        shape.addLog(4, 2, 6);
        shape.addInsPointWithLeaves(5, 3, 6, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(6, 3, 6);
        shape.addLog(3, 0, 2);
        shape.addLog(4, 0, 2);
        shape.addLog(5, 1, 2);
        shape.addLog(6, 1, 1);
        shape.addLog(7, 2, 1);
        shape.addInsPointWithLeaves(7, 2, 0, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(7, 1, -1);
        shape.addLog(8, 0, -1);
        shape.addLog(9, -1, -2);
        shape.addLog(8, 3, 2);
        shape.addLog(9, 4, 3);
        shape.addInsPointWithLeaves(10, 4, 3, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(11, 4, 3);
        shape.addLog(12, 5, 4);
        shape.addInsPointWithLeaves(13, 5, 4, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addInsPointWithRotation(14, 5, 4, Rotation.east); // enables more branches, remember rotation.

        ShapeList.add(shape);

        shape = new Shape(8);

        shape.addLog(1, 1, 1);
        shape.addLog(2, 1, 1);
        shape.addInsPointWithLeaves(3, 1, 1, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(4, 2, 2);
        shape.addLog(4, 2, 3);
        shape.addLog(4, 2, 4);
        shape.addInsPointWithLeaves(5, 1, 5, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(5, 1, 6);
        shape.addLog(5, 2, 2);
        shape.addLog(6, 3, 3);
        shape.addLog(7, 4, 4);
        shape.addInsPointWithLeaves(7, 5, 6, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(6, 3, 1);
        shape.addLog(7, 3, 0);
        shape.addInsPointWithLeaves(7, 3, 0, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addInsPointWithRotation(8, 3, 0, Rotation.east); // enables trunk, remember rotation.

        ShapeList.add(shape);

        shape = new Shape(9);

        shape.addLog(1, 0, 1);
        shape.addLog(2, 0, 2);
        shape.addInsPointWithLeaves(2, 1, 3, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(1, 3, 3);
        shape.addLog(3, 2, 3);
        shape.addLog(3, 2, 4);
        shape.addInsPointWithLeaves(3, 3, 5, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(2, 4, 6);
        shape.addLog(4, 4, 4);
        shape.addLog(5, 5, 4);
        shape.addLog(4, 5, 6);
        shape.addInsPointWithLeaves(5, 5, 7, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addInsPointWithRotation(6, 6, 8, Rotation.east); // enables trunk, remember rotation.

        ShapeList.add(shape);

        shape = new Shape(10);

        shape.addLog(1, 0, 1);
        shape.addLog(2, 1, 2);
        shape.addLog(3, 1, 3);
        shape.addLog(4, 1, 3);
        shape.addInsPointWithLeaves(5, 1, 3, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(3, 1, 4);
        shape.addLog(3, 1, 5);
        shape.addLog(4, 2, 6);
        shape.addInsPointWithLeaves(5, 3, 6, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addInsPointWithRotation(6, 3, 7, Rotation.east); // enables trunk, remember rotation.

        ShapeList.add(shape);

        shape = new Shape(11);
        shape.addLog(1, 1, 0);
        shape.addInsPointWithLeaves(2, 1, 0, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(3, 2, 0);
        shape.addInsPointWithLeaves(4, 2, 0, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addInsPointWithRotation(5, 3, 0, Rotation.east); // enables trunk, remember rotation.

        ShapeList.add(shape);

        shape = new Shape(12);

        shape.addLog(1, 1, 0);
        shape.addInsPointWithLeaves(2, 1, 0, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(3, 2, 0);
        shape.addInsPointWithLeaves(4, 2, 1, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addInsPointWithRotation(5, 3, 1, Rotation.east); // enables trunk, remember rotation.

        ShapeList.add(shape);

        shape = new Shape(13);

        shape.addLog(1, 1, 1);
        shape.addInsPointWithLeaves(2, 1, 1, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(3, 2, 2);
        shape.addInsPointWithLeaves(4, 2, 2, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addInsPointWithRotation(5, 3, 3, Rotation.east); // enables trunk, remember rotation.

        ShapeList.add(shape);

        shape = new Shape(14);

        shape.addLog(1, 1, 1);
        shape.addInsPointWithLeaves(2, 1, 2, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(3, 2, 3);
        shape.addInsPointWithLeaves(4, 2, 4, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addInsPointWithRotation(5, 3, 5, Rotation.east); // enables trunk, remember rotation.

        ShapeList.add(shape);

        shape = new Shape(15);

        shape.addLog(1, 1, 1);
        shape.addInsPointWithLeaves(2, 1, 2, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(3, 2, 3);
        shape.addInsPointWithLeaves(3, 2, 4, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addInsPointWithRotation(4, 3, 5, Rotation.east); // enables trunk, remember rotation.

        ShapeList.add(shape);

        shape = new Shape(16);

        shape.addLog(1, 1, 1);
        shape.addInsPointWithLeaves(1, 1, 2, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(2, 2, 3);
        shape.addInsPointWithLeaves(2, 2, 4, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addInsPointWithRotation(3, 3, 5, Rotation.east); // enables trunk, remember rotation.

        ShapeList.add(shape);

        shape = new Shape(17);

        shape.addLog(1, 1, 1);
        shape.addInsPointWithLeaves(1, 1, 2, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(2, 2, 3);
        shape.addInsPointWithLeaves(2, 2, 4, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addInsPointWithRotation(2, 3, 5, Rotation.east); // enables trunk, remember rotation.

        ShapeList.add(shape);

        shape = new Shape(18);

        shape.addLog(1, 1, 1);
        shape.addInsPointWithLeaves(1, 1, 2, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addLog(1, 2, 3);
        shape.addInsPointWithLeaves(1, 2, 4, Rotation.east); // enables leaves, remember to put rotation. East is the basic one.
        shape.addInsPointWithRotation(1, 3, 5, Rotation.east); // enables trunk, remember rotation.

        ShapeList.add(shape);

        shape = new Shape(19);

        shape.addLog(-2, 0, 0);
        shape.addLog(-1, 0, 0);
        shape.addLog(-1, 1, 0);
        shape.addLog(0, -1, 0);
        shape.addLog(0, 0, 1);
        shape.addLog(0, 0, 2);
        shape.addLog(0, 0, -1);
        shape.addLog(0, 0, -2);
        shape.addLog(0, 1, 1);
        shape.addLog(0, 1, 0);
        shape.addLog(0, 1, -1);
        shape.addLog(0, 2, 0);
        shape.addLog(1, 0, 0);
        shape.addLog(1, 1, 0);
        shape.addLog(2, 0, 0);

        ShapeList.add(shape);

    }

    public static void registerTrees()
    {

        Tree tree;

        tree = new Tree(2, 7, 3);
        tree.addFeatureList(new int[] { 1, 1 }, FeatureType.Trunk);
        tree.addFeatureList(new int[] { 3, 6, 7, 8, 9, 10 }, FeatureType.Branch);
        tree.addFeatureList(new int[] { 5, 6 }, FeatureType.Fruit);
        tree.addFeatureList(new int[] { 4, 4 }, FeatureType.leaves);
        tree.addFeatureList(new int[] { 5, 5 }, FeatureType.TrunkLeaves);
        tree.addFeatureList(new int[] { 2, 2 }, FeatureType.wood);

        TreeList.add(tree);

        tree = new Tree(1, 8, 4);
        tree.addFeatureList(new int[] { 1, 1 }, FeatureType.Trunk);
        tree.addFeatureList(new int[] { 11, 12, 13, 14, 15, 16, 17, 18 }, FeatureType.Branch);
        tree.addFeatureList(new int[] { 5, 6 }, FeatureType.Fruit);
        tree.addFeatureList(new int[] { 19, 19 }, FeatureType.leaves);
        tree.addFeatureList(new int[] { 5, 5 }, FeatureType.TrunkLeaves);
        tree.addFeatureList(new int[] { 2, 2 }, FeatureType.wood);

        TreeList.add(tree);

        tree = new Tree(0, 8, 4, 3, 3, 5);
        tree.addFeatureList(new int[] { 1, 1 }, FeatureType.Trunk);
        tree.addFeatureList(new int[] { 11, 12, 13, 14, 15, 16, 17, 18 }, FeatureType.Branch);
        tree.addFeatureList(new int[] { 5, 6 }, FeatureType.Fruit);
        tree.addFeatureList(new int[] { 19, 19 }, FeatureType.leaves);
        tree.addFeatureList(new int[] { 5, 5 }, FeatureType.TrunkLeaves);
        tree.addFeatureList(new int[] { 2, 2 }, FeatureType.wood);

        TreeList.add(tree);
    }

    public static Shape getShapeFromCode(int code)
    {

        for (Shape shape : ShapeList)
        {

            if (shape.getCode() == code)
            {
                return shape;
            }

        }

        return null;

    }

    public static Tree getTreeFromCode(int code)
    {

        for (Tree tree : TreeList)
        {

            if (tree.getCode() == code)
            {
                return tree.getBaseCopy();
            }

        }

        return null;

    }

    public static Shape getRotatedShapeFromCode(int code, Rotation rotation)
    {

        Shape rotatedShape = new Shape(code);

        for (Shape shape : ShapeList)
        {

            if (shape.getCode() == code)
            {

                for (TreeBlock block : shape.blocksList)
                {

                    if (block instanceof InsPoint)
                    {

                        InsPoint point = (InsPoint) block;

                        switch (rotation)
                        {

                            case north:
                                rotatedShape.addInsPointAskTrunkAndLeaves(point.getZ(), point.getY(), -point.getX(), point.getLevel(), Rotation.rotate90(point.rotation), point.allowTrunk, point.getLeaves());
                                break;
                            case south:
                                rotatedShape.addInsPointAskTrunkAndLeaves(-point.getZ(), point.getY(), point.getX(), point.getLevel(), Rotation.rotate270(point.rotation), point.allowTrunk, point.getLeaves());
                                break;
                            case west:
                                rotatedShape.addInsPointAskTrunkAndLeaves(-point.getX(), point.getY(), -point.getZ(), point.getLevel(), Rotation.rotate180(point.rotation), point.allowTrunk, point.getLeaves());
                                break;
                            default:
                                rotatedShape.addInsPointAskTrunkAndLeaves(point.getX(), point.getY(), point.getZ(), point.getLevel(), point.rotation, point.allowTrunk, point.getLeaves());
                                break;

                        }

                    }
                    else
                    {

                        switch (rotation)
                        {

                            case north:
                                rotatedShape.addLog(block.getZ(), block.getY(), -block.getX(), block.level);
                                break;
                            case south:
                                rotatedShape.addLog(-block.getZ(), block.getY(), block.getX(), block.level);
                                break;
                            case west:
                                rotatedShape.addLog(-block.getX(), block.getY(), -block.getZ(), block.level);
                                break;
                            default:
                                rotatedShape.addLog(block.getX(), block.getY(), block.getZ(), block.level);
                                break;

                        }

                    }

                }

                return rotatedShape;

            }

        }

        return null;

    }

}
