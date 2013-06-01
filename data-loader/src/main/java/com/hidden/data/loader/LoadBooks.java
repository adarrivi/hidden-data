package com.hidden.data.loader;

import java.io.File;

import com.common.file.io.IOCommonsFileUtils;

public class LoadBooks {

	public static void main(String[] args) {

		File libraryFolder = new File("D:/eclipse/dataMng/books/");
		Library library = new Library(libraryFolder,
				IOCommonsFileUtils.getInstance());
		for (AuthorFolder authorFolder : library.getAuthors()) {
			System.out.println(authorFolder.getAuthorName());
			for (BookFile book : authorFolder.getBookFiles()) {
				System.out.println(book.getTitle() + " "
						+ book.getFile().length());
			}
		}

	}

}
