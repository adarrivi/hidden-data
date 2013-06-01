package com.hidden.data.producer.util;

import java.util.Iterator;

import com.common.file.reader.FileLineIterator;
import com.hidden.data.producer.book.Book;
import com.hidden.data.producer.book.BookFactory;
import com.hidden.data.producer.book.Line;

public class TestObjectFactory {
	private static final TestObjectFactory INSTANCE = new TestObjectFactory();
	public static final String[] BOOK_CONTENT = {
			"Lorem ipsum dolor sit amet, consectetur adipisicing elit,",
			"sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
			"",
			"Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris ",
			"nisi ut aliquip ex ea commodo consequat.",
			"",
			"Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
			"",
			"Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." };
	public static final int BOOK_ID = 1;
	public static final String BOOK_TITLE = "I, Robot";

	public static TestObjectFactory getInstance() {
		return INSTANCE;
	}

	private TestObjectFactory() {
		// Limit scope
	}

	public FileLineIterator<String> createStringArrayIterator() {
		return new FileLineIteratorStub(BOOK_CONTENT);
	}

	public FileLineIterator<String> createCustomArrayIterator(String content[]) {
		return new FileLineIteratorStub(content);
	}

	public FileLineIterator<String> createEmptyStringArrayIterator() {
		return new FileLineIteratorStub(new String[0]);
	}

	public Book<String> createTextBook() {
		Iterator<Line<String>> bookIterator = BookFactory.getInstance()
				.createTextBookIterator(BOOK_ID, createStringArrayIterator());
		return BookFactory.getInstance().createTextBook(BOOK_ID, BOOK_TITLE,
				bookIterator);
	}

}
