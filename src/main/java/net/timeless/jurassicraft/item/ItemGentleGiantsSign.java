package net.timeless.jurassicraft.item;

import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.timeless.jurassicraft.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.entity.item.EntityJurassiCraftSign;

public class ItemGentleGiantsSign extends Item
{
    public ItemGentleGiantsSign()
    {
        super();
        this.setUnlocalizedName("gentle_giants_sign");
        this.setCreativeTab(JCCreativeTabs.decorations);
    }

    /**
     * Called when a Block is right-clicked with this Item
     * 
     * @param pos
     *            The block being right-clicked
     * @param side
     *            The side being right-clicked
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (side == EnumFacing.DOWN)
        {
            return false;
        }
        else if (side == EnumFacing.UP)
        {
            return false;
        }
        else
        {
            BlockPos blockpos1 = pos.offset(side);

            if (!playerIn.canPlayerEdit(blockpos1, side, stack))
            {
                return false;
            }
            else
            {
                EntityHanging sign = new EntityJurassiCraftSign(world, blockpos1, side);

                if (sign != null && sign.onValidSurface())
                {
                    if (!world.isRemote)
                    {
                        world.spawnEntityInWorld(sign);
                    }

                    --stack.stackSize;
                }

                return true;
            }
        }
    }
}
