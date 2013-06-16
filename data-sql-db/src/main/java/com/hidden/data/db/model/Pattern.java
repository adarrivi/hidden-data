package com.hidden.data.db.model;

import java.util.ArrayList;
import java.util.List;

public class Pattern implements NotNullEntity, PersistentEntity {

	private Integer id;
	private String name;
	private List<PatternRow> rows;
	private List<String> currentLines;
	private int startIndex;

	public static Pattern createEmptyPatter() {
		return new Pattern();
	}

	protected Pattern() {
		// Protected to be used by Hibernate
	}

	@Override
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean isEmpty() {
		return name == null;
	}

	public List<List<PatternItem>> getContent() {
		List<List<PatternItem>> items = new ArrayList<List<PatternItem>>();
		if (rows != null) {
			for (PatternRow row : rows) {
				items.add(row.getContent());
			}
		}
		return items;
	}

	public boolean matches(List<String> lines) {
		currentLines = lines;
		if (isEmpty()) {
			return true;
		}
		if (isDifferentHeight()) {
			return false;
		}
		return matchesCurrentLines();
	}

	private boolean isDifferentHeight() {
		return rows.size() != currentLines.size();
	}

	private boolean matchesCurrentLines() {
		for (startIndex = 0; startIndex < currentLines.get(0).length(); startIndex++) {
			if (matchesForStartIndex()) {
				return true;
			}
		}
		return false;
	}

	private boolean matchesForStartIndex() {
		for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
			if (!rows.get(rowIndex).matches(startIndex,
					currentLines.get(rowIndex))) {
				return false;
			}
		}
		return true;
	}
}
