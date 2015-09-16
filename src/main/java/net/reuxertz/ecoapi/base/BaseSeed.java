package net.reuxertz.ecoapi.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.reuxertz.ecoapi.item.BaseItem;

public class BaseSeed extends BaseItem implements IPlantable {

    private BaseEcoCrop crops;

    public BaseSeed(BaseEcoCrop crops) {
        this.crops = crops;
        this.setMaxStackSize(64);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (side != EnumFacing.UP)
        {
            return false;
        }
        else if (!playerIn.canPlayerEdit(pos.offset(side), side, stack))
        {
            return false;
        }
        else if (worldIn.getBlockState(pos).getBlock().canSustainPlant(worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up()))
        {
            worldIn.setBlockState(pos.up(), this.crops.getDefaultState());
            --stack.stackSize;
            return true;
        }
        else if (this.crops.canPlaceBlockOn(worldIn.getBlockState(pos).getBlock()) && worldIn.isAirBlock(pos.up()))
        {
            IBlockState cropState = this.crops.getDefaultState();
            worldIn.setBlockState(pos.up(), cropState);
            --stack.stackSize;

            IBlockState state = worldIn.getBlockState(pos.up());
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return this.crops.getDefaultState();
    }
}