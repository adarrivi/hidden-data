package com.hidden.data.loader;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hidden.data.common.file.CommonsFileUtils;
import com.hidden.data.common.file.io.IOCommonsFileUtils;
import com.hidden.data.common.performance.PerformanceLogged;
import com.hidden.data.common.property.FileProperties;
import com.hidden.data.common.property.PropertiesFactory;
import com.hidden.data.db.model.Author;
import com.hidden.data.loader.service.AuthorService;
import com.hidden.data.loader.service.BookService;

@Component
public class LibraryLoader implements Runnable {

	private static final Logger LOG = LoggerFactory
			.getLogger(LibraryLoader.class);

	private static FileProperties PROPERTIES = PropertiesFactory.getInstance()
			.getPropertiesFromRelativePath("/library.properties");

	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;

	private CommonsFileUtils fileUtils = IOCommonsFileUtils.getInstance();

	@Override
	@PerformanceLogged(identifier = "runLoad")
	public void run() {
		File libraryFolder = fileUtils.getFileFromAbsolutePath(PROPERTIES
				.getProperty("libraryLocation"));
		Library library = new Library(libraryFolder, fileUtils);
		for (BookFile book : library.getBooks()) {
			Author author = authorService.createAuthorIfDoesntExist(book
					.getAuthor());
			bookService.saveBookIfDoesntExist(book, author);
		}
		LOG.debug("Books loaded to the library");
	}
}
