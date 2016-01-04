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
        return bookWiki;
    }

    public BookWikiContainer getContainer() {
        return container;
    }
}
