package com.common.iterator;

import java.util.Iterator;

public abstract class IteratorDecorator<K, T> implements Iterator<K> {

	protected Iterator<T> iterator;

	public IteratorDecorator(Iterator<T> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
