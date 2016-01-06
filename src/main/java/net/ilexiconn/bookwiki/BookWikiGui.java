package net.ilexiconn.bookwiki;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookWikiGui extends GuiScreen {
    private BookWiki bookWiki;

    public static final ResourceLocation TEXTURE = new ResourceLocation("jurassicraft", "bookwiki/gui.png");
    private String currentPage = "general";

    public BookWikiGui(BookWiki bookWiki) {
        this.bookWiki = bookWiki;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

    }
}
