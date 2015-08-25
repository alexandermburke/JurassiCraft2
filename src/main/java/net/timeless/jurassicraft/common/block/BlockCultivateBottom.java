package net.timeless.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.tileentity.TileCultivate;

public class BlockCultivateBottom extends BlockCultivate
{
    public BlockCultivateBottom()
    {
        super("bottom");
        this.setCreativeTab(JCCreativeTabs.blocks);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileCultivate)
                ((TileCultivate) tileentity).setCustomInventoryName(stack.getDisplayName());
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else if (!player.isSneaking())
        {
            TileEntity tileEntity = world.getTileEntity(pos);

            if (tileEntity instanceof TileCultivate)
            {
                TileCultivate cultivator = (TileCultivate) tileEntity;

                if (cultivator.isUseableByPlayer(player))
                {
                    player.openGui(JurassiCraft.instance, 10, world, pos.getX(), pos.getY(), pos.getZ());
                    return true;
                }
            }
        }

        return false;
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        BlockPos topBlock = pos.add(0, 1, 0);

        Block block = worldIn.getBlockState(topBlock).getBlock();

        if (block == Blocks.air)
        {
            worldIn.setBlockState(topBlock, JCBlockRegistry.cultivate_top.getDefaultState().withProperty(COLOR, state.getValue(COLOR)));
        }
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.setBlockState(pos.add(0, 1, 0), Blocks.air.getDefaultState());
        dropItems(worldIn, pos);

        super.breakBlock(worldIn, pos, state);
    }
}
