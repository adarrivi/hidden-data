package com.hidden.data.loader;

import java.util.Iterator;

import com.common.file.RelativeFile;
import com.common.file.RelativeFileFactory;
import com.common.iterator.IteratorDecorator;

public class AuthorFolderIterator extends
		IteratorDecorator<AuthorFolder, RelativeFile> {

	private RelativeFileFactory relativeFileFactory;

	public AuthorFolderIterator(Iterator<RelativeFile> iterator,
			RelativeFileFactory relativeFileFactory) {
		super(iterator);
		this.relativeFileFactory = relativeFileFactory;
	}

	@Override
	public AuthorFolder next() {
		if (!getIterator().hasNext()) {
			return new AuthorFolder(
					relativeFileFactory.createEmptyRelativeFile(),
					relativeFileFactory);
		}
		return new AuthorFolder(getIterator().next(), relativeFileFactory);
	}
}
