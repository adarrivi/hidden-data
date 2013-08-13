package com.hidden.data.loader;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.common.file.CommonsFileUtils;
import com.hidden.data.common.test.AccessorVerifier;

public class AuthorFolderTest {

	private static final String AUTHOR = "Anonymous";
	private AuthorFolder victim;
	private Collection<BookFile> files;
	@Mock
	private File expectedFile;
	@Mock
	private File folder;
	@Mock
	private CommonsFileUtils commonsFileUtils;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getFiles_ReturnsFilesFromCommonsFileUtils() {
		givenOneFileFolder();
		whenGetFiles();
		thenOneFileFoundFromCommonsFileUtils();
	}

	private void givenOneFileFolder() {
		givenFolderName();
		Mockito.when(commonsFileUtils.getFilesFromFolder(folder)).thenReturn(
				Collections.singleton(expectedFile));
		Mockito.when(expectedFile.getName()).thenReturn("book Title");
	}

	private void givenFolderName() {
		Mockito.when(folder.getName()).thenReturn(AUTHOR);
		createVictim();
	}

	private void whenGetFiles() {
		files = victim.getBookFiles();
	}

	private void createVictim() {
		victim = new AuthorFolder(folder, commonsFileUtils);
	}

	private void thenOneFileFoundFromCommonsFileUtils() {
		Mockito.verify(commonsFileUtils).getFilesFromFolder(folder);
		Assert.assertEquals(1, files.size());
		Assert.assertEquals(expectedFile.getName(), files.iterator().next()
				.getTitle());
	}

	@Test
	public void verifyDirectGetters() {
		createVictim();
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addGetterToVerify("getAuthorName", "authorName", AUTHOR);
		verifier.verifyDirectGetters();
	}
}
