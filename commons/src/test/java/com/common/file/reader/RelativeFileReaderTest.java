package com.common.file.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.common.file.RelativeFile;
import com.common.file.exception.FileException;
import com.common.util.TestCommonsObjectFactory;

public class RelativeFileReaderTest {

	@Mock
	private RelativeFile relativeFile;
	private RelativeFileReader victim;
	private FileReader fileReader;
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws IOException {
		if (fileReader != null) {
			fileReader.close();
		}
	}

	@Test
	public void getFileReader_FileNotFoundEx_ThrowsFileEx() {
		expectFileExWrappingFileNotFoundEx();
		givenEmptyFile();
		whenGetReader();
	}

	private void expectFileExWrappingFileNotFoundEx() {
		expectedException.expect(FileException.class);
		expectedException.expectMessage(FileNotFoundException.class
				.getCanonicalName());
	}

	private void givenEmptyFile() {
		Mockito.when(relativeFile.getFile()).thenReturn(
				new File(StringUtils.EMPTY));
		victim = new RelativeFileReader(relativeFile);
	}

	private void whenGetReader() {
		fileReader = victim.getFileReader();
	}

	@Test
	public void getFileReader_ExistingFile_ReturnsReader() {
		givenExistingFile();
		whenGetReader();
		thenReaderContainsFile();
	}

	private void givenExistingFile() {
		File existingFile = TestCommonsObjectFactory.getInstance()
				.getExistingFile();
		Mockito.when(relativeFile.getFile()).thenReturn(existingFile);
		victim = new RelativeFileReader(relativeFile);
	}

	private void thenReaderContainsFile() {
		Assert.assertNotNull(fileReader);
		Mockito.verify(relativeFile).getFile();
	}

}
