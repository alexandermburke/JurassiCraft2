package net.timeless.jurassicraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.Explosion;
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
}
