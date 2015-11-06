package org.jurassicraft.common.item;

import net.minecraft.block.BlockBush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.jurassicraft.common.creativetab.JCCreativeTabs;

public class ItemEmptySyringe extends Item
{
    public ItemEmptySyringe()
    {
        super();
        this.setUnlocalizedName("empty_syringe");
        this.setCreativeTab(JCCreativeTabs.items);
    }

    /**
     * Called when a Block is right-clicked with this Item
     *
     * @param pos  The block being right-clicked
     * @param side The side being right-clicked
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (worldIn.getBlockState(pos).getBlock() instanceof BlockBush)
        {
            playerIn.inventory.addItemStackToInventory(new ItemStack(JCItemRegistry.plant_cells));
            stack.stackSize--;

            return true;
        }

        return false;
    }
}
