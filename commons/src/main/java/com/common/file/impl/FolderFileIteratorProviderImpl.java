package com.common.file.impl;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.common.file.FolderFileIteratorProvider;
import com.common.file.RelativeFile;

class FolderFileIteratorProviderImpl implements FolderFileIteratorProvider {

	private RelativeFile folder;

	FolderFileIteratorProviderImpl(RelativeFile folder) {
		this.folder = folder;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<File> getFileIterator() {
		return FileUtils.iterateFiles(folder.getFile(),
				TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
	}

}
