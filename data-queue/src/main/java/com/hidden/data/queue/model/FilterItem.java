package com.hidden.data.queue.model;

import java.io.Serializable;
import java.util.List;

public class FilterItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> lines;
	private int firstLineNumber;
	private Integer bookId;
	private Integer patternId;

	public FilterItem(List<String> lines, int firstLineNumber,
			Integer bookId, Integer patternId) {
		this.lines = lines;
		this.firstLineNumber = firstLineNumber;
		this.bookId = bookId;
		this.patternId = patternId;
	}

	public List<String> getLines() {
		return lines;
	}

	public int getFirstLineNumber() {
		return firstLineNumber;
	}

	public Integer getBookId() {
		return bookId;
	}

	public Integer getPatternId() {
		return patternId;
	}
}
