package net.timeless.jurassicraft.item;

import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.timeless.jurassicraft.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.entity.item.EntityBluePrint;

public class ItemBluePrint extends Item
{
    private final Class hangingEntityClass;

    public ItemBluePrint()
    {
        this.hangingEntityClass = EntityBluePrint.class;
        this.setUnlocalizedName("blueprint");
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
                EntityHanging entityhanging = this.createEntity(worldIn, blockpos1, side);

                if (entityhanging != null && entityhanging.onValidSurface())
                {
                    if (!worldIn.isRemote)
                    {
                        worldIn.spawnEntityInWorld(entityhanging);
                    }

                    --stack.stackSize;
                }

                return true;
            }
        }
    }

    private EntityHanging createEntity(World worldIn, BlockPos pos, EnumFacing clickedSide)
    {
        return new EntityBluePrint(worldIn, pos, clickedSide);
    }
}