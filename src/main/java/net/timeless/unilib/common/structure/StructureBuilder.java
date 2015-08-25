package net.timeless.unilib.common.structure;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.timeless.unilib.common.structure.rules.FixedRule;
import net.timeless.unilib.common.structure.rules.RepeatRule;
import net.timeless.unilib.utils.Tuple3;

import java.util.*;

public class StructureBuilder extends StructureGenerator {
    private final HashMap<BlockCoords, BlockList> blocks;
    private int offsetX;
    private int offsetY;
    private int offsetZ;
    private List<RepeatRule> repeats;
    private List<ComponentInfo> components;
    private ComponentInfo currentLayer;

    public StructureBuilder() {
        blocks = Maps.newHashMap();
        repeats = Lists.newArrayList();
        components = Lists.newArrayList();
    }

    @Override
    public void generate(World world, int x, int y, int z, Random random) {
        BlockCoords pos = new BlockCoords();
        for(ComponentInfo layer : components) {
            for(RepeatRule rule : layer.repeats) {
                rule.reset(world, random, pos);
            }
        }
        for(ComponentInfo layer : components) {
            int currentX = x;
            int currentY = y;
            int currentZ = z;
            pos.x = currentX;
            pos.y = currentY;
            pos.z = currentZ;
            for(RepeatRule rule : layer.repeats) {
                rule.init(world, random, pos);
                while(rule.continueRepeating(world, random, pos)) {
                    for (Map.Entry<BlockCoords, BlockList> e : layer.blocks.entrySet()) {
                        BlockCoords coords = e.getKey();
                        int blockX = coords.x + pos.x;
                        int blockY = coords.y + pos.y;
                        int blockZ = coords.z + pos.z;
                        world.setBlockState(new BlockPos(blockX, blockY, blockZ), e.getValue().getRandom(random));
                    }
                    rule.repeat(world, random, pos);
                }
            }
        }
    }

    @Override
    public StructureGenerator rotateClockwise(EnumRotAngle angle) {
        StructureBuilder copy = new StructureBuilder();
        float radangle = 0; // This angle is sooooooooooo rad
        switch(angle) {
            case DEGREES_180:
                return rotateClockwise(EnumRotAngle.DEGREES_90).rotateClockwise(EnumRotAngle.DEGREES_90);

            case DEGREES_90:
                radangle = (float) Math.PI/2f;
                break;

            case DEGREES_270:
                radangle = (float) Math.PI*3f/2f;
                break;
        }
        for(ComponentInfo oldComp : components) { // Rotate each layer
            ComponentInfo newComp = new ComponentInfo();
            newComp.repeats.addAll(oldComp.repeats);
            newComp.facing = oldComp.facing;
            for(int i = 0;i<angle.turnsCount();i++)
                newComp.facing = getNextClockwise(newComp.facing);
            HashMap<BlockCoords, BlockList> blocks = newComp.blocks;
            Tuple3<Integer, Integer, Integer> minCoords = mins(oldComp.blocks);
            Tuple3<Integer, Integer, Integer> maxCoords = maxs(oldComp.blocks);
            int width = maxCoords.getX() - minCoords.getX();
            int height = maxCoords.getY() - minCoords.getY();
            int depth = maxCoords.getZ() - minCoords.getZ();

            float midX = width/2f + minCoords.getX();
            float midZ = depth/2f + minCoords.getZ();
            for(BlockCoords coords : oldComp.blocks.keySet()) {
                float angleToCenter = (float) Math.atan2(coords.z - midZ, coords.x - midX);
                float dx = midX - coords.x;
                float dz = midZ - coords.z;
                float distToCenter = (float) Math.sqrt(dx * dx + dz * dz);
                float nangle = radangle + angleToCenter;
                float newX = (float) Math.cos(nangle) * distToCenter;
                float newZ = (float) Math.sin(nangle) * distToCenter;
                BlockCoords newCoords = new BlockCoords((int) Math.floor(newX + midX), coords.y, (int) Math.floor(newZ + midZ));
                BlockList newList = oldComp.blocks.get(coords).copy();
                IBlockState[] states = newList.getStates();
                float[] probas = newList.getProbabilities();
                for(int i = 0;i<states.length;i++) {
                    IBlockState state = states[i];
                    Collection<IProperty> properties = state.getPropertyNames();
                    for(IProperty prop : properties) {
                        if(prop instanceof PropertyDirection) {
                            PropertyDirection dir =(PropertyDirection)prop;
                            EnumFacing facing = (EnumFacing) state.getValue(dir);
                            for(int j = 0;j<angle.turnsCount();j++)
                                facing = getNextClockwise(facing);
                            states[i] = state.withProperty(dir, facing);
                        }
                    }
                }
                blocks.put(newCoords, newList);
            }

            copy.components.add(newComp);
        }
        return copy;
    }

