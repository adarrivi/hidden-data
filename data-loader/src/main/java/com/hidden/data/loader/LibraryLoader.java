package com.hidden.data.loader;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.common.file.io.IOCommonsFileUtils;
import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.model.Author;
import com.hidden.data.db.model.Book;

@Component("libraryLoader")
public class LibraryLoader {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private AuthorDao authorDao;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationDbContext.xml");
		// context will get closed at JVM runtime
		ctx.registerShutdownHook();
		LibraryLoader libraryLoader = (LibraryLoader) ctx
				.getBean("libraryLoader");
		libraryLoader.start();
	}

	private void start() throws IOException {
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
				if (book.isEmpty()) {
					book.setAuthor(author);
					book.setTitle(bookFile.getTitle());
					book.setContent(FileUtils.readFileToString(bookFile
							.getFile()));
					book = bookDao.save(book);
				}
			}
		}
	}
}
