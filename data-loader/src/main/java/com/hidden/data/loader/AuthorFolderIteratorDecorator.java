package com.hidden.data.loader;

import java.util.Iterator;

import com.common.file.RelativeFile;
import com.common.iterator.IteratorDecorator;

public class AuthorFolderIteratorDecorator extends
		IteratorDecorator<AuthorFolder, RelativeFile> {

	public AuthorFolderIteratorDecorator(Iterator<RelativeFile> iterator) {
		super(iterator);
	}

	@Override
	public AuthorFolder next() {
		if (!iterator.hasNext()) {
			return AuthorFolder.createEmpty();
		}
		return new AuthorFolder(iterator.next());
	}
}
