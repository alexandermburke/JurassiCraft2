package net.ilexiconn.jurassicraft.item;

import net.ilexiconn.jurassicraft.block.JCBlockRegistry;
import net.ilexiconn.jurassicraft.creativetab.JCCreativeTabs;
import net.ilexiconn.jurassicraft.handler.FossilHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemPlasterAndBandage extends Item
{
    public ItemPlasterAndBandage()
    {
        super();
        this.setUnlocalizedName("item_plaster_and_bandage");
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
            IBlockState iblockstate = world.getBlockState(pos);
            if (iblockstate.getBlock() == JCBlockRegistry.fossil)
            {
                if (player.getHeldItem().getItem() instanceof ItemPlasterAndBandage)
                {
                    ItemStack plasterAndBandages = player.getHeldItem();
                    if (!player.capabilities.isCreativeMode)
                        plasterAndBandages.stackSize--;

                    if (world.rand.nextFloat() < 0.9F)
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
