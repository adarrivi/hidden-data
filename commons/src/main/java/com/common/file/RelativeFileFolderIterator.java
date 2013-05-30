package com.common.file;

import java.io.File;
import java.util.Iterator;

import com.common.iterator.IteratorDecorator;

public class RelativeFileFolderIterator extends
		IteratorDecorator<RelativeFile, File> {

	public RelativeFileFolderIterator(Iterator<File> iterator) {
		super(iterator);
	}

	@Override
	public RelativeFile next() {
		if (!getIterator().hasNext()) {
			return RelativeFile.createEmpty();
		}
		return new RelativeFile(getIterator().next().getPath());
	}

}
