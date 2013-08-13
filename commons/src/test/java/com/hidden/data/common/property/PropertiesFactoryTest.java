package com.hidden.data.common.property;

import org.junit.Assert;
import org.junit.Test;

import com.hidden.data.common.property.FileProperties;
import com.hidden.data.common.property.PropertiesFactory;

public class PropertiesFactoryTest {

	private PropertiesFactory victim = PropertiesFactory.getInstance();
	private String relativePath;
	private FileProperties fileProperties;

	@Test
	public void getPropertiesFromRelativePath_ReturnsNotNull() {
		givenExistingPath();
		whenGetPropertiesFromRelativePath();
		thenShouldNotReturnNull();
	}

	private void givenExistingPath() {
		relativePath = "/file/Lorem_ipsum_dolor.txt";
	}

	private void whenGetPropertiesFromRelativePath() {
		fileProperties = victim.getPropertiesFromRelativePath(relativePath);
	}

	private void thenShouldNotReturnNull() {
		Assert.assertNotNull(fileProperties);
	}

}
