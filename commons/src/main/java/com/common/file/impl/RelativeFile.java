package com.common.file.impl;

import java.io.File;
import java.net.URL;

import com.common.file.FileException;

class RelativeFile {

	private String relativeFilePath;

	RelativeFile(String relativeFilePath) {
		this.relativeFilePath = relativeFilePath;
	}

	File getFile() {
		URL resource = getClass().getResource(relativeFilePath);
		assertFileExists(resource);
		return new File(resource.getPath());
	}

	private void assertFileExists(URL resource) {
		if (resource == null) {
			throw new FileException("File not found");
		}
	}
}
