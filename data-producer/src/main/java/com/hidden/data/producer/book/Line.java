package com.hidden.data.producer.book;

public class Line<T> {

	private int row;
	private int bookId;
	private T rowContent;

	private Line(int row, int bookId, T rowContent) {
		this.row = row;
		this.bookId = bookId;
		this.rowContent = rowContent;
	}

	public static <T> Line<T> createEmptyLine() {
		Line<T> concreteLine = new Line<T>(0, 0, null);
		return concreteLine;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setRowContent(T rowContent) {
		this.rowContent = rowContent;
	}

	public int getRow() {
		return row;
	}

	public int getBookId() {
		return bookId;
	}

	public T getRowContent() {
		return rowContent;
	}

	public boolean isEmpty() {
		return rowContent == null;
	}

}
