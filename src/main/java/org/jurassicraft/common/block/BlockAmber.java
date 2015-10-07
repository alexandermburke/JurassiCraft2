package org.jurassicraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.item.JCItemRegistry;

import java.util.List;
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
        List<ItemStack> ret = new java.util.ArrayList<>();

        Random rand = world instanceof World ? ((World) world).rand : RANDOM;

        int count = rand.nextInt(fortune + 2) - 1;

        if (count < 0)
        {
            count = 0;
        }

        for (int i = 0; i < count + 1; i++)
        {
            Item item = JCItemRegistry.amber;

            if (item != null)
            {
                ret.add(new ItemStack(item, 1, rand.nextBoolean() ? 1 : 0));
            }
        }

        return ret;
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosion)
    {
        return false;
    }
}
