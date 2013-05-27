package com.hidden.data.loader;

public class BooksFolderTest extends
		RelativeFileIterableTestTemplate<AuthorFolder> {

	@Override
	protected RelativeFileIterable<AuthorFolder> getNewVictim() {
		return new BooksFolder(folder);
	}

}
