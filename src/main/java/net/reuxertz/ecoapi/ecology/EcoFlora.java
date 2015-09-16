package net.reuxertz.ecoapi.ecology;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.reuxertz.ecoapi.base.BaseEcoCrop;
import net.reuxertz.ecoapi.util.BlockHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class EcoFlora
{
    //Crop Registry
    //private static HashMap<ItemStack, Block> floraBlockDropRegistry = new HashMap<ItemStack, Block>();
    private static List<BlockCrops> cropRegistry = new ArrayList<BlockCrops>();
    private static List<IBlockState> floraBlockDropStateRegistry = new ArrayList<IBlockState>();
    private static List<List<ItemStack>> floraBlockDropListRegistry = new ArrayList<List<ItemStack>>();
    private static HashMap<IBlockState, ItemStack> floraBlockSeedRegistry = new HashMap<IBlockState, ItemStack>();
    private static List<IBlockState> floraBlocksRequireUnderBlock = new ArrayList<IBlockState>();
    static
    {
        //Crops
        EcoFlora.registerFloraBlockDrop(Blocks.potatoes.getStateFromMeta(7), new ItemStack(Items.potato));
        floraBlockSeedRegistry.put(Blocks.potatoes.getStateFromMeta(7), new ItemStack(Items.potato));

        EcoFlora.registerFloraBlockDrop(Blocks.carrots.getStateFromMeta(7), new ItemStack(Items.carrot));
        floraBlockSeedRegistry.put(Blocks.carrots.getStateFromMeta(7), new ItemStack(Items.carrot));

        ArrayList<ItemStack> l1 = new ArrayList<ItemStack>();
        l1.add(new ItemStack(Items.wheat));
        l1.add(new ItemStack(Items.wheat_seeds));
        EcoFlora.registerFloraBlockDrop(Blocks.wheat.getStateFromMeta(7), l1);
        floraBlockSeedRegistry.put(Blocks.wheat.getStateFromMeta(7), new ItemStack(Items.wheat_seeds));

        EcoFlora.registerFloraBlockDrop(Blocks.reeds.getStateFromMeta(15), new ItemStack(Items.sugar));
        floraBlocksRequireUnderBlock.add(Blocks.reeds.getStateFromMeta(15));
        floraBlockSeedRegistry.put(Blocks.reeds.getStateFromMeta(15), new ItemStack(Item.getItemFromBlock(Blocks.reeds)));

        EcoFlora.registerFloraBlockDrop(Blocks.cactus.getStateFromMeta(15), new ItemStack(Item.getItemFromBlock(Blocks.cactus)));
        floraBlocksRequireUnderBlock.add(Blocks.cactus.getStateFromMeta(15));
        floraBlockSeedRegistry.put(Blocks.cactus.getStateFromMeta(15), new ItemStack(Item.getItemFromBlock(Blocks.cactus)));

        EcoFlora.registerFloraBlockDrop(Blocks.melon_block.getStateFromMeta(0), new ItemStack(Items.melon));
        floraBlockSeedRegistry.put(Blocks.melon_block.getStateFromMeta(0), new ItemStack(Items.melon_seeds));

        EcoFlora.registerFloraBlockDrop(Blocks.pumpkin.getStateFromMeta(0), new ItemStack(Item.getItemFromBlock(Blocks.pumpkin)));
        floraBlockSeedRegistry.put(Blocks.pumpkin.getStateFromMeta(0), new ItemStack(Items.pumpkin_seeds));

        //EcoFlora.registerFloraBlockDrop(Blocks.leaves.getStateFromMeta(0), new ItemStack(Items.apple));

        //Tree Saplings
        for (int i = 0; i < 16; i++)
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
        }

        //Resources
        //for (int i = 0; i < 16; i++)
        //    floraBlockDropRegistry.put(Blocks.log.getStateFromMeta(i), new ItemStack(Items.apple));

    }
    public static void registerFloraBlockDrop(IBlockState state, List<ItemStack> drops)
    {
        for (int i = 0; i < floraBlockDropStateRegistry.size(); i++)
        {
            if (BlockHelper.getBlockStateEquals(state, floraBlockDropStateRegistry.get(i)))
            {
                for (int k = 0; k < drops.size(); k++)
                    for (int j = 0; j < floraBlockDropListRegistry.get(i).size(); j++)
                        if (ItemStack.areItemsEqual(floraBlockDropListRegistry.get(i).get(j), drops.get(k)))
                            continue;
                        else
                            floraBlockDropListRegistry.get(i).add(drops.get(k));
                return;
            }
        }

        List<ItemStack> l = new ArrayList<ItemStack>();
        l.addAll(drops);

        floraBlockDropStateRegistry.add(state);
        floraBlockDropListRegistry.add(l);
        return;
    }
    public static void registerFloraBlockDrop(IBlockState state, ItemStack drop)
    {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        list.add(drop);
        EcoFlora.registerFloraBlockDrop(state, list);
    }
    public static void registerFloraBlockDrop(IBlockState state, ItemStack seed, ItemStack[] drops)
    {
        if (state == null)
            return;

        if (drops != null)
            for (ItemStack stack: drops)
                EcoFlora.registerFloraBlockDrop(state, stack);

        if (seed != null)
        {
            EcoFlora.registerFloraBlockDrop(state, seed);
            floraBlockSeedRegistry.put(state, seed);
        }
    }
    public static ItemStack getFloraBlockSeed(IBlockState b)
    {
        Set<IBlockState> blocks = floraBlockSeedRegistry.keySet();
        for (IBlockState bi: blocks)
            if (Block.isEqualTo(b.getBlock(), bi.getBlock()))
                return floraBlockSeedRegistry.get(bi);

        return null;
    }
    public static List<ItemStack> getFloraBlockDrops(IBlockState b)
    {
        for (int i = 0; i < floraBlockDropStateRegistry.size(); i++)
            if (BlockHelper.getBlockStateEquals(b, floraBlockDropStateRegistry.get(i)))
                return floraBlockDropListRegistry.get(i);

        return null;
    }

    public static void registerCrop(BlockCrops crop, IBlockState ripe, ItemStack seed, ItemStack[] drops)
    {
        if (!cropRegistry.contains(crop))
            cropRegistry.add(crop);

        EcoFlora.registerFloraBlockDrop(ripe, seed, drops);
    }
    public static void registerEcoCrop(BaseEcoCrop crop)
    {
        if (!cropRegistry.contains(crop))
            cropRegistry.add(crop);

        List<ItemStack> nd = new ArrayList<ItemStack>();
        nd.add(new ItemStack(crop.getSeed()));
        nd.add(new ItemStack(crop.getCrop()));
        EcoFlora.registerFloraBlockDrop(crop.getStateFromMeta(7), nd);

        return;
    }
    public static int getCropRegistryCount()
    {
        return cropRegistry.size();
    }
    public static BlockCrops getCrop(int index)
    {
        return cropRegistry.get(index);
    }
}
