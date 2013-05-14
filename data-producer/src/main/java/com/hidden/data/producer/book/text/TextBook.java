package com.hidden.data.producer.book.text;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import com.hidden.data.producer.book.ConcreteBook;
import com.hidden.data.producer.book.ConcreteLine;
import com.hidden.data.producer.book.Line;
import com.hidden.data.producer.book.Transformable;

public class TextBook extends ConcreteBook<String> implements
		Transformable<BitSet> {
	private static final char SPACE_CHAR = ' ';
	private static final int TRANSFORM_BUFFER_SIZE = 50;
	private TextFile bookContent;

	public TextBook(int id, String title, TextFile bookContent) {
		super(id, title);
		this.bookContent = bookContent;
	}

	@Override
	public List<Line<String>> readNextLines(int numberOfLines) {
		boolean readAllLines = false;
		if (numberOfLines < 0) {
			readAllLines = true;
		}
		List<Line<String>> lines = new ArrayList<Line<String>>();
		int rowIndex = 0;
		for (String lineContent = bookContent.readLine(); lineContent != null
				&& (readAllLines || rowIndex < numberOfLines); lineContent = bookContent
				.readLine()) {
			lines.add(new ConcreteLine<String>(rowIndex++, getId(), lineContent));
		}
		return lines;
	}

	@Override
	public List<Line<BitSet>> transformBook() {
		List<Line<BitSet>> transformedLines = new ArrayList<Line<BitSet>>();
		List<Line<String>> lines = readNextLines(TRANSFORM_BUFFER_SIZE);
		while (!lines.isEmpty()) {
			transformedLines.addAll(transformAllLines(lines));
			lines = readNextLines(TRANSFORM_BUFFER_SIZE);
		}
		return transformedLines;
	}

	private List<Line<BitSet>> transformAllLines(List<Line<String>> lines) {
		List<Line<BitSet>> transformeddLines = new ArrayList<Line<BitSet>>();
		for (Line<String> line : lines) {
			transformeddLines.add(transformStringLine(line));
		}
		return transformeddLines;
	}

	private Line<BitSet> transformStringLine(Line<String> line) {
		String content = line.getLineContent();
		BitSet transformedContent = new BitSet(content.length());
		for (int i = 0; i < content.length(); i++) {
			if (Character.valueOf(SPACE_CHAR) == content.charAt(i)) {
				transformedContent.set(i);
			}
		}
		return new ConcreteLine<BitSet>(line.getRow(), line.getBookId(),
				transformedContent);
	}
}
