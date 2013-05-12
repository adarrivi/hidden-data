package com.hidden.data.producer.book.text;

import java.util.Collections;
import java.util.List;

import com.hidden.data.producer.book.Line;
import com.hidden.data.producer.book.TransformableBook;

public class TextBook implements TransformableBook<String> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Line<String>> readNextLines(int numberOfLines) {
		if (numberOfLines == 0) {
			return Collections.EMPTY_LIST;
		}
		return null;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Line<String>> transformBook() {
		// TODO Auto-generated method stub
		return null;
	}

}
