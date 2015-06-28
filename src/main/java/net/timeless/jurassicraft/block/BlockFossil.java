package net.timeless.jurassicraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.Explosion;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.creativetab.JCCreativeTabs;

public class BlockFossil extends Block
{
    public BlockFossil()
    {
        super(Material.rock);
        this.setUnlocalizedName("fossil_block");
        this.setHardness(0.5F);
        this.setResistance(1.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setCreativeTab(JCCreativeTabs.blocks);
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
        return 0;
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosion)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.SOLID;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return true;
    }

    @Override
    public boolean isFullCube()
    {
        return true;
    }

    @Override
    public int getRenderType()
    {
        return 3;
    }
}
