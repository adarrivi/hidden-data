package com.hidden.data.nosql.model.discovery;

import java.util.ArrayList;
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
	private PatternDiscovery pattern;
	private int bookTotalLines;
	private double randomizer;

	public BookDiscovery(String bookTitle, String author, List<Line> lines,
			PatternDiscovery pattern, int bookTotalLines) {
		this.bookTitle = bookTitle;
		this.author = author;
		this.lines = lines;
		this.pattern = pattern;
		this.bookTotalLines = bookTotalLines;
		randomizer = Math.random();
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

	public PatternDiscovery getPattern() {
		return pattern;
	}

	public int getBookTotalLines() {
		return bookTotalLines;
	}

	public int getFirstPatternLineNumber() {
		return lines.get(0).getLineNumber();
	}

	public double getRandomizer() {
		return randomizer;
	}

	public List<String> getLinesContent() {
		List<String> linesContent = new ArrayList<String>();
		for (Line line : lines) {
			linesContent.add(line.getContent());
		}
		return linesContent;

	}

}
