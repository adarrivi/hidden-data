package com.hidden.data.producer.book;

public interface Book<K> extends Iterable<Line<K>> {

	int getId();

	String getTitle();
}
