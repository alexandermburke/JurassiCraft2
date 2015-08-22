package net.timeless.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

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

        Block block = item instanceof ItemBlock && !isFlowerPot() ? Block.getBlockFromItem(item) : this;
        return new ItemStack(item, 1, block.getDamageValue(world, pos));
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        BlockPos bottomBlock = pos.add(0, -1, 0);

        if(worldIn.getBlockState(bottomBlock).getBlock() != JCBlockRegistry.cultivate_bottom)
        {
            worldIn.setBlockState(bottomBlock, JCBlockRegistry.cultivate_bottom.getDefaultState().withProperty(COLOR, state.getValue(COLOR)));
        }
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.setBlockState(pos.add(0, -1, 0), Blocks.air.getDefaultState());

        super.breakBlock(worldIn, pos, state);
    }
}
