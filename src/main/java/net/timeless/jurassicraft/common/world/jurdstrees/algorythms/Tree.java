package net.timeless.jurassicraft.common.world.jurdstrees.algorythms;

import net.minecraft.block.Block;
import net.timeless.jurassicraft.common.block.JCBlockRegistry;
import net.timeless.jurassicraft.common.world.jurdstrees.algorythms.Feature.FeatureType;
import net.timeless.jurassicraft.common.world.jurdstrees.algorythms.InsPCoord.InsPType;
import net.timeless.jurassicraft.common.world.jurdstrees.algorythms.TreeBlock.InsPoint;
import net.timeless.jurassicraft.common.world.jurdstrees.algorythms.TreeBlock.Rotation;

import java.util.ArrayList;
import java.util.Random;

public class Tree
{

    // unique identifier.
    private int TreeCode;

    // Features that can be built in the tree in the form of arrays of codes. This is used to generate a random tree.
    private int[] TrunkList;
    private int[] BranchList;
    private int[] WoodList;
    private int[] LeafList;
    private int[] TrunkLeafList;
    private int[] FruitList;

    private int penaltyPerHeight;
    private int lowerBranchLevel;

    private int age, maxAge;
    private int maxBranchLevel;
    private int maxTrunkHeight;

    private ArrayList<InsPCoord> insPList = new ArrayList<InsPCoord>();

    public Tree(int code, int maxAge, int maxBranchLength)
    {

        TreeCode = code;
        this.maxAge = maxAge;
        this.maxBranchLevel = maxBranchLength;
        penaltyPerHeight = 3;
        lowerBranchLevel = 0;
        maxTrunkHeight = 30;

    }

    public Tree(int code, int maxAge, int maxBranchLength, int penalty, int lbl, int mtheight)
    {

        TreeCode = code;
        this.maxAge = maxAge;
        this.maxBranchLevel = maxBranchLength;
        penaltyPerHeight = penalty;
        lowerBranchLevel = lbl;
        maxTrunkHeight = mtheight;

    }

    public static Block getBlocksFromCode(int code)
    {


        return JCBlockRegistry.woods[code];


    }

    public static Block getLeavesFromCode(int code)
    {

        return JCBlockRegistry.leaves[code];

    }

    public int getCode()
    {
        return TreeCode;
    }

    public Tree getBaseCopy()
    {

        Tree tree = new Tree(TreeCode, maxAge, maxBranchLevel);

        tree.addFeatureList(TrunkList, FeatureType.Trunk);
        tree.addFeatureList(BranchList, FeatureType.Branch);
        tree.addFeatureList(FruitList, FeatureType.Fruit);
        tree.addFeatureList(LeafList, FeatureType.leaves);
        tree.addFeatureList(WoodList, FeatureType.wood);
        tree.addFeatureList(TrunkLeafList, FeatureType.TrunkLeaves);

        return tree;
    }

    public boolean hasBeenGenerated()
    {

        if (insPList.size() != 0)
            return true;

        return false;

    }

    public void addFeatureList(int[] featureList, FeatureType type)
    {

        switch (type)
        {

            case Branch:
                BranchList = featureList;
                break;
            case Fruit:
                FruitList = featureList;
                break;
            case Trunk:
                TrunkList = featureList;
                break;
            case leaves:
                LeafList = featureList;
                break;
            case wood:
                WoodList = featureList;
                break;
            case TrunkLeaves:
                TrunkLeafList = featureList;
                break;
            default:
                break;
        }

    }

