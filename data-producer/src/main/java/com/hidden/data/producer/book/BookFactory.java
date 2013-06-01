package com.hidden.data.producer.book;

import java.util.Iterator;

import com.common.file.reader.FileLineIterator;

public class BookFactory {
	private static final BookFactory INSTANCE = new BookFactory();

	private BookFactory() {
		// limit scope
	}

	public static BookFactory getInstance() {
		return INSTANCE;
	}

	public Book<String> createTextBook(int id, String title,
			Iterator<Line<String>> fileLineIterator) {
		return new ConcreteBook<String>(id, title, fileLineIterator);
	}

	public Iterator<Line<String>> createTextBookIterator(int bookId,
			FileLineIterator<String> fileContentIterator) {
		return new TextBookIterator(bookId, fileContentIterator);
	}
}
