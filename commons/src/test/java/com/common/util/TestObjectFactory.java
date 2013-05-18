package com.common.util;

import java.io.File;

import com.common.file.impl.FileLineIteratorFactory;

public class TestObjectFactory {

	public static final String FILES_FOLDER = "/file/";
	public static final String FILE_NAME = "Lorem_ipsum_dolor.txt";
	public static final String NOT_EXISTING_FILE_NAME = "doesNotExist.txt";

	private static final TestObjectFactory INSTANCE = new TestObjectFactory();

	private TestObjectFactory() {
		// limit scope
	}

	public static TestObjectFactory getInstance() {
		return INSTANCE;
	}

	public File getExistingFile() {
		return FileLineIteratorFactory.getInstance().getFile(
				FILES_FOLDER + FILE_NAME);
	}

}
