package net.timeless.jurassicraft.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.entity.item.EntityCageSmall;

public class ItemCage extends Item
{
    public ItemCage()
    {
        super();
        this.setUnlocalizedName("cage_small");
        this.setCreativeTab(JCCreativeTabs.items);
    }

    /**
     * This is called when the item is used, before the block is activated.
     * @param stack The Item Stack
     * @param player The Player that used the item
     * @param world The Current World
     * @param pos Target position
     * @param side The side of the target hit
     * @return Return true to prevent any further processing.
     */
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        pos = pos.offset(side);

        if(player.canPlayerEdit(pos, side, stack) && !world.isRemote)
        {
            EntityCageSmall cage = new EntityCageSmall(world);
            cage.setEntity(getCaged(stack));
            cage.setEntityData(getData(stack));
            cage.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

            world.spawnEntityInWorld(cage);

            if(!player.capabilities.isCreativeMode)
            {
                stack.stackSize--;
            }

            return true;
        }

        return false;
    }

    private int getCaged(ItemStack stack)
    {
        if(stack.getTagCompound() != null)
        {
            return stack.getTagCompound().getInteger("CagedID");
        }

        return -1;
    }

    private NBTTagCompound getData(ItemStack stack)
    {
        if (stack.getTagCompound() != null)
        {
            return stack.getTagCompound().getCompoundTag("Entity");
        }

        return null;
    }
}
