package com.common.file.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.common.file.RelativeFile;
import com.common.file.exception.FileException;

class RelativeFileReader {

	private RelativeFile relativeFile;

	RelativeFileReader(RelativeFile relativeFile) {
		this.relativeFile = relativeFile;
	}

	FileReader getFileReader() {
		try {
			return new FileReader(relativeFile.getFile());
		} catch (FileNotFoundException e) {
			throw new FileException(e);
		}
	}

}
