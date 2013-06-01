package com.hidden.data.loader.util;

import com.common.file.RelativeFile;
import com.common.file.impl.RelativeFileFactoryImpl;

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
		return RelativeFileFactoryImpl.getInstance().createRelativeFileFromPath(
				EMPTY_FOLDER);
	}

	public RelativeFile getAuthorFolder() {
		return RelativeFileFactoryImpl.getInstance().createRelativeFileFromPath(
				AUTHOR_FOLDER);
	}
}
