package net.timeless.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.tileentity.TileCarnivoreFeeder;

public class BlockCarnivoreFeeder extends Block implements ITileEntityProvider
{

    public BlockCarnivoreFeeder()
    {
        super(Material.rock);
        setCreativeTab(JCCreativeTabs.blocks);
        setUnlocalizedName("carnivore_feeder");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileCarnivoreFeeder();
    }
}
