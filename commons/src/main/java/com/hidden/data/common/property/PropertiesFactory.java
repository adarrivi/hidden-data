package com.hidden.data.common.property;

import java.util.Properties;

public class PropertiesFactory {

	private static final PropertiesFactory INSTANCE = new PropertiesFactory();

	public static PropertiesFactory getInstance() {
		return INSTANCE;
	}

	private PropertiesFactory() {
		// limiting scope
	}

	public FileProperties getPropertiesFromRelativePath(String relativePath) {
		return new FileProperties(getClass().getResourceAsStream(relativePath),
				new Properties());
	}
}
