package com.hidden.data.loader;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.common.test.AccessorVerifier;

public class BookFileTest {

	private static final String TWO_SUFFIXES = "fileName.cfr.txt";
	private static final String ONE_SUFFIX = "fileName.txt";
	private static final String FILE_NAME = "fileName";
	private BookFile victim;
	private String title;

	@Mock
	private File expectedFile;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getTitle_NoSuffix_DoesNotRemoveAnything() {
		String fileName = FILE_NAME;
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
		givenBookWithFileName(ONE_SUFFIX);
		whenGetTitle();
		thenTitleShouldBe(FILE_NAME);
	}

	@Test
	public void getTitle_TwoSuffixes_RemovesLast() {
		givenBookWithFileName(TWO_SUFFIXES);
		whenGetTitle();
		thenTitleShouldBe("fileName.cfr");
	}

	@Test
	public void verifyDirectGetters() {
		givenBookWithFileName(FILE_NAME);
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addGetterToVerify("getFile", "file", expectedFile);
		verifier.addGetterToVerify("getTitle", "title", ONE_SUFFIX);
		verifier.verifyDirectGetters();
	}

}
