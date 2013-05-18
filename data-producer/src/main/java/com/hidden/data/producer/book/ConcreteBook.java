package com.hidden.data.producer.book;

import java.util.Iterator;

class ConcreteBook<K> implements Book<K> {

	private int id;
	private String title;
	private Iterator<Line<K>> iterator;

	ConcreteBook(int id, String title, Iterator<Line<K>> iterator) {
		this.id = id;
		this.title = title;
		this.iterator = iterator;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public final Iterator<Line<K>> iterator() {
		return iterator;
	}

}