    public boolean generateTree()
    {

        if (TrunkList == null || BranchList == null || WoodList == null || LeafList == null || FruitList == null || TrunkLeafList == null)
            return false;

        Random random = new Random();

        // adding the first insert point to the tree. It is placed above the main block (supposedly the TileEntity).
        insPList.add(new InsPCoord(TrunkList[random.nextInt(TrunkList.length)], InsPType.getTypeIndex(InsPType.trunk), 0, 0, 0, 0, 1, 0, 0, 0));

		/*
         * ROTATION AND SHAPE ARE DETERMINED IN THE INSERTPOINT BEFORE THE FEATURE. ALSO REMEMBER THAT SHAPES ARE ALWAYS FACING EAST AT THE BEGGINING.
		 */

        // generating the tree to the maximum level of complexity, AKA the maximum number of added levels you will find.
        for (int i = 1; i < maxAge; i++)
        {

            // Every level we refresh the list size but we don't surpass this
            // point because new levels must not be detected, otherwise this
            // would end in an infinite loop.
            int size = insPList.size();

            // every level we want to iterate over the list of insert points and add a new level of complexity that will prolong the main trunk and branches. We
            // iterate only till the list's last known number of levels without touching the ones we add here.
            for (int j = 0; j < size; j++)
            {

                if (insPList.get(j).getBuilt() == 0)
                {

                    // if there is a possible trunk placing, get the random code of the trunk in this point and get the corresponding shape.
                    Shape shape;

                    if (insPList.get(j).getTrunk() == 1)
                    {

                        if (insPList.get(j).getY() < maxTrunkHeight)
                        {

                            shape = TreeCompendium.getRotatedShapeFromCode(insPList.get(j).getCode(), Rotation.getRotationFromIndex(insPList.get(j).getRotation()));

                            addNewInsertPoints(random, i, j, shape, InsPType.trunk, false);
                        }

                    } else
                    {

                        if (insPList.get(j).getLevel() < maxBranchLevel)
                        {

                            // If trunk is not allowed (it's only for branches), you need to place the new shape several times. so you want to get the shape in
                            // all directions.

                            if (insPList.get(j).getRotation() != 0)
                            {

                                if (insPList.get(j).getLeaves() == 0)
                                {

                                    shape = TreeCompendium.getRotatedShapeFromCode(insPList.get(j).getCode(), Rotation.getRotationFromIndex(insPList.get(j).getRotation()));

                                    addNewInsertPoints(random, i, j, shape, InsPType.branch, false); // add the basic rotated shape

                                }
                            }

                            if (insPList.get(j).getRotation() == 0)
                            {

                                // no rotation means 4 branches. Let's add their new insert points for every shape

                                shape = TreeCompendium.getRotatedShapeFromCode(insPList.get(j).getCode(), Rotation.east);
                                addNewInsertPoints(random, i, j, shape, InsPType.branch, false); // add the basic rotated shape

                                shape = TreeCompendium.getRotatedShapeFromCode(insPList.get(j).getCode(), Rotation.north);
                                addNewInsertPoints(random, i, j, shape, InsPType.branch, false);

                                shape = TreeCompendium.getRotatedShapeFromCode(insPList.get(j).getCode(), Rotation.west);
                                addNewInsertPoints(random, i, j, shape, InsPType.branch, false);

                                shape = TreeCompendium.getRotatedShapeFromCode(insPList.get(j).getCode(), Rotation.south);
                                addNewInsertPoints(random, i, j, shape, InsPType.branch, false);

                            }

                        }

                        if (insPList.get(j).getLevel() >= maxBranchLevel)
                        {

                            Shape leavesShape;

                            if (insPList.get(j).getRotation() != 0)
                            {

                                leavesShape = TreeCompendium.getRotatedShapeFromCode(insPList.get(j).getCode(), Rotation.getRotationFromIndex(insPList.get(j).getRotation()));

                                addNewInsertPoints(random, i, j, leavesShape, InsPType.branch, true);

                            } else
                            {

                                leavesShape = TreeCompendium.getRotatedShapeFromCode(insPList.get(j).getCode(), Rotation.east);
                                addNewInsertPoints(random, i, j, leavesShape, InsPType.branch, true); // add the basic rotated shape

                                leavesShape = TreeCompendium.getRotatedShapeFromCode(insPList.get(j).getCode(), Rotation.north);
                                addNewInsertPoints(random, i, j, leavesShape, InsPType.branch, true);

                                leavesShape = TreeCompendium.getRotatedShapeFromCode(insPList.get(j).getCode(), Rotation.west);
                                addNewInsertPoints(random, i, j, leavesShape, InsPType.branch, true);

                                leavesShape = TreeCompendium.getRotatedShapeFromCode(insPList.get(j).getCode(), Rotation.south);
                                addNewInsertPoints(random, i, j, leavesShape, InsPType.branch, true);

                            }

                        }

                    }

                }

            }

        }

        return true;

    }

