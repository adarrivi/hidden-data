package com.hidden.data.filter;

import java.util.List;

import com.hidden.data.queue.model.SimplifiedBookRow;

class Pattern {

	private boolean[][] pattern;
	private int patternColumns;

	private boolean[][] trimmedRowsContent;
	private int trimmedRowsContentColumns;
	private List<SimplifiedBookRow> rows;

	public Pattern(boolean[][] pattern) {
		this.pattern = pattern;
	}

	public boolean matches(List<SimplifiedBookRow> rowList) {
		this.rows = rowList;
		if (isEmptyPattern()) {
			return true;
		}
		setTrimmedRowsContent();
		if (isTrimmedContentSmallerThanPatter()) {
			return false;
		}
		for (int columnIndex = 0; columnIndex < trimmedRowsContentColumns
				- patternColumns + 1; columnIndex++) {
			if (patternMatchesFromColumn(columnIndex)) {
				return true;
			}
		}
		return false;
	}

	private void setTrimmedRowsContent() {
		patternColumns = pattern[0].length;
		setTrimmedColumnsLenght();
		trimmedRowsContent = new boolean[rows.size()][trimmedRowsContentColumns];
		for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
			for (int columnIndex = 0; columnIndex < trimmedRowsContentColumns; columnIndex++) {
				trimmedRowsContent[rowIndex][columnIndex] = rows.get(rowIndex)
						.getContent()[columnIndex];
			}
		}
	}

	private void setTrimmedColumnsLenght() {
		int shortest = -1;
		for (SimplifiedBookRow row : rows) {
			int rowLength = row.getContent().length;
			if (shortest < 0 || rowLength < shortest) {
				shortest = rowLength;
			}
		}
		trimmedRowsContentColumns = shortest;
	}

	private boolean isTrimmedContentSmallerThanPatter() {
		return patternColumns > trimmedRowsContentColumns;
	}

	private boolean isEmptyPattern() {
		return pattern.length == 0;
	}

	private boolean patternMatchesFromColumn(int startColumnIndex) {
		for (int rowIndex = 0; rowIndex < trimmedRowsContent.length; rowIndex++) {
			for (int columnIndex = 0; columnIndex < patternColumns; columnIndex++) {
				if (pattern[rowIndex][columnIndex] != trimmedRowsContent[rowIndex][columnIndex
						+ startColumnIndex]) {
					return false;
				}
			}
		}
		return true;
	}
}
