package com.common.file;

import java.io.File;
import java.util.Iterator;

import com.common.iterator.IteratorDecorator;

public class RelativeFileIterator extends
		IteratorDecorator<RelativeFile, File> {

	public RelativeFileIterator(Iterator<File> iterator) {
		super(iterator);
	}

	@Override
	public RelativeFile next() {
		return new RelativeFile(iterator.next().getPath());
	}

}
