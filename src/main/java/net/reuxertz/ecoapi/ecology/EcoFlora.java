package net.reuxertz.ecoapi.ecology;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class EcoFlora
{
    //Crop Registry
    //private static HashMap<ItemStack, Block> floraBlockDropRegistry = new HashMap<ItemStack, Block>();
    private static HashMap<IBlockState, ItemStack> floraBlockDropRegistry = new HashMap<IBlockState, ItemStack>();
    private static HashMap<IBlockState, ItemStack> floraBlockSeedRegistry = new HashMap<IBlockState, ItemStack>();
    private static List<IBlockState> floraBlocksRequireUnderBlock = new ArrayList<IBlockState>();
    static
    {
        //Crops
        floraBlockDropRegistry.put(Blocks.potatoes.getStateFromMeta(7), new ItemStack(Items.potato));
        floraBlockSeedRegistry.put(Blocks.potatoes.getStateFromMeta(7), new ItemStack(Items.potato));

        floraBlockDropRegistry.put(Blocks.carrots.getStateFromMeta(7), new ItemStack(Items.carrot));
        floraBlockSeedRegistry.put(Blocks.carrots.getStateFromMeta(7), new ItemStack(Items.carrot));

        floraBlockDropRegistry.put(Blocks.wheat.getStateFromMeta(7), new ItemStack(Items.wheat));
        floraBlockDropRegistry.put(Blocks.wheat.getStateFromMeta(7), new ItemStack(Items.wheat_seeds));
        floraBlockSeedRegistry.put(Blocks.wheat.getStateFromMeta(7), new ItemStack(Items.wheat_seeds));

        floraBlockDropRegistry.put(Blocks.reeds.getStateFromMeta(15), new ItemStack(Items.sugar));
        floraBlocksRequireUnderBlock.add(Blocks.reeds.getStateFromMeta(15));
        floraBlockSeedRegistry.put(Blocks.reeds.getStateFromMeta(15), new ItemStack(Item.getItemFromBlock(Blocks.reeds)));

        floraBlockDropRegistry.put(Blocks.cactus.getStateFromMeta(15), new ItemStack(Item.getItemFromBlock(Blocks.cactus)));
        floraBlocksRequireUnderBlock.add(Blocks.cactus.getStateFromMeta(15));
        floraBlockSeedRegistry.put(Blocks.cactus.getStateFromMeta(15), new ItemStack(Item.getItemFromBlock(Blocks.cactus)));

        floraBlockDropRegistry.put(Blocks.melon_block.getStateFromMeta(0), new ItemStack(Items.melon));
        floraBlockDropRegistry.put(Blocks.melon_block.getStateFromMeta(0), new ItemStack(Items.melon_seeds));
        floraBlockSeedRegistry.put(Blocks.melon_block.getStateFromMeta(0), new ItemStack(Items.melon_seeds));

        floraBlockDropRegistry.put(Blocks.pumpkin.getStateFromMeta(0), new ItemStack(Item.getItemFromBlock(Blocks.pumpkin)));
        floraBlockDropRegistry.put(Blocks.pumpkin.getStateFromMeta(0), new ItemStack(Items.pumpkin_seeds));
        floraBlockSeedRegistry.put(Blocks.pumpkin.getStateFromMeta(0), new ItemStack(Items.pumpkin_seeds));

        //floraBlockDropRegistry.put(Blocks.leaves.getStateFromMeta(0), new ItemStack(Items.apple));

        //Tree Saplings
        /*for (int i = 0; i < 16; i++)
        {
            IBlockState b1 = Blocks.log.getStateFromMeta(i);
            ItemStack s1 = new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, i % 4);
            floraBlockSeedRegistry.put(b1, s1);

            if (i % 4 < 2)
            {
                ItemStack s2 = new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 4 + i % 4);
                IBlockState b2 = Blocks.log2.getStateFromMeta(i);
                floraBlockSeedRegistry.put(b2, s2);
            }
        }*/

        //Resources
        //for (int i = 0; i < 16; i++)
        //{
        //    floraBlockDropRegistry.put(Blocks.leaves.getStateFromMeta(i), new ItemStack(Items.apple));
        //}

    }
    /*public static Block getFloraBlockDrop(ItemStack stack)
    {
        Set<ItemStack> stacks = floraBlockDropRegistry.keySet();
        for (ItemStack s: stacks)
            if (BaseItem.itemsEqual(s, stack))
                return floraBlockDropRegistry.get(s);

        return null;
    }*/
    public static ItemStack getFloraBlockSeed(IBlockState b)
    {
        Set<IBlockState> blocks = floraBlockSeedRegistry.keySet();
        for (IBlockState bi: blocks)
            if (Block.isEqualTo(b.getBlock(), bi.getBlock()))
                return floraBlockSeedRegistry.get(bi);

        return null;
    }
    public static ItemStack getFloraBlockDrop(IBlockState b)
    {
        Set<IBlockState> blocks = floraBlockDropRegistry.keySet();
        for (IBlockState bi: blocks)
            if (Block.isEqualTo(b.getBlock(), bi.getBlock()) && b.getBlock().getMetaFromState(b) == bi.getBlock().getMetaFromState(bi))
                return floraBlockDropRegistry.get(bi);

        return null;
    }
}
