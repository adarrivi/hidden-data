package com.hidden.data.loader.service;

import com.hidden.data.common.performance.PerformanceLogged;
import com.hidden.data.db.model.Author;
import com.hidden.data.loader.BookFile;

public interface BookService {

	@PerformanceLogged(identifier = "SaveBook")
	void saveBook(BookFile bookFile, Author author);

	@PerformanceLogged(identifier = "SaveBookIfDoesntExist")
	// This is not working because BookServiceImpl is not getting proxied. Add
	// transaction manager or other alternative?
	void saveBookIfDoesntExist(BookFile bookFile, Author author);

}
