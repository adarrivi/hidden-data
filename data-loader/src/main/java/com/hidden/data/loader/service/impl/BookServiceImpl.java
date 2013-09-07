package com.hidden.data.loader.service.impl;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hidden.data.common.performance.PerformanceLogged;
import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.model.Author;
import com.hidden.data.db.model.Book;
import com.hidden.data.loader.BookFile;
import com.hidden.data.loader.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger LOG = LoggerFactory
			.getLogger(BookServiceImpl.class);

	@Autowired
	private BookDao bookDao;

	@Override
	@PerformanceLogged(identifier = "saveBookIfDoesntExist")
	public void saveBookIfDoesntExist(BookFile bookFile, Author author) {
		Book book = bookDao.findByTitle(bookFile.getTitle());
		if (book.isEmpty()) {
			saveBook(bookFile, author);
		}
	}

	private void saveBook(BookFile bookFile, Author author) {
		try {
			Book book = Book.createEmptyBook();
			book.setAuthor(author);
			book.setTitle(bookFile.getTitle());
			// TODO move this FileUtils method inside CommonsFileUtils so it is
			// easily mockable
			book.setContent(FileUtils.readFileToString(bookFile.getFile()));
			bookDao.save(book);
		} catch (IOException e) {
			LOG.error("IOException while getting the content from the book "
					+ bookFile.getTitle(), e);
		}
	}

}
