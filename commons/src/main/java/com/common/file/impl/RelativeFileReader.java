package com.common.file.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.common.file.FileException;

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
