package com.common.file;

import java.io.File;
import java.net.URL;

public class RelativeFile {

	private String relativeFilePath;

	public RelativeFile(String relativeFilePath) {
		this.relativeFilePath = relativeFilePath;
	}

	public File getFile() {
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
