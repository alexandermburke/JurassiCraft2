package net.reuxertz.ecoapi.ecology;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.world.BlockEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EcoTerra
{
    //Tool Materials
    public static final Item.ToolMaterial toolMaterialFlint = EnumHelper.addToolMaterial("FLINT", 1, 131, 4.0F, 1, 26);
    public static final Item.ToolMaterial toolMaterialCopper = EnumHelper.addToolMaterial("COPPER", 1, 131, 4.0F, 1, 26);
    public static final Item.ToolMaterial toolMaterialBronze = EnumHelper.addToolMaterial("BRONZE", 1, 131, 4.0F, 1, 26);
    public static final Item.ToolMaterial toolMaterialSteel = EnumHelper.addToolMaterial("STEEL", 1, 131, 4.0F, 1, 26);

    //Armor Materials
    public static ItemArmor.ArmorMaterial armorMaterialCopper = EnumHelper.addArmorMaterial("COPPER", "ecocraft:copper", 16, new int[]{2, 5, 4, 1}, 30);
    public static ItemArmor.ArmorMaterial armorMaterialBronze = EnumHelper.addArmorMaterial("BRONZE", "ecocraft:bronze", 16, new int[]{2, 7, 5, 2}, 30);
    public static ItemArmor.ArmorMaterial armorMaterialSteel = EnumHelper.addArmorMaterial("STEEL", "ecocraft:steel", 16, new int[]{3, 8, 6, 3}, 30);

    private static Random random = new Random();

    //Earth Drop Registry
    private static List<Block> earthDropBlocks = Arrays.asList(Blocks.dirt, Blocks.grass, Blocks.sand, Blocks.gravel, Blocks.sandstone, Blocks.stone);
    private static List<ItemStack> earthDrops = new ArrayList<ItemStack>();//Arrays.asList(new ItemStack(Items.flint), new ItemStack(EcoItems.ironNugget), new ItemStack(Items.gold_nugget), new ItemStack(EcoItems.diamondShard), new ItemStack(EcoItems.emeraldShard));
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
