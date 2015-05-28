package net.reuxertz.ecocraft.core;


import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.world.BlockEvent;

import java.util.Arrays;
import java.util.List;

public class EcoResources {

    //Tool Materials
    public static final Item.ToolMaterial ToolMaterialFlint = EnumHelper.addToolMaterial("FLINT", 1, 131, 4.0F, 1, 26);

    //Earth Drop Fields
    private static List<Block> _earthDropBlocks = Arrays.asList(Blocks.dirt, Blocks.grass, Blocks.sand, Blocks.gravel,
            Blocks.sandstone, Blocks.stone );
    private static List<ItemStack> _earthDrops = Arrays.asList(
            new ItemStack(Items.flint), new ItemStack(EcoRegistry.ironNugget), new ItemStack(Items.gold_nugget),
            new ItemStack(EcoRegistry.diamondShard),  new ItemStack(EcoRegistry.emeraldShard) );
    private static double _earthDropConstant = .055;
    private static List<Double> _earthDropProbs = Arrays.asList(1.0, 0.1, 0.01, 0.005, 0.001);

    //Earth Block Handlers
    public static void RegisterBlockAsEarthDrop(Block b)
    {
        if (!EcoResources._earthDropBlocks.contains(b))
            EcoResources._earthDropBlocks.add(b);
    }
    public static void RegisterItemAsEarthDrop(ItemStack is)
    {
        EcoResources.RegisterItemAsEarthDrop(is , false);
    }
    public static void RegisterItemAsEarthDrop(ItemStack is, boolean override)
    {
        if (!override)
            for (int i = 0; i < EcoResources._earthDrops.size(); i++)
                if (EcoResources._earthDrops.get(i).getItem() == is.getItem())
                    return;

        EcoResources._earthDrops.add(is);
    }
    public static void HandleRandomEarthDrops(BlockEvent.HarvestDropsEvent e)
    {
        Block broken = e.state.getBlock();
        boolean cont = false;
        for (int i = 0; i < EcoResources._earthDropBlocks.size(); i++)
            if (broken == EcoResources._earthDropBlocks.get(i))
            {
               cont = true;
                break;
            }

        if (!cont)
            return;

        if (EcoCraft.RND.nextDouble() > EcoResources._earthDropConstant)
            return;

        for (int i = EcoResources._earthDropProbs.size() - 1; i >= 0; i--)
            if (EcoCraft.RND.nextDouble() > EcoResources._earthDropProbs.get(i))
                continue;
            else
            {
                e.drops.add(EcoResources._earthDrops.get(i).copy());
                break;
            }

    }


}

