package com.hidden.data.nosql.model.discovery;

import java.util.List;

public class PatternDiscovery {

	private String name;
	private List<List<Character>> lines;

	public PatternDiscovery(String name, List<List<Character>> lines) {
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
