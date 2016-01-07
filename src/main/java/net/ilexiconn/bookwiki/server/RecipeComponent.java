package net.ilexiconn.bookwiki.server;

import net.ilexiconn.bookwiki.BookWiki;
import net.ilexiconn.bookwiki.client.RecipeRenderer;
import net.ilexiconn.bookwiki.api.IComponent;
import net.ilexiconn.bookwiki.client.BookWikiContainer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        recipeRenderer.render(recipe, x, y, mouseX, mouseY);
    }
}
