package com.common.file.impl;

import java.io.BufferedReader;
import java.io.IOException;

import com.common.file.FileException;
import com.common.file.FileLineIterator;

class BufferedReaderIterator implements FileLineIterator {

	private BufferedReader reader;
	private String line;

	BufferedReaderIterator(BufferedReader reader) {
		this.reader = reader;
		iterate();
	}

	@Override
	public boolean hasNext() {
		return line != null;
	}

	@Override
	public String next() {
		String alreadyReadValue = line;
		iterate();
		return alreadyReadValue;
	}

	private void iterate() {
		try {
			line = reader.readLine();
		} catch (IOException e) {
			throw new FileException(e);
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void close() {
		try {
			reader.close();
		} catch (IOException e) {
			throw new FileException(e);
		}
	}

}
