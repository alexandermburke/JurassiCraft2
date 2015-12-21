package org.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.item.JCItemRegistry;
import org.jurassicraft.common.tileentity.TileActionFigure;

import java.util.List;
import java.util.Random;

public class BlockActionFigure extends BlockOriented
{
    public BlockActionFigure()
    {
        super(Material.wood);
        this.setTickRandomly(true);
        this.setHardness(0.0F);
        this.setResistance(0.0F);
        this.setUnlocalizedName("action_figure_block");
        float f = 0.2F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && canBlockStay(worldIn, pos, worldIn.getBlockState(pos));
    }

    /**
     * Called when a neighboring block changes.
     */
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
        this.checkAndDropBlock(worldIn, pos, state);
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }

    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.air.getDefaultState(), 3);
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        return worldIn.getBlockState(pos.down()).getBlock().isFullCube();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return JCItemRegistry.action_figure;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return JCItemRegistry.action_figure;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileActionFigure();
    }

    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT_MIPPED;
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

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        return true;
    }

    @Override
    public int getDamageValue(World worldIn, BlockPos pos)
    {
        return getTile(worldIn, pos).dinosaur;
    }

    protected TileActionFigure getTile(World world, BlockPos pos)
    {
        return (TileActionFigure) world.getTileEntity(pos);
    }

    /**
     * This returns a complete list of items dropped from this block.
     *
     * @param world   The current world
     * @param pos     Block position in world
     * @param state   Current state
     * @param fortune Breakers fortune level
     * @return A ArrayList containing all items this block drops
     */
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();

        Random rand = world instanceof World ? ((World) world).rand : RANDOM;

        int count = quantityDropped(state, fortune, rand);

        for (int i = 0; i < count; i++)
        {
            Item item = this.getItemDropped(state, rand, fortune);

            if (item != null)
            {
                ret.add(new ItemStack(item, 1, this.getDamageValue((World) world, pos)));
            }
        }

        return ret;
    }
}
