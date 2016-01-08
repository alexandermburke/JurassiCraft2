package net.ilexiconn.bookwiki.api;

import net.ilexiconn.bookwiki.BookWiki;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author iLexiconn
 */
@SideOnly(Side.CLIENT)
public interface IComponent {
    char getID();

    String init(String string, String arg, String group);

    void render(Minecraft mc, BookWiki bookWiki, String arg, int x, int y, int mouseX, int mouseY);
}
