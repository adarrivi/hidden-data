package com.hidden.data.nosql.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "filtered")
public class FilteredBlock {

	@Id
	private String id;

	private int patternId;
	private int bookId;
	private int startLineNumber;
	private List<String> lines = new ArrayList<String>();

	public FilteredBlock(int patternId, int bookId, int startLineNumber,
			List<String> lines) {
		super();
		this.patternId = patternId;
		this.bookId = bookId;
		this.startLineNumber = startLineNumber;
		this.lines = lines;
	}

	public String getId() {
		return id;
	}

	public int getPatternId() {
		return patternId;
	}

	public int getBookId() {
		return bookId;
	}

	public int getStartLineNumber() {
		return startLineNumber;
	}

	public List<String> getLines() {
		return lines;
	}

}
