package com.hidden.data.producer.book;

import com.common.file.reader.FileLineIterator;
import com.common.iterator.IteratorDecorator;

class TextBookIterator extends IteratorDecorator<Line<String>, String> {
	private int rowIndex;
	private int bookId;

	TextBookIterator(int bookId, FileLineIterator<String> fileContentIterator) {
		super(fileContentIterator);
		this.bookId = bookId;
		rowIndex = 0;
	}

	@Override
	public Line<String> next() {
		Line<String> nextLine = Line.createEmptyLine();
		if (getIterator().hasNext()) {
			nextLine.setBookId(bookId);
			nextLine.setRow(rowIndex++);
			nextLine.setRowContent(getIterator().next());
		}
		return nextLine;
	}
}
