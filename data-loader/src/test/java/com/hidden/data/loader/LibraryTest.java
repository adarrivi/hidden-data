package com.hidden.data.loader;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.common.file.CommonsFileUtils;

public class LibraryTest {

	private static final String AUTHOR_NAME = "authorName";
	private Library victim;
	private File folder;
	private Collection<AuthorFolder> authors;

	@Mock
	private File authorFileFolder;

	@Mock
	private CommonsFileUtils commonsFileUtils;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAuthors_ReturnsFilesFromCommonsFileUtils() {
		givenBook();
		whenGetAuthors();
		thenOneFolderFoundFromCommonsFileUtils();
	}

	private void givenBook() {
		Mockito.when(commonsFileUtils.getSubFolders(folder)).thenReturn(
				Collections.singleton(authorFileFolder));
		Mockito.when(authorFileFolder.getName()).thenReturn(AUTHOR_NAME);
		victim = new Library(folder, commonsFileUtils);
	}

	private void whenGetAuthors() {
		authors = victim.getAuthors();
	}

	private void thenOneFolderFoundFromCommonsFileUtils() {
		Assert.assertEquals(1, authors.size());
		AuthorFolder authorFolder = authors.iterator().next();
		Assert.assertEquals(AUTHOR_NAME, authorFolder.getAuthorName());
		Mockito.verify(commonsFileUtils).getSubFolders(folder);
	}

}
