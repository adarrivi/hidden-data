package com.hidden.data.nosql.model.discovery;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "discovery.book")
public class BookDiscovery {

	@Id
	private String id;
	private String bookTitle;
	private String author;
	private List<Line> lines;
	private Pattern pattern;

	public BookDiscovery(String bookTitle, String author, List<Line> lines,
			Pattern pattern) {
		super();
		this.bookTitle = bookTitle;
		this.author = author;
		this.lines = lines;
		this.pattern = pattern;
	}

	public String getId() {
		return id;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public List<Line> getLines() {
		return lines;
	}

	public Pattern getPattern() {
		return pattern;
	}

}
