package net.timeless.jurassicraft.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.achievements.JCAchievements;
import net.timeless.jurassicraft.common.block.BlockEncasedFossil;
import net.timeless.jurassicraft.common.block.BlockFossil;
import net.timeless.jurassicraft.common.block.JCBlockRegistry;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;

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

            Block block = state.getBlock();

            if (block instanceof BlockFossil)
            {
                JCBlockRegistry blockRegistry = JurassiCraft.blockRegistry;

                int id = blockRegistry.getDinosaurId((BlockFossil) block, block.getMetaFromState(state));

                world.setBlockState(pos, blockRegistry.getEncasedFossil(id).getDefaultState().withProperty(BlockEncasedFossil.VARIANT, blockRegistry.getMetadata(id)));

                if (!player.capabilities.isCreativeMode)
                    stack.stackSize--;

                player.addStat(JCAchievements.fossils, 1);

                return true;
            }
        }

        return false;
    }
}
