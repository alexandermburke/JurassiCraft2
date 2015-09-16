package net.reuxertz.ecoapi.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.reuxertz.ecocraft.common.item.food.BaseFood;

public class BaseSeedFood extends BaseFood implements IPlantable
{

    private BaseEcoCrop crops;
    /** Block ID of the dirtSoil this seed food should be planted on. */
    private static final String __OBFID = "CL_00000060";

    public BaseSeedFood(int healAmount, float saturation, BaseEcoCrop crops)
    {
        super(healAmount, saturation);
        this.crops = crops;
    }

    /**
     * Called when a Block is right-clicked with this Item
     *
     * @param pos The block being right-clicked
     * @param side The side being right-clicked
     */
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
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return net.minecraftforge.common.EnumPlantType.Crop;
    }

    @Override
    public net.minecraft.block.state.IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return this.crops.getDefaultState();
    }
}
