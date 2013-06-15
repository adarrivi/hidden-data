package com.hidden.data.producer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.dao.PatternDao;
import com.hidden.data.db.model.Book;
import com.hidden.data.db.model.Pattern;
import com.hidden.data.producer.db.SpaceBookDb;
import com.hidden.data.queue.connection.ProducerConnection;
import com.hidden.data.queue.connection.activemq.ConnectionActiveMqFactory;
import com.hidden.data.queue.model.SimplifiedBookRow;

@Component("dbBookProducer")
public class DbBookProducer {

	private static final int PATTERN_SIZE = 3;

	@Autowired
	private BookDao bookDao;
	@Autowired
	private PatternDao patternDao;

	private ProducerConnection producerConnection;
	private int rowNumber;
	private Integer bookId;
	private Queue<SimplifiedBookRow> rowQueue = new LinkedList<SimplifiedBookRow>();

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
		List<Pattern> patterns = patternDao.loadAll();
		for (Pattern pattern : patterns) {
			System.out.println(pattern.prettyFormat());
		}

		producerConnection = ConnectionActiveMqFactory.getInstance()
				.createFilterProducerConnection();
		List<Book> allBooks = bookDao.loadAll();
		for (Book book : allBooks) {
			rowNumber = 0;
			bookId = book.getId();
			SpaceBookDb dbBook = new SpaceBookDb(book);
			for (boolean[] line : dbBook.getLines()) {
				sendRows(line);
			}
		}
		producerConnection.close();
	}

	private void sendRows(boolean[] line) {
		SimplifiedBookRow row = new SimplifiedBookRow(line, rowNumber++, bookId);
		rowQueue.add(row);
		if (rowQueue.size() > PATTERN_SIZE) {
			rowQueue.remove();
		}
		if (rowQueue.size() == PATTERN_SIZE) {
			List<SimplifiedBookRow> list = new ArrayList<SimplifiedBookRow>();
			list.addAll(rowQueue);
			producerConnection.sendMessage((Serializable) list);
		}
	}

}
