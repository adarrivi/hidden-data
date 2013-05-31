package com.common.file.impl;

import java.io.File;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

import com.common.file.RelativeFile;
import com.common.file.exception.FileException;

class RelativeFileImpl implements RelativeFile {

	private String relativeFilePath;

	static RelativeFileImpl createEmpty() {
		return new RelativeFileImpl(StringUtils.EMPTY);
	}

	RelativeFileImpl(String relativeFilePath) {
		this.relativeFilePath = relativeFilePath;
	}

	@Override
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

	@Override
	public boolean isEmpty() {
		return StringUtils.isEmpty(relativeFilePath);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + relativeFilePath.hashCode();
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
		if (!(obj instanceof RelativeFileImpl)) {
			return false;
		}
		RelativeFileImpl other = (RelativeFileImpl) obj;
		return relativeFilePath.equals(other.relativeFilePath);
	}

}
