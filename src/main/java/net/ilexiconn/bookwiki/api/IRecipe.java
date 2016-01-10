package net.ilexiconn.bookwiki.api;

import net.ilexiconn.bookwiki.BookWiki;
import net.ilexiconn.bookwiki.BookWikiContainer;
import net.ilexiconn.bookwiki.client.gui.BookWikiGui;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author iLexiconn
 */
@SideOnly(Side.CLIENT)
public interface IRecipe {
    String getType();

    void render(Minecraft mc, BookWiki bookWiki, BookWikiContainer.Recipe recipe, BookWikiGui gui, int x, int y, int mouseX, int mouseY);

    void renderTooltip(Minecraft mc, BookWiki bookWiki, BookWikiContainer.Recipe recipe, BookWikiGui gui, int x, int y, int mouseX, int mouseY);

    int getHeight();
}
