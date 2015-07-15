package net.timeless.jurassicraft.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.tileentity.TileDnaSynthesizer;

public class BlockDnaSynthesizer extends BlockOriented
{
    public BlockDnaSynthesizer()
    {
        super(Material.wood);
        this.setUnlocalizedName("dna_synthesizer");
        this.setHardness(2.0F);
        this.setStepSound(Block.soundTypeWood);
        this.setCreativeTab(JCCreativeTabs.blocks);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (stack.hasDisplayName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            // if (tileentity instanceof TileDnaSynthesizer)
            // ((TileDnaSynthesizer)
            // tileentity).setCustomInventoryName(stack.getDisplayName());
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileDnaSynthesizer)
        {
            // InventoryHelper.dropInventoryItems(worldIn, pos,
            // (TileDnaSynthesizer) tileentity);
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(JCBlockRegistry.dna_synthesizer);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(JCBlockRegistry.dna_synthesizer);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileDnaSynthesizer();
    }
}
