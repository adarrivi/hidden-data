package com.common.file.impl;

import com.common.file.FolderFileIteratorProvider;
import com.common.file.RelativeFile;

public class FileFactory {

	private static final FileFactory INSTANCE = new FileFactory();

	public static FileFactory getInstance() {
		return INSTANCE;
	}

	private FileFactory() {
		// limiting scope
	}

	public FolderFileIteratorProvider createFolderFileIterator(
			RelativeFile folder) {
		return new FolderFileIteratorProviderImpl(folder);
	}

}
