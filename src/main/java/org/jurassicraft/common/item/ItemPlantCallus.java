package org.jurassicraft.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.jurassicraft.common.lang.AdvLang;
import org.jurassicraft.common.plant.JCPlantRegistry;
import org.jurassicraft.common.plant.Plant;

public class ItemPlantCallus extends Item
{
    public ItemPlantCallus()
    {
        super();
        this.setUnlocalizedName("plant_callus");
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        return new AdvLang("item.plant_callus.name").withProperty("plant", "plants." + JCPlantRegistry.getPlantById(stack.getItemDamage()).getName().toLowerCase().replaceAll(" ", "_") + ".name").build();
    }

    /**
     * Called when a Block is right-clicked with this Item
     *
     * @param pos  The block being right-clicked
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
        else if (worldIn.isAirBlock(pos.up()) && worldIn.getBlockState(pos).getBlock() == Blocks.farmland)
        {
            Plant plant = JCPlantRegistry.getPlantById(stack.getItemDamage());

            if (plant != null)
            {
                worldIn.setBlockState(pos.up(), plant.getBlock().getDefaultState());
                worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
                --stack.stackSize;
                return true;
            }
        }

        return false;
    }
}
