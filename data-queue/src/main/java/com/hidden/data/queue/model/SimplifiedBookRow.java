package com.hidden.data.queue.model;

public class SimplifiedBookRow {

	private boolean[] content;
	private int rowNumber;
	private Integer bookId;

	public SimplifiedBookRow(boolean[] content, int rowNumber, Integer bookId) {
		this.content = content;
		this.rowNumber = rowNumber;
		this.bookId = bookId;
	}

	public boolean[] getContent() {
		return content;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public Integer getBookId() {
		return bookId;
	}

}
