package com.hidden.data.producer;

import java.io.File;
import java.net.URL;

import com.common.TestUtils;
import com.hidden.data.producer.book.ConcreteLine;
import com.hidden.data.producer.book.text.TextBook;
import com.hidden.data.producer.book.text.TextFile;

public class TestObjectCreator {
	private static final TestObjectCreator INSTANCE = new TestObjectCreator();
	public static final String[] BOOK_CONTENT = {
			"Lubber...",
			"",
			"Stephen followed a lubber...",
			"",
			"One day in the national library we had a discussion. Shakes. After. His",
			"lub back: I followed. I gall his kibe.",
			"",
			"Stephen, greeting, then all amort, followed a lubber jester, a wellkempt",
			"head, newbarbered, out of the vaulted cell into a shattering daylight of",
			"no thought." };
	public static final String BOOK_FOLDER = "/book/";
	private static final String EXISTING_BOOK = BOOK_FOLDER + "sampleBook.txt";
	private static final String EMPTY_BOOK = BOOK_FOLDER + "emptyBook.txt";
	public static final int BOOK_ID = 1;
	public static final String BOOK_TITLE = "I, Robot";

	public static TestObjectCreator getInstance() {
		return INSTANCE;
	}

	private TestObjectCreator() {
		// Limit scope
	}

	public TextBook createTextBook() {
		return new TextBook(BOOK_ID, BOOK_TITLE, createTextFile());
	}

	public TextFile createTextFile() {
		return new TextFile(getFile(EXISTING_BOOK));
	}

	public TextFile createEmptyTextFile() {
		return new TextFile(getFile(EMPTY_BOOK));
	}

	public TextFile createInvalidFolderTextFile() {
		return new TextFile(getFile(BOOK_FOLDER));
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
