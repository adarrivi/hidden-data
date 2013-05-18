package com.hidden.data.producer;

import java.util.Iterator;

import com.common.file.FileLineIterator;
import com.common.file.impl.FileLineIteratorFactory;
import com.hidden.data.producer.book.Book;
import com.hidden.data.producer.book.BookFactory;
import com.hidden.data.producer.book.Line;

public class LineProducer {

	private static final LineProducer INSTANCE = new LineProducer();

	public static void main(String[] args) {
		INSTANCE.run();
	}

	private LineProducer() {
		// limit scope;
	}

	private void run() {
		FileLineIterator<String> fileLineIterator = FileLineIteratorFactory
				.getInstance().createBufferedFileLineIterator(
						"/book/Ulysses-James.Joyce.txt");
		Iterator<Line<String>> bookIterator = BookFactory.getInstance()
				.createTextBookIterator(1, fileLineIterator);
		Book<String> book = BookFactory.getInstance().createTextBook(1,
				"Ulysses", bookIterator);
		for (Line<String> line : book) {
			printLine(line);
		}
	}

	private void printLine(Line<String> line) {
		StringBuilder sb = new StringBuilder();
		sb.append(line.getBookId()).append(", ").append(line.getRow())
				.append(", ").append(line.getRowContent());
		System.out.println(sb.toString());
	}

}
