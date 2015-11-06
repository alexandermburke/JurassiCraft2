package org.jurassicraft.common.block.machine;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import org.jurassicraft.common.block.JCBlockRegistry;

public class BlockCultivateTop extends BlockCultivate
{
    public BlockCultivateTop()
    {
        super("top");
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos)
    {
        Item item = Item.getItemFromBlock(JCBlockRegistry.cultivate_bottom);

        if (item == null)
        {
            return null;
        }

        Block block = item instanceof ItemBlock && !isFlowerPot() ? getBlockFromItem(item) : this;
        return new ItemStack(item, 1, block.getDamageValue(world, pos));
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        BlockPos add = pos.add(0, -1, 0);
        IBlockState blockState = world.getBlockState(add);

        return blockState.getBlock().onBlockActivated(world, add, blockState, player, side, hitX, hitY, hitZ);
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        BlockPos bottomBlock = pos.add(0, -1, 0);

        if (worldIn.getBlockState(bottomBlock).getBlock() != JCBlockRegistry.cultivate_bottom)
        {
            worldIn.setBlockState(bottomBlock, JCBlockRegistry.cultivate_bottom.getDefaultState().withProperty(COLOR, state.getValue(COLOR)));
        }
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.setBlockState(pos.add(0, -1, 0), Blocks.air.getDefaultState());
        dropItems(worldIn, pos);

        super.breakBlock(worldIn, pos, state);
    }
}
