package com.hidden.data.loader.util;

import com.common.file.RelativeFile;

public class TestObjectFactory {
	private static final String AUTHOR_FOLDER = "/books/James.Joyce";
	private static final String EMPTY_FOLDER = "/books/emptyFolder";
	private static final TestObjectFactory INSTANCE = new TestObjectFactory();

	public static TestObjectFactory getInstance() {
		return INSTANCE;
	}

	private TestObjectFactory() {
		// limit scope
	}

	public RelativeFile getEmptyFolder() {
		return new RelativeFile(EMPTY_FOLDER);
	}

	public RelativeFile getAuthorFolder() {
		return new RelativeFile(AUTHOR_FOLDER);
	}

}
