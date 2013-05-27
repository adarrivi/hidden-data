package com.hidden.data.db;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.model.Author;
import com.hidden.data.db.model.Book;

@Component("dataBaseApp")
public class DataBaseApp {

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
		DataBaseApp dataBaseApp = (DataBaseApp) ctx.getBean("dataBaseApp");
		dataBaseApp.start();
	}

	private void start() throws IOException {
		List<Book> allBooks = bookDao.loadAll();
		for (Book book : allBooks) {
			System.out.println(book.toString());
			String content = new String(book.getContent());
			System.out.println(content);
		}

		List<Author> allAuthors = authorDao.loadAll();
		for (Author author : allAuthors) {
			System.out.println(author.toString());
		}

		Author author = Author.createEmptyAuthor();
		author.setName("New author");
		authorDao.save(author);

		URL resource = getClass().getResource("/book/Ulysses-James.Joyce.txt");
		File file = new File(resource.getPath());
		String fileContent = FileUtils.readFileToString(file);

		Book book = Book.createBook("title", fileContent);
		bookDao.save(book);
	}
}
