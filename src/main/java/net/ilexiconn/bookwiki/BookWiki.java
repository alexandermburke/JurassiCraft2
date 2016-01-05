package net.ilexiconn.bookwiki;

import net.ilexiconn.llibrary.common.json.JsonFactory;
import net.ilexiconn.llibrary.common.log.LoggerHelper;

import java.io.Reader;

/**
 * @author iLexiconn
 */
public class BookWiki {
    public static LoggerHelper logger = new LoggerHelper("BookWiki");

    private BookWikiContainer container;

    public static BookWiki create(Reader reader) {
        BookWiki bookWiki = new BookWiki();
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

    public BookWikiContainer getContainer() {
        return container;
    }
}
