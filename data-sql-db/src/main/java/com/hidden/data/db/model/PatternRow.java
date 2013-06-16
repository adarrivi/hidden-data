package com.hidden.data.db.model;

import java.util.List;

public class PatternRow implements PersistentEntity {

	private Integer id;
	private List<PatternItem> content;
	private String matchingLine;
	private int startIndex;

	PatternRow() {
		// Used by Hibernate
	}

	List<PatternItem> getContent() {
		return content;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public boolean matches(int indexToStart, String line) {
		startIndex = indexToStart;
		matchingLine = line;
		if (notValidToMatch()) {
			return false;
		}
		return matchesContent();
	}

	private boolean notValidToMatch() {
		int lineLength = matchingLine.length();
		if (startIndex > lineLength
				|| content.size() > (lineLength - startIndex)) {
			return true;
		}
		return false;
	}

	private boolean matchesContent() {
		for (int i = 0; i < content.size(); i++) {
			PatternItem item = content.get(i);
			if (!item.matches(matchingLine.charAt(startIndex + i))) {
				return false;
			}
		}
		return true;
	}
}
