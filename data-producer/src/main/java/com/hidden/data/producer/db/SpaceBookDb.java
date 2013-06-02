package com.hidden.data.producer.db;

import java.util.ArrayList;
import java.util.List;

import com.hidden.data.db.model.Book;
import com.hidden.data.producer.SpaceBook;

public class SpaceBookDb implements SpaceBook {

	private static final char SPACE_CHAR = ' ';

	protected static final String LINE_BREAK = System
			.getProperty("line.separator");

	private Book book;
	private List<boolean[]> transformedLines;

	public SpaceBookDb(Book book) {
		this.book = book;
	}

	@Override
	public List<boolean[]> getLines() {
		if (transformedLines == null) {
			setTransformedLines();
		}
		return transformedLines;
	}

	private void setTransformedLines() {
		transformedLines = new ArrayList<boolean[]>();
		String lines[] = book.getContent().split(LINE_BREAK);
		for (String line : lines) {
			transformedLines.add(transformLine(line));
		}
	}

	private boolean[] transformLine(String line) {
		int lineLength = line.length();
		boolean[] transformedContent = new boolean[lineLength];
		for (int i = 0; i < lineLength; i++) {
			transformedContent[i] = SPACE_CHAR == line.charAt(i);
		}
		return transformedContent;
	}
}
