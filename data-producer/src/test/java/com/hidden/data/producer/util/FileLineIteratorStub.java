package com.hidden.data.producer.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.common.file.FileLineIterator;

class FileLineIteratorStub implements FileLineIterator<String> {

	private Iterator<String> iterator;

	FileLineIteratorStub(String[] fileContent) {
		List<String> lines = Arrays.asList(fileContent);
		iterator = lines.iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public String next() {
		return iterator.next();
	}

	@Override
	public void remove() {
		iterator.remove();

	}

	@Override
	public void close() {
		// Nothing to do
	}

}
