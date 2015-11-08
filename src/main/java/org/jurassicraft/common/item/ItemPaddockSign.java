package org.jurassicraft.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.entity.item.EntityBluePrint;
import org.jurassicraft.common.entity.item.EntityPaddockSign;
import org.jurassicraft.common.handler.JCGuiHandler;
import org.jurassicraft.common.lang.AdvLang;

public class ItemPaddockSign extends Item
{
    public ItemPaddockSign()
    {
        this.setUnlocalizedName("paddock_sign");
        this.setCreativeTab(JCCreativeTabs.items);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
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
            BlockPos placePos = pos.offset(side);

            if (!player.canPlayerEdit(placePos, side, stack))
            {
                return false;
            }
            else
            {
                if (player.worldObj.isRemote)
                {
                    JCGuiHandler.openSelectDino(player, placePos, side);
                }

//                int dinosaur = getDinosaur(stack);
//
//                if (dinosaur != -1)
//                {
//                    EntityPaddockSign paddockSign = new EntityPaddockSign(world, placePos, side, dinosaur);
//
//                    if (paddockSign.onValidSurface())
//                    {
//                        if (!world.isRemote)
//                        {
//                            world.spawnEntityInWorld(paddockSign);
//                        }
//
//                        --stack.stackSize;
//
//                        return true;
//                    }
//                }
            }
        }

        return false;
    }
}
