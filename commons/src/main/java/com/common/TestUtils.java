package com.common;

import java.io.File;
import java.net.URL;

public class TestUtils {

	private static final TestUtils INSTANCE = new TestUtils();

	private TestUtils() {
		// Limit scope
	}

	public static TestUtils getInstance() {
		return INSTANCE;
	}

	public File getFile(String path) {
		URL resource = TestUtils.class.getResource(path);
		if (resource == null) {
			return null;
		}
		return new File(resource.getPath());
	}
}
