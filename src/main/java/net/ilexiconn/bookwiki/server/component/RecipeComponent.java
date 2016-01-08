package net.ilexiconn.bookwiki.server.component;

import net.ilexiconn.bookwiki.BookWiki;
import net.ilexiconn.bookwiki.api.IComponent;
import net.ilexiconn.bookwiki.BookWikiContainer;
import net.ilexiconn.bookwiki.client.render.RecipeRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author iLexiconn
 */
public class RecipeComponent implements IComponent {
    @SideOnly(Side.CLIENT)
    private RecipeRenderer recipeRenderer;

    @Override
    public char getID() {
        return 'r';
    }

    @Override
    public String init(String string, String arg, String group) {
        return string.replace(group, group + "\n\n\n\n\n\n\n");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(Minecraft mc, BookWiki bookWiki, String arg, int x, int y, int mouseX, int mouseY) {
        if (recipeRenderer == null) {
            recipeRenderer = new RecipeRenderer();
        }
        BookWikiContainer.Recipe recipe = bookWiki.getRecipeByID(arg);
        recipeRenderer.render(recipe, x + 14, y + mc.fontRendererObj.FONT_HEIGHT, mouseX, mouseY);
    }
}
