package com.hidden.data.web.dto;

import java.util.ArrayList;
import java.util.List;

public class PatternsPerBookChart {

	private List<String> patternNames = new ArrayList<String>();
	private List<String> bookTitles = new ArrayList<String>();
	private List<List<Integer>> patternRepetitions = new ArrayList<List<Integer>>();

	public List<String> getPatternNames() {
		return patternNames;
	}

	public void setPatternNames(List<String> patternNames) {
		this.patternNames = patternNames;
	}

	public List<String> getBookTitles() {
		return bookTitles;
	}

	public void setBookTitles(List<String> bookTitles) {
		this.bookTitles = bookTitles;
	}

	public List<List<Integer>> getPatternRepetitions() {
		return patternRepetitions;
	}

	public void addPatternRepetitions(List<Integer> repetitions) {
		patternRepetitions.add(repetitions);
	}
}
