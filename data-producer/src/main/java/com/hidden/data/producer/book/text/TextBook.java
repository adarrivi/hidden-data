package com.hidden.data.producer.book.text;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

import com.hidden.data.producer.book.ConcreteBook;
import com.hidden.data.producer.book.ConcreteLine;
import com.hidden.data.producer.book.Line;
import com.hidden.data.producer.book.Transformable;

public class TextBook extends ConcreteBook<String> implements
		Transformable<BitSet> {

	private TextFile bookContent;

	public TextBook(int id, String title, TextFile bookContent) {
		super(id, title);
		this.bookContent = bookContent;
		this.bookContent.openFile();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Line<String>> readNextLines(int numberOfLines) {
		if (numberOfLines <= 0) {
			return Collections.EMPTY_LIST;
		}
		List<Line<String>> lines = new ArrayList<Line<String>>();
		int rowIndex = 0;
		String lineConent = bookContent.readLine();
		lines.add(new ConcreteLine<String>(rowIndex++, getId(), lineConent));
		while (rowIndex < numberOfLines && lineConent != null) {
			lineConent = bookContent.readLine();
			lines.add(new ConcreteLine<String>(rowIndex++, getId(), lineConent));
		}
		return lines;
	}

	@Override
	public List<Line<BitSet>> transformBook() {
		// TODO Auto-generated method stub
		return null;
	}

}
