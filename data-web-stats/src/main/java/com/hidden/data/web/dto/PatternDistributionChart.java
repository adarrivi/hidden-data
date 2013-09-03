package com.hidden.data.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PatternDistributionChart implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> bookTitles = new ArrayList<String>();
	private List<List<Integer>> patternDistributionsList = new ArrayList<List<Integer>>();

	public void addPatternsDistribution(String bookTitle,
			List<Integer> patternDistribution) {
		bookTitles.add(bookTitle);
		patternDistributionsList.add(patternDistribution);
	}

	public List<String> getBookTitles() {
		return bookTitles;
	}

	public List<List<Integer>> getPatternsDistributionList() {
		return patternDistributionsList;
	}

}
