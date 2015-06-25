package net.timeless.jurassicraft.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.timeless.jurassicraft.block.JCBlockRegistry;
import net.timeless.jurassicraft.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.handler.FossilHandler;

public class ItemPlasterAndBandage extends Item
{
    public ItemPlasterAndBandage()
    {
        super();
        this.setUnlocalizedName("plaster_and_bandage");
        this.setCreativeTab(JCCreativeTabs.items);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else if (!player.canPlayerEdit(pos.offset(side), side, stack))
        {
            return false;
        }
        else
        {
            IBlockState state = world.getBlockState(pos);

            if (state.getBlock() == JCBlockRegistry.fossil)
            {
                if (player.getHeldItem().getItem() instanceof ItemPlasterAndBandage)
                {
                    if (!player.capabilities.isCreativeMode)
                        stack.stackSize--;

                    if (player.getRNG().nextFloat() < 0.9F)
                    {
                        // Change the block state depending on the pos later.
                        world.setBlockState(pos, JCBlockRegistry.encased_fossil.getStateFromMeta(FossilHandler.getDefaultTimePeriod()));
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
