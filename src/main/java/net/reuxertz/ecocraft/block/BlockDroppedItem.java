package net.reuxertz.ecocraft.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.reuxertz.ecocraft.tileentity.TileEntityDroppedItem;

import java.util.Arrays;
import java.util.List;

public class BlockDroppedItem extends BlockContainer
{
    public BlockDroppedItem()
    {
        //new BlockCompressed().setHardness(5.0F).setResistance(10.0F).setStepSound(soundTypeMetal).setBlockName("blockIron").setBlockTextureName("iron_block"));
        super(Material.air);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new TileEntityDroppedItem();
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        return Arrays.asList(((TileEntityDroppedItem) world.getTileEntity(pos)).getDroppedItem());
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {

        this.breakBlock(worldIn, pos, Blocks.air.getDefaultState());
        return true;
    }
}
