package net.reuxertz.ecoapi.ecology;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.world.BlockEvent;
import net.reuxertz.ecoapi.util.BlockReplaceConditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EcoTerra
{
    //Tool Materials
    public static final Item.ToolMaterial toolMaterialWood = EnumHelper.addToolMaterial("WOOD", 0, 21, 1.5F, 0, 26);
    public static final Item.ToolMaterial toolMaterialStone = EnumHelper.addToolMaterial("STONE", 0, 41, 2.0F, 1, 26);
    public static final Item.ToolMaterial toolMaterialFlint = EnumHelper.addToolMaterial("FLINT", 0, 131, 4.0F, 1.5f, 26);
    public static final Item.ToolMaterial toolMaterialBone = EnumHelper.addToolMaterial("BONE", 0, 131, 4.0F, 1.5f, 26);
    public static final Item.ToolMaterial toolMaterialCopper = EnumHelper.addToolMaterial("COPPER", 1, 250, 6.0F, 2, 26);
    public static final Item.ToolMaterial toolMaterialBronze = EnumHelper.addToolMaterial("BRONZE", 2, 500, 8.0F, 3, 26);
    public static final Item.ToolMaterial toolMaterialIron = EnumHelper.addToolMaterial("IRON", 3, 1000, 10.0F, 3, 26);
    public static final Item.ToolMaterial toolMaterialSteel = EnumHelper.addToolMaterial("STEEL", 4, 1750, 12.0F, 4, 26);

    //Armor Materials
    public static ItemArmor.ArmorMaterial armorMaterialLeather = EnumHelper.addArmorMaterial("LEATHER", "ecocraft:leather", 5, new int[]{1, 2, 1, 1}, 30);

    public static ItemArmor.ArmorMaterial armorMaterialCopperStudded = EnumHelper.addArmorMaterial("COPPERSTUDDED", "ecocraft:copper_studded", 10, new int[]{1, 3, 2, 1}, 30);
    public static ItemArmor.ArmorMaterial armorMaterialCopperChain = EnumHelper.addArmorMaterial("COPPERCHAIN", "ecocraft:copper_chain", 12, new int[]{1, 4, 3, 1}, 30);
    public static ItemArmor.ArmorMaterial armorMaterialCopper = EnumHelper.addArmorMaterial("COPPER", "ecocraft:copper", 14, new int[]{2, 5, 3, 2}, 30);

    public static ItemArmor.ArmorMaterial armorMaterialBronzeStudded = EnumHelper.addArmorMaterial("BRONZESTUDDED", "ecocraft:bronze_studded", 14, new int[]{2, 4, 3, 2}, 30);
    public static ItemArmor.ArmorMaterial armorMaterialBronzeChain = EnumHelper.addArmorMaterial("BRONZECHAIN", "ecocraft:bronze_chain", 18, new int[]{2, 5, 3, 2}, 30);
    public static ItemArmor.ArmorMaterial armorMaterialBronze = EnumHelper.addArmorMaterial("BRONZE", "ecocraft:bronze", 23, new int[]{2, 6, 4, 2}, 30);

    public static ItemArmor.ArmorMaterial armorMaterialIronStudded = EnumHelper.addArmorMaterial("IRONSTUDDED", "ecocraft:iron_studded", 23, new int[]{2, 5, 3, 2}, 30);
    public static ItemArmor.ArmorMaterial armorMaterialIronChain = EnumHelper.addArmorMaterial("IRON", "ecocraft:iron_chain", 27, new int[]{3, 6, 4, 3}, 30);
    public static ItemArmor.ArmorMaterial armorMaterialIron = EnumHelper.addArmorMaterial("IRON", "ecocraft:iron", 31, new int[]{3, 7, 5, 3}, 30);

    public static ItemArmor.ArmorMaterial armorMaterialSteelStudded = EnumHelper.addArmorMaterial("STEELSTUDDED", "ecocraft:steel_studded", 31, new int[]{3, 6, 4, 3}, 30);
    public static ItemArmor.ArmorMaterial armorMaterialSteelChain = EnumHelper.addArmorMaterial("STEELCHAIN", "ecocraft:steel_chain", 35, new int[]{3, 7, 5, 3}, 30);
    public static ItemArmor.ArmorMaterial armorMaterialSteel = EnumHelper.addArmorMaterial("STEEL", "ecocraft:steel", 40, new int[]{3, 8, 6, 3}, 30);

    private static Random random = new Random();

    //Terra Replace Registry
    private static List<BlockReplaceConditions> terraReplaceMap = new ArrayList<BlockReplaceConditions>();
    public static void registerBlockChange(IBlockState start, IBlockState replace)
    {
        EcoTerra.terraReplaceMap.add(new BlockReplaceConditions(start, replace));
    }
    public static void registerBlockChange(Block start, IBlockState replace)
    {
        EcoTerra.terraReplaceMap.add(new BlockReplaceConditions(start, replace));
    }
    public static void registerBlockChange(BlockReplaceConditions conditions)
    {
        terraReplaceMap.add(conditions);
    }
    public static void performBlockChange(World w, Chunk c)
    {
        int xs = c.xPosition * 16; int zs = c.zPosition * 16;
        for (BlockReplaceConditions next: terraReplaceMap)
        {
            for (int x = 0; x < 16; x++)
            {
                for (int z = 0; z < 16; z++)
                {
                    if (!next.isBiomeSatisfied(w, c, x, z))
                        return;

                    for (int y = 0; y < c.getHeight(new BlockPos(x, 0, z)) + 10; y++)
                    {
                        BlockPos pos = new BlockPos(xs + x, y, zs + z);
                        IBlockState state = c.getBlockState(pos);
                        next.attemptPerform(w, c, state, pos);
                    }
                }
            }
        }

        //c.setModified(true);
    }

    //Earth Drop Registry
    private static List<Block> earthDropBlocks = Arrays.asList(Blocks.dirt, Blocks.grass, Blocks.sand, Blocks.gravel, Blocks.sandstone, Blocks.stone);
    private static List<ItemStack> earthDrops = Arrays.asList(new ItemStack(Items.flint), new ItemStack(EcoItems.ironNugget), new ItemStack(Items.gold_nugget), new ItemStack(EcoItems.diamondShard), new ItemStack(EcoItems.emeraldShard));
    private static double earthDropConstant = 0.055;
    private static List<Double> earthDropProbs = Arrays.asList(1.0, 0.1, 0.01, 0.005, 0.001);

    //Earth Drop Functions
    public static void registerBlockAsEarthDrop(Block b)
    {
        if (!EcoTerra.earthDropBlocks.contains(b))
            EcoTerra.earthDropBlocks.add(b);
    }
    public static void registerItemAsEarthDrop(ItemStack is)
    {
        EcoTerra.registerItemAsEarthDrop(is, false);
    }
    public static void registerItemAsEarthDrop(ItemStack is, boolean override)
    {
        if (!override)
            for (int i = 0; i < EcoTerra.earthDrops.size(); i++)
                if (EcoTerra.earthDrops.get(i).getItem() == is.getItem())
                    return;

        EcoTerra.earthDrops.add(is);
    }
    public static void handleRandomEarthDrops(BlockEvent.HarvestDropsEvent e)
    {
        Block broken = e.state.getBlock();
        boolean cont = false;
        for (int i = 0; i < EcoTerra.earthDropBlocks.size(); i++)
        {
            if (broken == EcoTerra.earthDropBlocks.get(i))
            {
                cont = true;
                break;
            }
        }

        if (!cont)
            return;

        if (random.nextDouble() > EcoTerra.earthDropConstant)
            return;

        for (int i = EcoTerra.earthDropProbs.size() - 1; i >= 0; i--)
        {
            if (random.nextDouble() < EcoTerra.earthDropProbs.get(i))
            {
                e.drops.add(EcoTerra.earthDrops.get(i).copy());
                break;
            }
        }
    }

}
