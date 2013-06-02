package com.hidden.data.queue.model;

import java.io.Serializable;

public class SimplifiedBookRow implements Serializable {

	private static final long serialVersionUID = 1L;

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
