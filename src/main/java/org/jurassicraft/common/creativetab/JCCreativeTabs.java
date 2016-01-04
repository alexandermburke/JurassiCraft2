package org.jurassicraft.common.creativetab;

import net.minecraft.item.Item;
import org.jurassicraft.common.block.JCBlockRegistry;
import org.jurassicraft.common.item.JCItemRegistry;

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

    public static CreativeTabJurassiCraftMerchandise merchandise;

    public void register()
    {
        items = new CreativeTabJurassiCraft("jurassicraft.items")
        {
            public Item getTabIconItem()
            {
                return JCItemRegistry.amber;
            }
        };

        bones = new CreativeTabJurassiCraftFossils();

        dna = new CreativeTabJurassiCraftDNAs();

        eggs = new CreativeTabJurassiCraftEggs();

        spawnEggs = new CreativeTabJurassiCraftSpawnEggs();

        foods = new CreativeTabJurassiCraftFoods();

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

        merchandise = new CreativeTabJurassiCraftMerchandise();
    }
}
