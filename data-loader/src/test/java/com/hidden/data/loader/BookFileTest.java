package com.hidden.data.loader;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BookFileTest {

	private BookFile victim;
	private String title;
	private File file;

	@Mock
	private File expectedFile;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getTitle_NoSuffix_DoesNotRemoveAnything() {
		String fileName = "fileName";
		givenBookWithFileName(fileName);
		whenGetTitle();
		thenTitleShouldBe(fileName);
	}

	private void givenBookWithFileName(String fileName) {
		Mockito.when(expectedFile.getName()).thenReturn(fileName);
		victim = new BookFile(expectedFile);
	}

	private void whenGetTitle() {
		title = victim.getTitle();
	}

	private void thenTitleShouldBe(String expectedTitle) {
		Assert.assertEquals(expectedTitle, title);
	}

	@Test
	public void getTitle_OneSuffix_RemovesSuffix() {
		givenBookWithFileName("fileName.txt");
		whenGetTitle();
		thenTitleShouldBe("fileName");
	}

	@Test
	public void getTitle_TwoSuffixes_RemovesLast() {
		givenBookWithFileName("fileName.cfr.txt");
		whenGetTitle();
		thenTitleShouldBe("fileName.cfr");
	}

	@Test
	public void getFile_EqualsInputFile() {
		givenBookWithFileName(StringUtils.EMPTY);
		whenGetFile();
		thenFileIsEqualsExpected();
	}

	private void whenGetFile() {
		file = victim.getFile();
	}

	private void thenFileIsEqualsExpected() {
		Assert.assertEquals(expectedFile, file);
	}

}
