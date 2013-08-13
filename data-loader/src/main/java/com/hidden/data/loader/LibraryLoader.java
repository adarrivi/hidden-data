package com.hidden.data.loader;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hidden.data.common.file.io.IOCommonsFileUtils;
import com.hidden.data.common.performance.PerformanceLogged;
import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.db.model.Author;
import com.hidden.data.loader.service.BookService;

@Component
public class LibraryLoader implements Runnable {

	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorDao authorDao;

	@Override
	@PerformanceLogged(identifier = "runLoad")
	public void run() {
		File libraryFolder = new File("D:/eclipse/dataMng/books/");
		Library library = new Library(libraryFolder,
				IOCommonsFileUtils.getInstance());
		for (AuthorFolder authorFolder : library.getAuthors()) {
			Author author = authorDao.findByName(authorFolder.getAuthorName());
			if (author.isEmpty()) {
				author.setName(authorFolder.getAuthorName());
				author = authorDao.save(author);
			}
			for (BookFile bookFile : authorFolder.getBookFiles()) {
				bookService.saveBookIfDoesntExist(bookFile, author);
			}
		}
	}
}
