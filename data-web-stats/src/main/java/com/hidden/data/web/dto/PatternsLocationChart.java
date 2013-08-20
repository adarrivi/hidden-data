package com.hidden.data.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PatternsLocationChart implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> bookTitles = new ArrayList<String>();
	private List<List<Integer>> patternsLocationList = new ArrayList<List<Integer>>();

	public void addPatternsLocation(String bookTitle,
			List<Integer> patternsLocation) {
		bookTitles.add(bookTitle);
		patternsLocationList.add(patternsLocation);
	}

	public List<String> getBookTitles() {
		return bookTitles;
	}

	public List<List<Integer>> getPatternsLocationList() {
		return patternsLocationList;
	}

}
