package net.reuxertz.ecoapi.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.reuxertz.ecocraft.common.block.properties.PropertyFloat;
import net.reuxertz.ecocraft.common.handlers.FluidHandler;

public class BaseBlockFluidWater extends BaseBlockFluid
{
    public static final PropertyFloat HEIGHT_NW = new PropertyFloat("height_nw", 0F, 1F), HEIGHT_SW = new PropertyFloat("height_sw", 0F, 1F),
            HEIGHT_SE = new PropertyFloat("height_se", 0F, 1F), HEIGHT_NE = new PropertyFloat("height_ne", 0F, 1F);
    public static final PropertyFloat FLOW_DIRECTION = new PropertyFloat("flow_direction");

    public BaseBlockFluidWater(Fluid fluid, Material material)
    {
        super(fluid, material);
    }
    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing side)
    {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        boolean typeMatch = FluidHandler.TypeMatch(block, this);

        if ((side == EnumFacing.UP || side == EnumFacing.DOWN) && !typeMatch)
            return true;

        if (!typeMatch)
            return super.shouldSideBeRendered(world, pos, side);

        //if (side != EnumFacing.UP && side != EnumFacing.DOWN)
        {
            if (typeMatch)
            {
                if (block instanceof BaseBlockFluidFinite)
                {
                    int l = ((Integer) state.getValue(BlockFluidFinite.LEVEL)).intValue();
                    return l != 8;
                }

                return false;
            }

            /*
            if (side == EnumFacing.NORTH)
                return typeMatch ? false : super.shouldSideBeRendered(world, pos, side);
            else if (side == EnumFacing.SOUTH)
                return typeMatch ? false : super.shouldSideBeRendered(world, pos, side);
            else if (side == EnumFacing.EAST)
                return world.getBlockState(pos).getBlock() instanceof BaseBlockFluidWater ? false : super.shouldSideBeRendered(world, pos, side);
            else if (side == EnumFacing.WEST)
                return world.getBlockState(pos).getBlock() instanceof BaseBlockFluidWater ? false : super.shouldSideBeRendered(world, pos, side);*/
        }

        return super.shouldSideBeRendered(world, pos, side);
    }

    @Override
    public Vec3 getFlowVector(IBlockAccess world, BlockPos pos)
    {
        Vec3 vec = new Vec3(0.0D, 0.0D, 0.0D);
        int decay = quantaPerBlock - getQuantaValue(world, pos);

        for (int side = 0; side < 4; ++side)
        {
            int x2 = pos.getX();
            int z2 = pos.getZ();

            switch (side)
            {
                case 0:
                    --x2;
                    break;
                case 1:
                    --z2;
                    break;
                case 2:
                    ++x2;
                    break;
                case 3:
                    ++z2;
                    break;
            }

            BlockPos pos2 = new BlockPos(x2, pos.getY(), z2);
            IBlockState state = world.getBlockState(pos2);

            if (FluidHandler.TypeMatch(state.getBlock(), Blocks.water))
                continue;

            int otherDecay = quantaPerBlock - getQuantaValue(world, pos2);
            if (otherDecay >= quantaPerBlock)
            {
                if (!state.getBlock().getMaterial().blocksMovement())
                {
                    otherDecay = quantaPerBlock - getQuantaValue(world, pos2.down());
                    if (otherDecay >= 0)
                    {
                        int power = otherDecay - (decay - quantaPerBlock);
                        vec = vec.addVector((pos2.getX() - pos.getX()) * power, 0, (pos2.getZ() - pos.getZ()) * power);
                    }
                }
            }
            else if (otherDecay >= 0)
            {
                int power = otherDecay - decay;

                if (power != 0)
                    vec = vec.addVector((pos2.getX() - pos.getX()) * power, 0, (pos2.getZ() - pos.getZ()) * power);
            }
        }

        if (world.getBlockState(pos.up()).getBlock() == this)
        {
            boolean flag =
                    isBlockSolid(world, pos.add(0, 0, -1), EnumFacing.NORTH) ||
                            isBlockSolid(world, pos.add(0, 0, 1), EnumFacing.SOUTH) ||
                            isBlockSolid(world, pos.add(-1, 0, 0), EnumFacing.WEST) ||
                            isBlockSolid(world, pos.add(1, 0, 0), EnumFacing.EAST) ||
                            isBlockSolid(world, pos.add(0, 1, -1), EnumFacing.NORTH) ||
                            isBlockSolid(world, pos.add(0, 1, 1), EnumFacing.SOUTH) ||
                            isBlockSolid(world, pos.add(-1, 1, 0), EnumFacing.WEST) ||
                            isBlockSolid(world, pos.add(1, 1, 0), EnumFacing.EAST);

            if (flag)
            {
                vec = vec.normalize().addVector(0.0D, -6.0D, 0.0D);
            }
        }
        vec = vec.normalize();
        return vec;
    }
    @Override
    public boolean isBlockSolid(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        return worldIn.getBlockState(pos).getBlock().getMaterial().isSolid();
    }
}