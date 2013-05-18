package com.hidden.data.producer.book;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

class SpaceBook extends ConcreteBook<String> implements Transformable<BitSet> {

	SpaceBook(int id, String title, Iterator<Line<String>> iterator) {
		super(id, title, iterator);
	}

	@Override
	public List<Line<BitSet>> transform() {
		List<Line<BitSet>> transformedLines = new ArrayList<Line<BitSet>>();
		for (Line<String> line : this) {
			transformedLines.add(transformLine(line));
		}
		return transformedLines;
	}

	private Line<BitSet> transformLine(Line<String> line) {
		Line<BitSet> transformedLine = Line.createEmptyLine();
		transformedLine.setBookId(line.getBookId());
		transformedLine.setRow(line.getRow());
		transformedLine.setRowContent(transformContent(line.getRowContent()));
		return transformedLine;
	}

	private BitSet transformContent(String content) {
		int lineLength = content.length();
		BitSet transformedContent = new BitSet(lineLength);
		for (int i = 0; i < lineLength; i++) {
			if (' ' == content.charAt(i)) {
				transformedContent.set(i);
			}
		}
		return transformedContent;
	}
}
