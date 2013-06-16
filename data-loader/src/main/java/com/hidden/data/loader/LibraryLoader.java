package com.hidden.data.loader;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.file.io.IOCommonsFileUtils;
import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.model.Author;
import com.hidden.data.db.model.Book;

@Component
public class LibraryLoader implements Runnable {

	private static final Logger LOG = Logger.getLogger(LibraryLoader.class);

	@Autowired
	private BookDao bookDao;
	@Autowired
	private AuthorDao authorDao;

	@Override
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
				Book book = bookDao.findByTitle(bookFile.getTitle());
				saveBookIfNew(book, author, bookFile);
			}
		}
	}

	private void saveBookIfNew(Book book, Author author, BookFile bookFile) {
		try {
			if (book.isEmpty()) {
				book.setAuthor(author);
				book.setTitle(bookFile.getTitle());
				book.setContent(FileUtils.readFileToString(bookFile.getFile()));
				bookDao.save(book);
			}
		} catch (IOException e) {
			LOG.error("IOException while getting the content from the book "
					+ book.getTitle(), e);
		}
	}
}
