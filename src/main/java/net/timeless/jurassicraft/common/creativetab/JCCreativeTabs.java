package net.timeless.jurassicraft.common.creativetab;

import net.minecraft.item.Item;
import net.timeless.jurassicraft.common.block.JCBlockRegistry;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

public class JCCreativeTabs
{
    public static CreativeTabJurassiCraft items;
    public static CreativeTabJurassiCraft blocks;

    public static CreativeTabJurassiCraftDNAs dna;
    public static CreativeTabJurassiCraftEggs eggs;
    public static CreativeTabJurassiCraftSpawnEggs spawnEggs;
    public static CreativeTabJurassiCraftFoods foods;
    public static CreativeTabJurassiCraft plants;
    public static CreativeTabJurassiCraft fossils;

    public static CreativeTabJurassiCraftFossils bones;

    public void register()
    {
        items = new CreativeTabJurassiCraft("jurassicraft.items")
        {
            public Item getTabIconItem()
            {
                return JCItemRegistry.amber;
            }
        };

        bones = new CreativeTabJurassiCraftFossils("jurassicraft.dino_bones");

        dna = new CreativeTabJurassiCraftDNAs("jurassicraft.dna");

        eggs = new CreativeTabJurassiCraftEggs("jurassicraft.eggs");

        spawnEggs = new CreativeTabJurassiCraftSpawnEggs("jurassicraft.spawnEggs");

        foods = new CreativeTabJurassiCraftFoods("jurassicraft.foods");

        blocks = new CreativeTabJurassiCraft("jurassicraft.blocks")
        {
            public Item getTabIconItem()
            {
                return Item.getItemFromBlock(JCBlockRegistry.gypsum_bricks);
            }
        };

        plants = new CreativeTabJurassiCraft("jurassicraft.plants")
        {
            public Item getTabIconItem()
            {
                return Item.getItemFromBlock(JCBlockRegistry.small_royal_fern);
            }
        };

        fossils = new CreativeTabJurassiCraft("jurassicraft.fossils")
        {
            public Item getTabIconItem()
            {
                return Item.getItemFromBlock(JCBlockRegistry.encased_fossils.get(0));
            }
        };
    }
}
