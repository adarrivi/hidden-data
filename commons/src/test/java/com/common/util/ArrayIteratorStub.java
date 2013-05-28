package com.common.util;

import org.apache.commons.collections.iterators.ArrayIterator;

public class ArrayIteratorStub extends ArrayIterator {

	public static ArrayIteratorStub createEmptyIterator() {
		return new ArrayIteratorStub();
	}

	public ArrayIteratorStub(Object array) {
		super(array);
	}

	private ArrayIteratorStub() {
		super();
	}

	@Override
	public Object next() {
		if (!hasNext()) {
			return null;
		}
		return super.next();
	}
}
