package net.ilexiconn.bookwiki;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookWikiGui extends GuiScreen {
    private BookWiki bookWiki;

    private String currentPage = "general";

    public BookWikiGui(BookWiki bookWiki) {
        this.bookWiki = bookWiki;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
