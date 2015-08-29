package net.timeless.jurassicraft.common.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.tileentity.TileCarnivoreFeeder;

import java.util.Random;

public class BlockCarnivoreFeeder extends BlockOriented implements ITileEntityProvider
{

    public static final PropertyDirection facing = PropertyDirection.create("FACING", EnumFacing.Plane.HORIZONTAL);

    public BlockCarnivoreFeeder()
    {
        super(Material.rock);
        setCreativeTab(JCCreativeTabs.blocks);
        setUnlocalizedName("carnivore_feeder");
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(JCBlockRegistry.carnivoreFeeder);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileCarnivoreFeeder)
                ((TileCarnivoreFeeder) tileentity).setCustomInventoryName(stack.getDisplayName());
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            player.openGui(JurassiCraft.instance, 11, world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileCarnivoreFeeder();
    }
}
