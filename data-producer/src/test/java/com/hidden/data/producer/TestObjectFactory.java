package com.hidden.data.producer;

import java.io.File;
import java.net.URL;

import com.common.TestUtils;
import com.hidden.data.producer.book.ConcreteLine;
import com.hidden.data.producer.book.text.TextBook;
import com.hidden.data.producer.book.text.TextFile;
import com.hidden.data.producer.file.BufferFileReader;

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
	public static final String BOOK_FOLDER = "/book/";
	private static final String EXISTING_BOOK = BOOK_FOLDER + "sampleBook.txt";
	public static final int BOOK_ID = 1;
	public static final String BOOK_TITLE = "I, Robot";

	public static TestObjectFactory getInstance() {
		return INSTANCE;
	}

	private TestObjectFactory() {
		// Limit scope
	}

	public TextBook createTextBook() {
		return new TextBook(BOOK_ID, BOOK_TITLE, createTextFile());
	}

	public TextFile createTextFile() {
		return new TextFile(new BufferFileReader(getExistingFile()));
	}

	public File getFolderFile() {
		return getFile(BOOK_FOLDER);
	}

	public File getExistingFile() {
		return getFile(EXISTING_BOOK);
	}

	private File getFile(String path) {
		URL resource = TestUtils.class.getResource(path);
		if (resource == null) {
			return null;
		}
		return new File(resource.getPath());
	}

	public ConcreteLine<String> createStringLine() {
		int row = 0;
		return new ConcreteLine<String>(row, BOOK_ID, BOOK_CONTENT[row]);
	}

}
