package com.common.file.reader;

import java.io.File;

import com.common.file.FileLineIterator;
import com.common.file.RelativeFile;
import com.common.file.RelativeFileFactory;
import com.common.file.impl.RelativeFileFactoryImpl;

public final class FileLineIteratorFactory {
	private static final FileLineIteratorFactory INSTANCE = new FileLineIteratorFactory();
	private static final RelativeFileFactory FILE_FACTORY = RelativeFileFactoryImpl
			.getInstance();

	private FileLineIteratorFactory() {
		// limit scope
	}

	public static FileLineIteratorFactory getInstance() {
		return INSTANCE;
	}

	public FileLineIterator<String> createBufferedFileLineIterator(
			String relativeFilePath) {
		RelativeFile file = FILE_FACTORY.createRelativeFile(relativeFilePath);
		RelativeFileReader reader = new RelativeFileReader(file);
		RelativeBufferedReader bufferedReader = new RelativeBufferedReader(
				reader);
		return new BufferedReaderIterator(bufferedReader.getBufferedReader());
	}

	public File getFile(String relativePath) {
		RelativeFile relativeFile = FILE_FACTORY
				.createRelativeFile(relativePath);
		return relativeFile.getFile();
	}
}
