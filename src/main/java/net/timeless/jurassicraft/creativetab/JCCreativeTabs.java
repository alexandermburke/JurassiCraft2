package net.timeless.jurassicraft.creativetab;

import net.ilexiconn.llibrary.common.content.IContentHandler;
import net.minecraft.item.Item;
import net.timeless.jurassicraft.block.JCBlockRegistry;
import net.timeless.jurassicraft.item.JCItemRegistry;

public class JCCreativeTabs implements IContentHandler
{
    public static CreativeTabJurassiCraft items;
    public static CreativeTabJurassiCraft blocks;

    public void init()
    {
        items = new CreativeTabJurassiCraft("jurassicraft.items")
        {
            public Item getTabIconItem()
            {
                return JCItemRegistry.fossil;
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

    public void gameRegistry() throws Exception
    {

    }
}
