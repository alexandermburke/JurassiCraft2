package net.ilexiconn.bookwiki.server.component;

import net.ilexiconn.bookwiki.BookWiki;
import net.ilexiconn.bookwiki.api.IComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author iLexiconn
 */
public class ColorComponent implements IComponent {
    @Override
    public char getID() {
        return 'c';
    }

    @Override
    public String init(String string, String arg, String group) {
        EnumChatFormatting formatting = EnumChatFormatting.getValueByName(arg.toUpperCase());
        if (formatting != null) {
            string = string.replace(group, formatting + "");
        }
        return string;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(Minecraft mc, BookWiki bookWiki, String arg, int x, int y, int mouseX, int mouseY) {

    }
}
