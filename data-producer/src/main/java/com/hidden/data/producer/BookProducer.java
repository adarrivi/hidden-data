package com.hidden.data.producer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.dao.PatternDao;
import com.hidden.data.db.model.Book;
import com.hidden.data.db.model.Pattern;
import com.hidden.data.queue.connection.ProducerConnection;
import com.hidden.data.queue.connection.QueueConnectionFactory;
import com.hidden.data.queue.model.FilterItem;

@Component
public class BookProducer implements Runnable {

	protected static final String LINE_BREAK = System
			.getProperty("line.separator");
	@Autowired
	private BookDao bookDao;
	@Autowired
	PatternDao patternDao;

	private ProducerConnection producerConnection;
	private QueueConnectionFactory connectionFactory;

	private Book currentBook;
	private Pattern currentPattern;
	private List<String> bookLines;
	private List<Pattern> allPatterns;

	public void setConnectionFactory(QueueConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public void run() {
		producerConnection = connectionFactory.createFilterProducerConnection();
		loadBooksAndSendLines();
		producerConnection.close();
	}

	private void loadBooksAndSendLines() {
		// TODO Don't load all the books at the same time! Go one by one
		List<Book> allBooks = bookDao.loadAll();
		allPatterns = patternDao.loadAll();
		for (Book book : allBooks) {
			currentBook = book;
			sendLinesByPattern();
		}
	}

	private void sendLinesByPattern() {
		bookLines = Arrays.asList(currentBook.getContent().split(LINE_BREAK));
		for (Pattern pattern : allPatterns) {
			currentPattern = pattern;
			produceMessage();
		}
	}

	private void produceMessage() {
		int numberOfLines = bookLines.size();
		int patternHeigth = currentPattern.getRowsNumber();
		for (int i = 0; i < numberOfLines; i++) {
			if ((numberOfLines - i) >= patternHeigth) {
				int firstBookLineNumber = i + 1;
				int bookLinesStartIdx = i;
				int bookLinesEndIdx = i + patternHeigth;
				FilterItem item = new FilterItem(bookLines.subList(
						bookLinesStartIdx, bookLinesEndIdx),
						firstBookLineNumber, currentBook.getId(),
						currentPattern.getId());
				producerConnection.sendMessage(item);
			}
		}
	}
}
