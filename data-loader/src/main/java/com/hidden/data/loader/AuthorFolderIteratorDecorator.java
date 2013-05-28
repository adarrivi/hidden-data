package com.hidden.data.loader;

import java.util.Iterator;

import com.common.file.RelativeFile;

public class AuthorFolderIteratorDecorator implements Iterator<AuthorFolder> {

	private Iterator<RelativeFile> iterator;

	public AuthorFolderIteratorDecorator(Iterator<RelativeFile> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public AuthorFolder next() {
		return new AuthorFolder(iterator.next());
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

}
