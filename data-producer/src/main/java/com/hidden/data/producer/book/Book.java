package com.hidden.data.producer.book;

import java.util.List;

public interface Book<K> {
	List<Line<K>> readNextLines(int numberOfLines);

	int getId();

	String getTitle();
}
