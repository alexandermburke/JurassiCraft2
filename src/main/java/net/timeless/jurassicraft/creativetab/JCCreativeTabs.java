package net.timeless.jurassicraft.creativetab;

import net.minecraft.item.Item;
import net.timeless.jurassicraft.block.JCBlockRegistry;
import net.timeless.jurassicraft.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.item.JCItemRegistry;

public class JCCreativeTabs
{
    public static CreativeTabJurassiCraft items;
    public static CreativeTabJurassiCraft blocks;

    public static CreativeTabJurassiCraft dna;
    public static CreativeTabJurassiCraft eggs;
    public static CreativeTabJurassiCraft spawnEggs;
    public static CreativeTabJurassiCraft decorations;
    
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
                return JCItemRegistry.fossil;
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

        decorations = new CreativeTabJurassiCraft("jurassicraft.decorations")
        {
            public Item getTabIconItem()
            {
                return Item.getItemFromBlock(JCBlockRegistry.encased_fossil);
            }
        };
        
        blocks = new CreativeTabJurassiCraft("jurassicraft.blocks")
        {
            public Item getTabIconItem()
            {
                return Item.getItemFromBlock(JCBlockRegistry.encased_fossil);
            }
        };
    }
}
