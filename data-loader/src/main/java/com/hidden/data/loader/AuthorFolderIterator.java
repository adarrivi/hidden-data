package com.hidden.data.loader;

import java.util.Iterator;

import com.common.file.RelativeFile;
import com.common.iterator.IteratorDecorator;

public class AuthorFolderIterator extends
		IteratorDecorator<AuthorFolder, RelativeFile> {

	public AuthorFolderIterator(Iterator<RelativeFile> iterator) {
		super(iterator);
	}

	@Override
	public AuthorFolder next() {
		if (!getIterator().hasNext()) {
			return AuthorFolder.createEmpty();
		}
		return new AuthorFolder(getIterator().next());
	}
}
