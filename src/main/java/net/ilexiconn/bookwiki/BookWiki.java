package net.ilexiconn.bookwiki;

import com.google.common.collect.Lists;
import net.ilexiconn.bookwiki.client.gui.BookWikiGui;
import net.ilexiconn.bookwiki.server.item.BookWikiItem;
import net.ilexiconn.llibrary.common.json.JsonFactory;
import net.ilexiconn.llibrary.common.log.LoggerHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.Reader;
import java.util.List;

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

    public BookWikiContainer.Category getCategoryByID(String id) {
        for (BookWikiContainer.Category category : getContainer().getCategories()) {
            if (category.getID().equals(id)) {
                return category;
            }
        }
        return null;
    }

    public BookWikiContainer.Page getPageByID(String id) {
        for (BookWikiContainer.Page page : getContainer().getPages()) {
            if (page.getID().equals(id)) {
                return page;
            }
        }
        return null;
    }

    public BookWikiContainer.Page[] getPagesFromCategory(BookWikiContainer.Category category) {
        List<BookWikiContainer.Page> pageList = Lists.newArrayList();
        for (BookWikiContainer.Page page : getContainer().getPages()) {
            if (page.getCategory() == category) {
                pageList.add(page);
            }
        }
        return pageList.toArray(new BookWikiContainer.Page[pageList.size()]);
    }

    public BookWikiContainer.Recipe getRecipeByID(String id) {
        for (BookWikiContainer.Recipe recipe : getContainer().getRecipes()) {
            if (recipe.getID().equals(id)) {
                return recipe;
            }
        }
        return null;
    }
}
