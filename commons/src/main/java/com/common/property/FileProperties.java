package com.common.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.common.file.exception.FileException;

class FileProperties {

	private Properties properties;
	private String relativePathFile;
	private InputStream resource;

	FileProperties(String relativePathFile, Properties properties) {
		this.relativePathFile = relativePathFile;
		this.properties = properties;
		loadProperties();
	}

	private void loadProperties() {
		try {
			assertRelativePathFileExists();
			properties.load(resource);
			//TODO CLOSE THE RESOURCE!!!
			//TODO see http://docs.oracle.com/javase/tutorial/essential/environment/properties.html
		} catch (IOException e) {
			throw new FileException(e);
		}
	}

	private void assertRelativePathFileExists() {
		resource = getClass().getResourceAsStream(relativePathFile);
		if (resource == null) {
			throw new FileException("File not found in:" + relativePathFile);
		}

	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
