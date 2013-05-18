package com.common.file.impl;

import java.io.File;

import com.common.file.FileLineIterator;

public final class FileLineIteratorFactory {
	private static final FileLineIteratorFactory INSTANCE = new FileLineIteratorFactory();

	private FileLineIteratorFactory() {
		// limit scope
	}

	public static FileLineIteratorFactory getInstance() {
		return INSTANCE;
	}

	public FileLineIterator<String> createBufferedFileLineIterator(
			String relativeFilePath) {
		RelativeFile file = new RelativeFile(relativeFilePath);
		RelativeFileReader reader = new RelativeFileReader(file);
		RelativeBufferedReader bufferedReader = new RelativeBufferedReader(
				reader);
		return new BufferedReaderIterator(bufferedReader.getBufferedReader());
	}

	public File getFile(String relativePath) {
		RelativeFile relativeFile = new RelativeFile(relativePath);
		return relativeFile.getFile();
	}
}
