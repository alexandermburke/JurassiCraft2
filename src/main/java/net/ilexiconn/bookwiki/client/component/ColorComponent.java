package net.ilexiconn.bookwiki.client.component;

import net.ilexiconn.bookwiki.BookWiki;
import net.ilexiconn.bookwiki.api.IComponent;
import net.ilexiconn.bookwiki.client.gui.BookWikiGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author iLexiconn
 */
@SideOnly(Side.CLIENT)
public class ColorComponent extends Gui implements IComponent {
    @Override
    public char getID() {
        return 'c';
    }

    @Override
    public String init(String string, String arg, String group, BookWiki bookWiki) {
        EnumChatFormatting formatting = EnumChatFormatting.getValueByName(arg.toUpperCase());
        if (formatting != null) {
            string = string.replace(group, formatting + "");
        }
        return string;
    }

    @Override
    public void render(Minecraft mc, BookWiki bookWiki, String arg, BookWikiGui gui, int x, int y, int mouseX, int mouseY) {

    }

    @Override
    public void renderTooltip(Minecraft mc, BookWiki bookWiki, String arg, BookWikiGui gui, int x, int y, int mouseX, int mouseY) {

    }
}
