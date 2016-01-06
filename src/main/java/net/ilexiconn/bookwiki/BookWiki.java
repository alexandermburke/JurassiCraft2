package net.ilexiconn.bookwiki;

import net.ilexiconn.llibrary.common.json.JsonFactory;
import net.ilexiconn.llibrary.common.log.LoggerHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.Reader;

/**
 * @author iLexiconn
 */
public class BookWiki {
    public static LoggerHelper logger = new LoggerHelper("BookWiki");

    private Object modInstance;
    private Mod mod;
    private BookWikiContainer container;

    public static BookWiki create(Object mod, Reader reader) {
        if (!mod.getClass().isAnnotationPresent(Mod.class)) {
            logger.error("Please specify the mod instance!");
            return null;
        }
        BookWiki bookWiki = new BookWiki();
        bookWiki.modInstance = mod;
        bookWiki.mod = mod.getClass().getAnnotation(Mod.class);
        bookWiki.container = JsonFactory.getGson().fromJson(reader, BookWikiContainer.class);
        for (BookWikiContainer.Category category : bookWiki.container.getCategories()) {
            category.setContainer(bookWiki.container);
        }
        for (BookWikiContainer.Page page : bookWiki.container.getPages()) {
            page.setContainer(bookWiki.container);
        }
        for (BookWikiContainer.Recipe recipe : bookWiki.container.getRecipes()) {
            recipe.setContainer(bookWiki.container);
        }
        return bookWiki;
    }

    @SideOnly(Side.CLIENT)
    public GuiScreen getGuiScreen() {
        return new BookWikiGui(this);
    }

    public void registerItem() {
        GameRegistry.registerItem(new BookWikiItem(this), "bookWiki." + getMod().modid());
    }

    public Object getModInstance() {
        return modInstance;
    }

    public Mod getMod() {
        return mod;
    }

    public BookWikiContainer getContainer() {
        return container;
    }
}
