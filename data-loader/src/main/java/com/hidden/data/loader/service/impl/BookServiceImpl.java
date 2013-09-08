package com.hidden.data.loader.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hidden.data.common.file.CommonsFileUtils;
import com.hidden.data.common.file.io.IOCommonsFileUtils;
import com.hidden.data.common.performance.PerformanceLogged;
import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.model.Author;
import com.hidden.data.db.model.Book;
import com.hidden.data.loader.BookFile;
import com.hidden.data.loader.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	private CommonsFileUtils fileUtils = IOCommonsFileUtils.getInstance();

	@Override
	@PerformanceLogged(identifier = "saveBookIfDoesntExist")
	public void saveBookIfDoesntExist(BookFile bookFile, Author author) {
		Book book = bookDao.findByTitle(bookFile.getTitle());
		if (book.isEmpty()) {
			saveBook(bookFile, author);
		}
	}

	private void saveBook(BookFile bookFile, Author author) {
		Book book = Book.createEmptyBook();
		book.setAuthor(author);
		book.setTitle(bookFile.getTitle());
		book.setContent(fileUtils.getFileContentAsString(bookFile.getFile()));
		bookDao.save(book);
	}

}
