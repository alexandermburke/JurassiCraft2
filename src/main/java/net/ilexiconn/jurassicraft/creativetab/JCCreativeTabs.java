package net.ilexiconn.jurassicraft.creativetab;

import net.ilexiconn.jurassicraft.block.JCBlockRegistry;
import net.ilexiconn.jurassicraft.item.JCItemRegistry;
import net.ilexiconn.llibrary.common.content.IContentHandler;
import net.minecraft.item.Item;

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
