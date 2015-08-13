package net.timeless.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.tileentity.TileDNACombinator;
import net.timeless.jurassicraft.common.tileentity.TileDNAHybridizer;

import java.util.Random;

public class BlockDNACombinator extends BlockOriented
{
    public BlockDNACombinator()
    {
        super(Material.iron);
        this.setUnlocalizedName("dna_combinator");
        this.setHardness(2.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setCreativeTab(JCCreativeTabs.blocks);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(JCBlockRegistry.dna_combinator);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(JCBlockRegistry.dna_combinator);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileDNACombinator();
    }
}
