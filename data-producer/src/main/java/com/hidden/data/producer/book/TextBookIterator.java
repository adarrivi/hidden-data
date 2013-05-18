package com.hidden.data.producer.book;

import java.util.Iterator;

import com.common.file.FileLineIterator;

class TextBookIterator implements Iterator<Line<String>> {
	private FileLineIterator<String> fileContentIterator;
	private int rowIndex;
	private int bookId;

	TextBookIterator(int bookId, FileLineIterator<String> fileContentIterator) {
		this.fileContentIterator = fileContentIterator;
		this.bookId = bookId;
		rowIndex = 0;
	}

	@Override
	public boolean hasNext() {
		return fileContentIterator.hasNext();
	}

	@Override
	public Line<String> next() {
		Line<String> nextLine = Line.createEmptyLine();
		if (fileContentIterator.hasNext()) {
			nextLine.setBookId(bookId);
			nextLine.setRow(rowIndex++);
			nextLine.setRowContent(fileContentIterator.next());
		}
		return nextLine;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();

	}

}
