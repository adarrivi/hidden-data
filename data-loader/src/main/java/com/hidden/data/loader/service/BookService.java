package com.hidden.data.loader.service;

import com.hidden.data.common.performance.PerformanceLogged;
import com.hidden.data.db.model.Author;
import com.hidden.data.loader.BookFile;

public interface BookService {

	@PerformanceLogged(identifier = "SaveBookIfDoesntExist")
	void saveBookIfDoesntExist(BookFile bookFile, Author author);

}
