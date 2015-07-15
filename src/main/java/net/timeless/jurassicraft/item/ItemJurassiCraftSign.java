package net.timeless.jurassicraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.timeless.jurassicraft.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.entity.item.EntityJurassiCraftSign;

public class ItemJurassiCraftSign extends Item
{
    public ItemJurassiCraftSign()
    {
        this.setUnlocalizedName("jurassicraft_sign");
        this.setCreativeTab(JCCreativeTabs.items);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
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
                EntityJurassiCraftSign sign = new EntityJurassiCraftSign(worldIn, blockpos1, side);

                if (sign.onValidSurface())
                {
                    if (!worldIn.isRemote)
                    {
                        worldIn.spawnEntityInWorld(sign);
                    }

                    --stack.stackSize;
                }

                return true;
            }
        }
    }
}