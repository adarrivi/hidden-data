package com.common.util;

import java.io.File;

import com.common.file.RelativeFile;
import com.common.file.impl.RelativeFileFactoryImpl;
import com.common.file.reader.FileLineIteratorFactory;

public class TestCommonsObjectFactory {

	public static final String FILES_FOLDER = "/file/";
	public static final String FILE_NAME = "Lorem_ipsum_dolor.txt";
	public static final String NOT_EXISTING_FILE_NAME = "doesNotExist.txt";

	private static final TestCommonsObjectFactory INSTANCE = new TestCommonsObjectFactory();

	private TestCommonsObjectFactory() {
		// limit scope
	}

	public static TestCommonsObjectFactory getInstance() {
		return INSTANCE;
	}

	public File getExistingFile() {
		return FileLineIteratorFactory.getInstance().getFile(
				FILES_FOLDER + FILE_NAME);
	}

	public RelativeFile getRelativeFile() {
		return RelativeFileFactoryImpl.getInstance().createRelativeFileFromPath(
				FILES_FOLDER + FILE_NAME);
	}
}
