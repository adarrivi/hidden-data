package com.hidden.data.producer;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hidden.data.common.performance.PerformanceLogged;
import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.dao.PatternDao;
import com.hidden.data.db.model.Book;
import com.hidden.data.db.model.Pattern;
import com.hidden.data.queue.connection.ProducerConnection;
import com.hidden.data.queue.connection.QueueConnectionFactory;
import com.hidden.data.queue.model.FilterItem;

@Component
public class BookProducer implements Runnable {

	private static final Logger LOG = Logger.getLogger(BookProducer.class);

	@Autowired
	private BookDao bookDao;
	@Autowired
	private PatternDao patternDao;

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
		LOG.debug("All books produced");
		producerConnection.close();
	}

	private void loadBooksAndSendLines() {
		Collection<String> notProcessedBookTitles = bookDao
				.getNotProcessedBookTitles();
		allPatterns = patternDao.loadAll();
		for (String bookTitle : notProcessedBookTitles) {
			currentBook = bookDao.findByTitle(bookTitle);
			sendLinesByPattern();
			setCurrentBookProcessed();
		}
	}

	private void sendLinesByPattern() {
		bookLines = currentBook.getBookLines();
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
				sendItem(item);
			}
		}
	}

	@PerformanceLogged(identifier = "sendItem")
	private void sendItem(FilterItem item) {
		producerConnection.sendMessage(item);
	}

	private void setCurrentBookProcessed() {
		currentBook.setProcessed(true);
		bookDao.save(currentBook);
		LOG.debug("Book processed: " + currentBook.getTitle());
	}
}
