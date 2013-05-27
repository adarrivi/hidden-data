package com.hidden.data.loader;

import java.util.Iterator;

import com.common.file.RelativeFile;

public class BooksFolder extends RelativeFileIterable<AuthorFolder> {

	public BooksFolder(RelativeFile folder) {
		super(folder);
	}

	@Override
	public Iterator<AuthorFolder> iterator() {
		return null;
	}

}
