package net.timeless.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.world.Explosion;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

import java.util.Random;

public class BlockAmber extends Block
{
    public BlockAmber()
    {
        super(Material.rock);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setUnlocalizedName("amber_ore");
        this.setCreativeTab(JCCreativeTabs.blocks);
        this.setHarvestLevel("pickaxe", 2);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     *
     * @param fortune the level of the Fortune enchantment on the player's tool
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return JCItemRegistry.amber;
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosion)
    {
        return false;
    }
}
