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

import com.common.file.CommonsFileUtils;

public class AuthorFolderTest {

	private static final String ANONYMOUS = "Anonymous";
	private AuthorFolder victim;
	private Collection<BookFile> files;
	private String authorName;
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
		Mockito.when(folder.getName()).thenReturn(ANONYMOUS);
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
	public void getAuthorName() {
		givenFolderName();
		whenGetAuthorName();
		thenAuthorNameShouldBeAsExpected();
	}

	private void whenGetAuthorName() {
		authorName = victim.getAuthorName();
	}

	private void thenAuthorNameShouldBeAsExpected() {
		Assert.assertEquals(ANONYMOUS, authorName);
	}

}
