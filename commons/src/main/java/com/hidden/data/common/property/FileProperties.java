package com.hidden.data.common.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.hidden.data.common.file.exception.FileException;

public class FileProperties {

	private Properties properties;
	private InputStream resource;

	FileProperties(InputStream resource, Properties properties) {
		this.properties = properties;
		this.resource = resource;
		loadProperties();
	}

	private void loadProperties() {
		try {
			assertResourceExists();
			properties.load(resource);
			resource.close();
		} catch (IOException e) {
			throw new FileException(e);
		}
	}

	private void assertResourceExists() {
		if (resource == null) {
			throw new FileException("Stream cannot be null");
		}

	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
