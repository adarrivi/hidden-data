package com.hidden.data.producer.book;

public class ConcreteLine<T> implements Line<T> {

	private int row;
	private int bookId;
	private T rowContent;

	public ConcreteLine(int row, int bookId, T rowContent) {
		this.row = row;
		this.bookId = bookId;
		this.rowContent = rowContent;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public int getBookId() {
		return bookId;
	}

	@Override
	public T getLineContent() {
		return rowContent;
	}
}