    private double rot_x(float angle, float x, float y) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return x * cos + y * -sin;
    }

    private double rot_y(float angle, float x, float y) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return x * sin + y * cos;
    }

    private Tuple3<Integer, Integer, Integer> maxs(HashMap<BlockCoords, BlockList> blocks) {
        Tuple3<Integer, Integer, Integer> result = new Tuple3<Integer, Integer, Integer>();
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int maxZ = Integer.MIN_VALUE;
        for(BlockCoords coords : blocks.keySet()) {
            int x = coords.x;
            int y = coords.y;
            int z = coords.z;
            if(x > maxX) {
                maxX = x;
            }
            if(y > maxY) {
                maxY = y;
            }
            if(z > maxZ) {
                maxZ = z;
            }
        }
        result.set(maxX, maxY, maxZ);
        return result;
    }

    private Tuple3<Integer, Integer, Integer> mins(HashMap<BlockCoords, BlockList> blocks) {
        Tuple3<Integer, Integer, Integer> result = new Tuple3<Integer, Integer, Integer>();
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int minZ = Integer.MAX_VALUE;
        for(BlockCoords coords : blocks.keySet()) {
            int x = coords.x;
            int y = coords.y;
            int z = coords.z;
            if(x < minX) {
                minX = x;
            }
            if(y < minY) {
                minY = y;
            }
            if(z < minZ) {
                minZ = z;
            }
        }
        result.set(minX, minY, minZ);
        return result;
    }

    @Override
    public StructureGenerator rotateTowards(EnumFacing facing) {
        return null;
    }

    public StructureBuilder startComponent() {
        currentLayer = new ComponentInfo();
        blocks.clear();
        repeats.clear();
        offsetX = 0;
        offsetY = 0;
        offsetZ = 0;
        return this;
    }

    public StructureBuilder setOrientation(EnumFacing facing) {
        currentLayer.facing = facing;
        return this;
    }

    public StructureBuilder endComponent() {
        currentLayer.blocks.putAll(blocks);
        currentLayer.repeats.addAll(repeats);
        components.add(currentLayer);
        return this;
    }

    public StructureBuilder setOffset(int x, int y, int z) {
        this.offsetX = x;
        this.offsetY = y;
        this.offsetZ = z;
        return this;
    }

    public StructureBuilder translate(int x, int y, int z) {
        offsetX += x;
        offsetY += y;
        offsetZ += z;
        return this;
    }

    // TODO: Overload to specify block instead of block state
    public StructureBuilder setBlock(int x, int y, int z, IBlockState block) {
        return setBlock(x, y, z, new BlockList(block));
    }

    public StructureBuilder setBlock(int x, int y, int z, BlockList list) {
        blocks.put(new BlockCoords(x + offsetX, y + offsetY, z + offsetZ), list);
        return this;
    }

    public StructureBuilder cube(int startX, int startY, int startZ, int width, int height, int depth, IBlockState block) {
        return cube(startX, startY, startZ, width, height, depth, new BlockList(block));
    }

    public StructureBuilder cube(int startX, int startY, int startZ, int width, int height, int depth, BlockList list) {
        if(depth > 1) {
            fillCube(startX, startY, startZ, width, height, 1, list);
            fillCube(startX, startY, startZ + depth - 1, width, height, 1, list);
        }

        if(width > 1) {
            fillCube(startX, startY, startZ, 1, height, depth, list);
            fillCube(startX + width - 1, startY, startZ, 1, height, depth, list);
        }

        if(height > 1) {
            fillCube(startX, startY, startZ, width, 1, depth, list);
            fillCube(startX, startY + height - 1, startZ, width, 1, depth, list);
        }
        return this;
    }

    public StructureBuilder fillCube(int startX, int startY, int startZ, int width, int height, int depth, IBlockState block) {
        return fillCube(startX, startY, startZ, width, height, depth, new BlockList(block));
    }

    public StructureBuilder fillCube(int startX, int startY, int startZ, int width, int height, int depth, BlockList list) {
        for(int x = startX;x<startX+width;x++) {
            for(int y = startY;y<startY+height;y++) {
                for(int z = startZ;z<startZ+depth;z++) {
                    setBlock(x, y, z, list);
                }
            }
        }
        return this;
    }

    public StructureBuilder repeat(int spacingX, int spacingY, int spacingZ, int times) {
        return repeat(spacingX, spacingY, spacingZ, new FixedRule(times));
    }

    public StructureBuilder repeat(int spacingX, int spacingY, int spacingZ, RepeatRule repeatRule) {
        repeatRule.setSpacing(spacingX, spacingY, spacingZ);
        return addBakedRepeatRule(repeatRule);
    }

    public StructureBuilder addBakedRepeatRule(RepeatRule repeatRule) {
        repeats.add(repeatRule);
        return this;
    }

    public StructureBuilder cube(int startX, int startY, int startZ, int width, int height, int depth, Block block) {
        return cube(startX, startY, startZ, width, height, depth, block.getDefaultState());
    }

    public StructureBuilder fillCube(int startX, int startY, int startZ, int width, int height, int depth, Block block) {
        return fillCube(startX, startY, startZ, width, height, depth, block.getDefaultState());
    }

    public StructureBuilder wireCube(int startX, int startY, int startZ, int width, int height, int depth, Block block) {
        return wireCube(startX, startY, startZ, width, height, depth, block.getDefaultState());
    }

    public StructureBuilder wireCube(int startX, int startY, int startZ, int width, int height, int depth, IBlockState state) {
        return wireCube(startX, startY, startZ, width, height, depth, new BlockList(state));
    }

    private StructureBuilder wireCube(int startX, int startY, int startZ, int width, int height, int depth, BlockList list) {
        fillCube(startX, startY, startZ, 1, height, 1, list);
        fillCube(startX+width-1, startY, startZ, 1, height, 1, list);
        fillCube(startX+width-1, startY, startZ+depth-1, 1, height, 1, list);
        fillCube(startX, startY, startZ+depth-1, 1, height, 1, list);

        fillCube(startX, startY, startZ, width, 1, 1, list);
        fillCube(startX, startY+height, startZ, width, 1, 1, list);
        fillCube(startX, startY, startZ+depth-1, width, 1, 1, list);
        fillCube(startX, startY+height, startZ+depth-1, width, 1, 1, list);

        fillCube(startX, startY, startZ, 1, 1, depth, list);
        fillCube(startX, startY+height, startZ, 1, 1, depth, list);
        fillCube(startX+width-1, startY, startZ, 1, 1, depth, list);
        fillCube(startX+width-1, startY+height, startZ, 1, 1, depth, list);
        return this;
    }

    public StructureBuilder setBlock(int x, int y, int z, Block block) {
        setBlock(x, y, z, block.getDefaultState());
        return this;
    }
}
