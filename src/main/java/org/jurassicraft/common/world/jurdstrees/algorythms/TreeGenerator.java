package org.jurassicraft.common.world.jurdstrees.algorythms;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.world.jurdstrees.algorythms.InsPCoord.InsPType;
import org.jurassicraft.common.world.jurdstrees.algorythms.TreeBlock.Rotation;

import java.util.Random;


public class TreeGenerator
{

    private Tree tree;
    private int code;
    private BlockPos pos;
    private World world;
    int x, y, z;
    private Random random = new Random();

    public TreeGenerator(int code, World world, BlockPos pos)
    {

        this.world = world;
        x = 0;
        y = 0;
        z = 0;
        this.code = code;
        this.pos = pos;

    }


    public void placeTree()
    {


        if (!world.isRemote)
        {


            tree = TreeCompendium.getTreeFromCode(code);


            if (!tree.hasBeenGenerated())
            {


                tree.generateTree();


            }

            for (InsPCoord coord : tree.getInsPList())
            {

                if (coord.getLevel() <= tree.getMaxAge())
                {

                    Shape skull = TreeCompendium.getRotatedShapeFromCode(coord.getCode(), Rotation.getRotationFromIndex(coord.getRotation()));
                    Shape wood = TreeCompendium.getRotatedShapeFromCode(tree.getWoodList()[random.nextInt(tree.getWoodList().length)],
                            Rotation.getRotationFromIndex(coord.getRotation()));

                    if (coord.getLeaves() == 0)
                    {

                        setBlocksFromShape(world, x, y, z, coord, skull, wood);

                        if (coord.getTrunk() == 0 && coord.getRotation() == 0)
                        {

                            skull = TreeCompendium.getRotatedShapeFromCode(coord.getCode(), Rotation.north);
                            setBlocksFromShape(world, x, y, z, coord, skull, wood);
                            skull = TreeCompendium.getRotatedShapeFromCode(coord.getCode(), Rotation.west);
                            setBlocksFromShape(world, x, y, z, coord, skull, wood);
                            skull = TreeCompendium.getRotatedShapeFromCode(coord.getCode(), Rotation.south);
                            setBlocksFromShape(world, x, y, z, coord, skull, wood);
                        }
                    }

                }

                buildLeavesFromInsPCoord(world, x, y, z, coord);
            }

        }
    }

    private void buildLeavesFromInsPCoord(World world, int x, int y, int z, InsPCoord coord)
    {

        IBlockState leaves = tree.getLeavesFromCode(tree.getCode()).getDefaultState();


        if (coord.getLevel() <= tree.getMaxAge() + 1 && coord.getLeaves() == 1)
        {

            int leafCode;

            if (coord.getType() == InsPType.getTypeIndex(InsPType.trunk))
            {
                leafCode = tree.getTrunkLeavesList()[random.nextInt(tree.getTrunkLeavesList().length)];
            }
            else
            {
                leafCode = tree.getLeavesList()[random.nextInt(tree.getLeavesList().length)];
            }

            Shape leaf = TreeCompendium.getRotatedShapeFromCode(leafCode, Rotation.getRotationFromIndex(coord.getRotation()));

            for (TreeBlock LeafB : leaf.blocksList)
            {

                int xW;
                int yW;
                int zW;

                if (coord.getType() == InsPType.getTypeIndex(InsPType.trunk))
                {

                    xW = x + -LeafB.getY() + coord.getX();
                    yW = y + LeafB.getX() + coord.getY();
                    zW = z + LeafB.getZ() + coord.getZ();

                }
                else
                {

                    xW = x + LeafB.getX() + coord.getX();
                    yW = y + LeafB.getY() + coord.getY();
                    zW = z + LeafB.getZ() + coord.getZ();

                }


                if ((world.getBlockState(pos.add(xW, yW, zW)) == Blocks.air.getDefaultState() || world.getBlockState(new BlockPos(xW, yW, zW)).getBlock() == tree.getLeavesFromCode(tree.getCode())))
                {


                    world.setBlockState(pos.add(xW, yW, zW), leaves);

                }
            }
        }
    }

    private void setBlocksFromShape(World world, int x, int y, int z, InsPCoord coord, Shape skull, Shape wood)
    {


        IBlockState logs = tree.getBlocksFromCode(tree.getCode()).getDefaultState();

        for (TreeBlock TB : skull.blocksList)
        {

            int xC = x + TB.getX() + coord.getX();
            int yC = y + TB.getY() + coord.getY();
            int zC = z + TB.getZ() + coord.getZ();

            // this sets the skull blocks from the loaded shape.

            if (world.getBlockState(pos.add(xC, yC, zC)) == Blocks.air.getDefaultState() || world.getBlockState(pos.add(xC, yC, zC)) == tree.getBlocksFromCode(tree.getCode()).getDefaultState()
                    || world.getBlockState(pos.add(xC, yC, zC)) == tree.getLeavesFromCode(tree.getCode()).getDefaultState() || world.getBlockState(pos.add(xC, yC, zC)).getBlock() == JCBlockRegistry.saplings[tree.getCode()])
            {

                if (world.getBlockState(pos.add(xC, yC, zC)) == Blocks.air.getDefaultState() || world.getBlockState(pos.add(xC, yC, zC)) == tree.getLeavesFromCode(tree.getCode()).getDefaultState()
                        || world.getBlockState(pos.add(xC, yC, zC)).getBlock() == JCBlockRegistry.saplings[tree.getCode()])
                {
                    world.setBlockState(pos.add(xC, yC, zC), logs); // skull block

                }

                for (TreeBlock WoodB : wood.blocksList)
                {

                    int xW = x + TB.getX() + WoodB.getX() + coord.getX();
                    int yW = y + TB.getY() + WoodB.getY() + coord.getY();
                    int zW = z + TB.getZ() + WoodB.getZ() + coord.getZ();

                    int ageLevel = tree.getMaxAge() - (yW - y) / tree.getPenalty();

                    if (WoodB.level < ageLevel && (world.getBlockState(pos.add(xW, yW, zW)) == Blocks.air.getDefaultState() || world.getBlockState(pos.add(xW, yW, zW)) == tree.getLeavesFromCode(tree.getCode()).getDefaultState()))
                    {

                        world.setBlockState(pos.add(xW, yW, zW), logs);

                    }
                }

            }
            else
            {

                if (world.getBlockState(pos.add(xC, yC, zC)) != tree.getBlocksFromCode(tree.getCode()).getDefaultState())
                    break;
            }
        }
    }

}
