package com.hidden.data.loader;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hidden.data.common.file.io.IOCommonsFileUtils;
import com.hidden.data.common.performance.PerformanceLogged;
import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.db.model.Author;
import com.hidden.data.loader.service.BookService;

@Component
public class LibraryLoader implements Runnable {

	private static final Logger LOG = LoggerFactory
			.getLogger(LibraryLoader.class);

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
		for (BookFile book : library.getBooks()) {
			Author author = authorDao.findByName(book.getAuthor());
			if (author.isEmpty()) {
				author.setName(book.getAuthor());
				author = authorDao.save(author);
			}
			bookService.saveBookIfDoesntExist(book, author);
		}
		LOG.debug("Books loaded to the library");
	}
}
