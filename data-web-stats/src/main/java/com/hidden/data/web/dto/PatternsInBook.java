package com.hidden.data.web.dto;

import java.util.ArrayList;
import java.util.List;

public class PatternsInBook {

	List<Integer> patternRepetitions = new ArrayList<Integer>();

	public List<Integer> getPatternRepetitions() {
		return patternRepetitions;
	}

	public void addNumberOfPatterns(int numberOfPatterns) {
		patternRepetitions.add(Integer.valueOf(numberOfPatterns));
	}
}
