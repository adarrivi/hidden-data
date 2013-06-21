package com.hidden.data.nosql.model.discovery;

import java.util.List;

public class Pattern {

	private String name;
	private List<List<Character>> lines;

	public Pattern(String name, List<List<Character>> lines) {
		super();
		this.name = name;
		this.lines = lines;
	}

	public String getName() {
		return name;
	}

	public List<List<Character>> getLines() {
		return lines;
	}

}
