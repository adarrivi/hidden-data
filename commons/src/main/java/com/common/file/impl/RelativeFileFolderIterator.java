package com.common.file.impl;

import java.io.File;
import java.util.Iterator;

import com.common.file.RelativeFile;
import com.common.file.RelativeFileFactory;
import com.common.iterator.IteratorDecorator;

class RelativeFileFolderIterator extends IteratorDecorator<RelativeFile, File> {

	private RelativeFileFactory relativeFileFactory;

	RelativeFileFolderIterator(Iterator<File> iterator,
			RelativeFileFactory relativeFileFactory) {
		super(iterator);
		this.relativeFileFactory = relativeFileFactory;
	}

	@Override
	public RelativeFile next() {
		if (!getIterator().hasNext()) {
			return relativeFileFactory.createEmptyRelativeFile();
		}
		return relativeFileFactory.createRelativeFileFromFile(getIterator()
				.next());
	}

}