    private void addNewInsertPoints(Random random, int i, int j, Shape shape, InsPType type, boolean addLeavesInsPOnly)
    {
        // Iterate over the shape to find the next insert point
        for (TreeBlock block : shape.blocksList)
        {

            if (block instanceof InsPoint)
            {

                InsPoint insertp = (InsPoint) block;

                int xC = insPList.get(j).getX() + insertp.getX();
                int yC = insPList.get(j).getY() + insertp.getY();
                int zC = insPList.get(j).getZ() + insertp.getZ();

                // if the next insert point allows for trunk, then trunk rotation does not matter, it can be random.
                if (insertp.allowTrunk && addLeavesInsPOnly == false)
                {

                    // Rotation of the insert point is corrected in the Tree Compendium method that gives the rotated shape.
                    int newRotation = 0;

                    if (insertp.rotation == Rotation.none)
                    {

                        newRotation = random.nextInt(4) + 1;

                    } else
                    {

                        newRotation = Rotation.getRotationIndex(insertp.rotation);

                    }
                    // remember to set the previous insert point to BUILT.
                    insPList.get(j).turnBuilt();

                    // add the new insert point generating the rotation and shape for the next feature at random.
                    insPList.add(new InsPCoord(TrunkList[new Random().nextInt(TrunkList.length)], InsPType.getTypeIndex(type), xC, yC, zC, i, 1, 0, newRotation,
                            insertp.getLeaves() ? 1 : 0));

                } else
                {
                    // here, the insert point does not allow any trunk on it, which means it's time to put some branches. Points with rotation "none" are middle
                    // points and thus can spawn multiple branches. On the other hand, points with a specific rotation are sideways and thus need branches to be
                    // rotated in the same way.

                    if (insertp.growLeaves)
                    {

                        insPList.add(new InsPCoord(LeafList[new Random().nextInt(LeafList.length)], InsPType.getTypeIndex(type), xC, yC, zC, i, 0, 0,
                                Rotation.getRotationIndex(insertp.rotation), insertp.getLeaves() ? 1 : 0));
                    } else
                    {

                        if (addLeavesInsPOnly == false)
                        {

                            if (insertp.rotation == Rotation.none)
                            {

                                insPList.add(new InsPCoord(BranchList[new Random().nextInt(BranchList.length)], InsPType.getTypeIndex(type), xC, yC, zC, i, 0, 0,
                                        Rotation.getRotationIndex(insertp.rotation), insertp.getLeaves() ? 1 : 0));

                            } else
                            {

                                if (insPList.get(j).getY() > i * lowerBranchLevel)

                                    insPList.add(new InsPCoord(shape.getCode(), InsPType.getTypeIndex(type), xC, yC, zC, i, 0, 0, Rotation.getRotationIndex(insertp.rotation),
                                            insertp.getLeaves() ? 1 : 0));

                            }
                        }
                    }

                    insPList.get(j).turnBuilt();
                }
            }
        }
    }

    public ArrayList<InsPCoord> getInsPList()
    {
        return insPList;
    }

    public int[] getWoodList()
    {
        return WoodList;
    }

    public int[] getLeavesList()
    {
        return LeafList;
    }

    public int[] getTrunkLeavesList()
    {
        return TrunkLeafList;
    }

    public int getPenalty()
    {
        return penaltyPerHeight;
    }

    public int getAge()
    {
        return age;
    }

    public int getMaxAge()
    {
        return maxAge;
    }

    public void increaseAge()
    {
        this.age++;
    }

}
