package com.common.file.impl;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.common.file.RelativeFile;
import com.common.file.RelativeFileFactory;

public class RelativeFileFactoryImpl implements RelativeFileFactory {

	private static final RelativeFileFactoryImpl INSTANCE = new RelativeFileFactoryImpl();

	public static RelativeFileFactoryImpl getInstance() {
		return INSTANCE;
	}

	private RelativeFileFactoryImpl() {
		// limiting scope
	}

	@Override
	public RelativeFile createRelativeFile(String relativePath) {
		return new RelativeFileImpl(relativePath);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<RelativeFile> createFolderFileIterator(RelativeFile folder) {
		Iterator<File> fileIterator = FileUtils.iterateFiles(folder.getFile(),
				TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		return new RelativeFileFolderIterator(fileIterator, INSTANCE);
	}

	@Override
	public RelativeFile createEmptyRelativeFile() {
		return RelativeFileImpl.createEmpty();
	}

}
