package com.common.file.reader;

import java.io.BufferedReader;


class RelativeBufferedReader {

	private RelativeFileReader relativeFileReader;

	RelativeBufferedReader(RelativeFileReader fileReaderProvider) {
		this.relativeFileReader = fileReaderProvider;
	}

	BufferedReader getBufferedReader() {
		return new BufferedReader(relativeFileReader.getFileReader());
	}
}
