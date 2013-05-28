package com.common.file;

import java.io.File;
import java.net.URL;

public class RelativeFile {

	private String relativeFilePath;

	public static RelativeFile createEmpty() {
		return new RelativeFile(null);
	}

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

	public boolean isEmpty() {
		return relativeFilePath == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((relativeFilePath == null) ? 0 : relativeFilePath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RelativeFile)) {
			return false;
		}
		RelativeFile other = (RelativeFile) obj;
		if (relativeFilePath == null) {
			if (other.relativeFilePath != null) {
				return false;
			}
		}
		return relativeFilePath.equals(other.relativeFilePath);
	}

}
