package com.hidden.data.db.util;

import com.common.reflexion.Reflexion;
import com.hidden.data.db.model.Book;

public class TestObjectFactory {

	public static final Integer BOOK_ID = Integer.valueOf(1);
	public static final String BOOK_TITLE = "Ulysses";
	public static final String BOOK_CONTENT = "Stately, plump Buck Mulligan came from the stairhead, bearing a bowl of lather on which a mirror and a razor lay crossed.";
	private static final TestObjectFactory INSTANCE = new TestObjectFactory();

	public static TestObjectFactory getInstance() {
		return INSTANCE;
	}

	private TestObjectFactory() {
		// limit scope
	}

	public Book createNewBook() {
		return Book.createBook(BOOK_TITLE, BOOK_CONTENT);
	}

	public Book createBook() {
		Book book = createNewBook();
		Reflexion.getInstance().setMember(book, "id", BOOK_ID);
		return book;
	}

}
