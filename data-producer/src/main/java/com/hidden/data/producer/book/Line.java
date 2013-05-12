package com.hidden.data.producer.book;

public interface Line<T> {
	int getRow();

	int getBookId();

	T getLineContent();
}
