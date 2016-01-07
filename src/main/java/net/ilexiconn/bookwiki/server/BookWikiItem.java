package net.ilexiconn.bookwiki.server;

import net.ilexiconn.bookwiki.BookWiki;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class BookWikiItem extends Item {
    private BookWiki bookWiki;

    public BookWikiItem(BookWiki bookWiki) {
        this.bookWiki = bookWiki;
        setUnlocalizedName("bookWiki." +  bookWiki.getMod().modid());
        setCreativeTab(CreativeTabs.tabMisc);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (!player.isSneaking() && FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            Minecraft.getMinecraft().displayGuiScreen(bookWiki.getGuiScreen());
        }
        return itemStack;
    }
}
