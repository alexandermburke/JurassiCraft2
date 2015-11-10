package org.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.item.JCItemRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockIceShard extends Block
{
    public BlockIceShard()
    {
        super(Material.rock);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setUnlocalizedName("ice_shard");
        this.setCreativeTab(JCCreativeTabs.blocks);
        this.setHarvestLevel("pickaxe", 2);
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
        List<ItemStack> ret = new ArrayList<ItemStack>();

        Random rand = world instanceof World ? ((World) world).rand : RANDOM;

        if (rand.nextDouble() < 0.2 + ((fortune / 3.0) * 0.8))
        {
            ret.add(new ItemStack(JCItemRegistry.sea_lamprey));
        }
        else if (rand.nextDouble() < 0.2)
        {
            ret.add(new ItemStack(Blocks.ice));
        }

        return ret;
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosion)
    {
        return false;
    }
}
