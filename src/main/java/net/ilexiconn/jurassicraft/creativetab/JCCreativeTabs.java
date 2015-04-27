package net.ilexiconn.jurassicraft.creativetab;

import net.ilexiconn.llibrary.IContentHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
                return Items.bone;
            }
        };

        blocks = new CreativeTabJurassiCraft("jurassicraft.blocks")
        {
            public Item getTabIconItem()
            {
                return Item.getItemFromBlock(Blocks.stone);
            }
        };
    }

    public void gameRegistry() throws Exception
    {

    }
}
