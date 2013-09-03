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

	private BookFile victim;
	@Mock
	private File file;
	private String title;
	private String author;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getTitle_ReturnsTitle() {
		givenFileName("I, robot.by.Isaac Asimov.txt");
		whenGetTitle();
		thenTitleShouldBe("I, robot");
	}

	private void givenFileName(String fileName) {
		Mockito.when(file.getName()).thenReturn(fileName);
		victim = new BookFile(file);
	}

	private void whenGetTitle() {
		title = victim.getTitle();
	}

	private void thenTitleShouldBe(String expectedTilte) {
		Assert.assertEquals(expectedTilte, title);
	}

	@Test
	public void getAuthor_ReturnsAuthor() {
		givenFileName("I, robot.by.Isaac Asimov.txt");
		whenGetAuthor();
		thenAuthorShouldBe("Isaac Asimov");
	}

	private void whenGetAuthor() {
		author = victim.getAuthor();
	}

	private void thenAuthorShouldBe(String expectedAuthor) {
		Assert.assertEquals(expectedAuthor, author);
	}

	@Test
	public void getFile_ReturnsFile() {
		givenFileName("I, robot.by.Isaac Asimov.txt");
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addGetterToVerify("getFile", "file", file);
		verifier.verifyDirectGetters();
	}

	@Test
	public void getAuthor_Twice_ReturnsAuthor() {
		givenFileName("I, robot.by.Isaac Asimov.txt");
		whenGetAuthor();
		whenGetAuthor();
		thenAuthorShouldBe("Isaac Asimov");

	}

}
