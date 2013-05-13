package com.hidden.data.producer.book.text;

import com.hidden.data.producer.file.FileBufferReader;

public class TextFile {

	private FileBufferReader reader;

	public TextFile(FileBufferReader reader) {
		this.reader = reader;
	}

	public String readLine() {
		return reader.readLine();
	}

	public void closeFile() {
		reader.close();
	}

}
