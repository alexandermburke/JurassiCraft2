package net.timeless.jurassicraft.creativetab;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.block.JCBlockRegistry;
import net.timeless.jurassicraft.item.JCItemRegistry;

public class JCCreativeTabs
{
    public static CreativeTabJurassiCraft items;
    public static CreativeTabJurassiCraft blocks;
    public static CreativeTabJurassiCraft dna;
    public static CreativeTabJurassiCraft eggs;
    public static CreativeTabJurassiCraft spawnEggs;

    public static void preInitCommon()
    {
        items = new CreativeTabJurassiCraft("jurassicraft.items")
        {
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem()
            {
                return JCItemRegistry.fossil;
            }
        };

        dna = new CreativeTabJurassiCraft("jurassicraft.dna")
        {
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem()
            {
                return JCItemRegistry.dna;
            }
        };

        eggs = new CreativeTabJurassiCraft("jurassicraft.eggs")
        {
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem()
            {
                return JCItemRegistry.egg;
            }
        };

        spawnEggs = new CreativeTabJurassiCraft("jurassicraft.spawnEggs")
        {
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem()
            {
                return JCItemRegistry.spawn_egg;
            }
        };

        blocks = new CreativeTabJurassiCraft("jurassicraft.blocks")
        {
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem()
            {
                return Item.getItemFromBlock(JCBlockRegistry.encased_fossil);
            }
        };
    }

    public static void initCommon()
    {

    }

    public static void postInitCommon()
    {

    }

    public static void preInitClientOnly()
    {

    }

    public static void initClientOnly()
    {

    }

    public static void postInitClientOnly()
    {

    }
}
