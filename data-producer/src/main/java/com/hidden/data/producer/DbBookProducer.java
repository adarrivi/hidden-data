package com.hidden.data.producer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.model.Book;
import com.hidden.data.producer.db.SpaceBookDb;

@Component("dbBookProducer")
public class DbBookProducer {

	@Autowired
	private BookDao bookDao;

	// context will get closed at JVM runtime
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationProducerContext.xml");
		ctx.registerShutdownHook();
		DbBookProducer dataBaseApp = (DbBookProducer) ctx
				.getBean("dbBookProducer");
		dataBaseApp.start();
	}

	private void start() {
		List<Book> allBooks = bookDao.loadAll();
		for (Book book : allBooks) {
			SpaceBookDb dbBook = new SpaceBookDb(book);
			for (boolean[] line : dbBook.getLines()) {
				printLine(line);
			}
		}
	}

	private void printLine(boolean[] line) {
		for (boolean value : line) {
			String toPrint = value ? " " : "X";
			System.out.print(toPrint);
		}
		System.out.println();
	}
}
