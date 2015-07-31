package net.timeless.jurassicraft.common.creativetab;

import net.minecraft.item.Item;
import net.timeless.jurassicraft.common.block.JCBlockRegistry;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

public class JCCreativeTabs
{
    public static CreativeTabJurassiCraft items;
    public static CreativeTabJurassiCraft blocks;

    public static CreativeTabJurassiCraft dna;
    public static CreativeTabJurassiCraft eggs;
    public static CreativeTabJurassiCraft spawnEggs;
    public static CreativeTabJurassiCraft foods;
    public static CreativeTabJurassiCraft plants;

    public void register()
    {
        items = new CreativeTabJurassiCraft("jurassicraft.items")
        {
            public int getIconItemDamage()
            {
                return JCEntityRegistry.getDinosaurId(JCEntityRegistry.tyrannosaurus_rex);
            }

            public Item getTabIconItem()
            {
                return JCItemRegistry.skull;
            }
        };

        dna = new CreativeTabJurassiCraft("jurassicraft.dna")
        {
            public int getIconItemDamage()
            {
                return JCEntityRegistry.getDinosaurId(JCEntityRegistry.tyrannosaurus_rex);
            }

            public Item getTabIconItem()
            {
                return JCItemRegistry.dna;
            }
        };

        eggs = new CreativeTabJurassiCraft("jurassicraft.eggs")
        {
            public int getIconItemDamage()
            {
                return JCEntityRegistry.getDinosaurId(JCEntityRegistry.tyrannosaurus_rex);
            }

            public Item getTabIconItem()
            {
                return JCItemRegistry.egg;
            }
        };

        spawnEggs = new CreativeTabJurassiCraft("jurassicraft.spawnEggs")
        {
            public int getIconItemDamage()
            {
                return JCEntityRegistry.getDinosaurId(JCEntityRegistry.tyrannosaurus_rex);
            }

            public Item getTabIconItem()
            {
                return JCItemRegistry.spawn_egg;
            }
        };

        foods = new CreativeTabJurassiCraft("jurassicraft.foods")
        {
            public int getIconItemDamage()
            {
                return JCEntityRegistry.getDinosaurId(JCEntityRegistry.tyrannosaurus_rex);
            }

            public Item getTabIconItem()
            {
                return JCItemRegistry.dino_meat;
            }
        };

        blocks = new CreativeTabJurassiCraft("jurassicraft.blocks")
        {
            public Item getTabIconItem()
            {
                return Item.getItemFromBlock(JCBlockRegistry.fossil_grinder);
            }
        };

        plants = new CreativeTabJurassiCraft("jurassicraft.plants")
        {
            public Item getTabIconItem()
            {
                return Item.getItemFromBlock(JCBlockRegistry.saplings[0]);
            }
        };
    }
}
