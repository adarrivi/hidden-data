package com.common.file.impl;

import java.io.File;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.common.file.RelativeFile;
import com.common.file.exception.FileException;
import com.common.util.TestCommonsObjectFactory;

public class RelativeFileImplTest {

	private RelativeFile victim;
	private File file;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void getFile_Existing_ReturnsFile() {
		givenExistingFilePath();
		whenGetFile();
		thenReturnsExistingFile();
	}

	private void givenExistingFilePath() {
		victim = new RelativeFileImpl(TestCommonsObjectFactory.FILES_FOLDER
				+ TestCommonsObjectFactory.FILE_NAME);
	}

	private void whenGetFile() {
		file = victim.getFile();
	}

	private void thenReturnsExistingFile() {
		Assert.assertNotNull(file);
		Assert.assertTrue(file.getPath().contains(
				TestCommonsObjectFactory.FILE_NAME));
	}

	@Test
	public void getFile_NoExisting_ThrowsFileEx() {
		expectFileEx();
		givenNoExistingFilePath();
		whenGetFile();
	}

	private void expectFileEx() {
		expectedException.expect(FileException.class);
	}

	private void givenNoExistingFilePath() {
		victim = new RelativeFileImpl(TestCommonsObjectFactory.FILES_FOLDER
				+ TestCommonsObjectFactory.NOT_EXISTING_FILE_NAME);
	}

}
