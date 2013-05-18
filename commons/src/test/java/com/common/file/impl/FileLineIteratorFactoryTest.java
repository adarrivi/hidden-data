package com.common.file.impl;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.common.TestObjectFactory;
import com.common.file.FileLineIterator;

public class FileLineIteratorFactoryTest {

	private FileLineIteratorFactory victim = FileLineIteratorFactory
			.getInstance();
	private FileLineIterator<String> fileLineIterator;
	private File file;
	private String relativeFilePath;

	@Test
	public void createBufferedFileLineIterator_ExistingFile_ReturnsBufferedReaderIterator() {
		givenExistingRelativeFilePath();
		whenCreateBufferedFileLineIterator();
		thenIteratorShouldBeBufferedReaderIterator();
	}

	private void whenCreateBufferedFileLineIterator() {
		fileLineIterator = victim
				.createBufferedFileLineIterator(relativeFilePath);
	}

	private void thenIteratorShouldBeBufferedReaderIterator() {
		Assert.assertNotNull(fileLineIterator);
		Assert.assertEquals(BufferedReaderIterator.class,
				fileLineIterator.getClass());
	}

	@Test
	public void getFile_Existing_ReturnsFile() {
		givenExistingRelativeFilePath();
		whenGetFile();
		thenFileShouldNotBeNull();
	}

	private void givenExistingRelativeFilePath() {
		relativeFilePath = TestObjectFactory.FILES_FOLDER
				+ TestObjectFactory.FILE_NAME;
	}

	private void whenGetFile() {
		file = victim.getFile(relativeFilePath);
	}

	private void thenFileShouldNotBeNull() {
		Assert.assertNotNull(file);
	}
}
