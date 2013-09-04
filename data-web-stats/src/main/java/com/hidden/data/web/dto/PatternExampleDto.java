package com.hidden.data.web.dto;

import java.util.List;

public class PatternExampleDto {

	private String bookTitle;
	private String author;
	private String patternName;
	private List<String> lines;

	public PatternExampleDto(String bookTitle, String author,
			String patternName, List<String> lines) {
		this.bookTitle = bookTitle;
		this.author = author;
		this.patternName = patternName;
		this.lines = lines;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public String getPatternName() {
		return patternName;
	}

	public List<String> getLines() {
		return lines;
	}
}
