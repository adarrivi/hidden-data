package com.hidden.data.loader;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.common.file.CommonsFileUtils;

public class LibraryTest {

	private Library victim;
	private List<BookFile> books;

	@Mock
	private File booksFolder;
	@Mock
	private File bookFile;

	@Mock
	private CommonsFileUtils commonsFileUtils;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getBooks_1Book_ReturnsBook() {
		givenLibraryWithBooks(1);
		whenGetBooks();
		thenNumberOfBooksFoundSholdBe(1);
	}

	private void givenLibraryWithBooks(int numberOfBooks) {
		Mockito.when(commonsFileUtils.getFilesFromFolder(booksFolder))
				.thenReturn(Collections.nCopies(numberOfBooks, bookFile));
		victim = new Library(booksFolder, commonsFileUtils);
	}

	private void whenGetBooks() {
		books = victim.getBooks();
	}

	private void thenNumberOfBooksFoundSholdBe(int expectedNumberOfBooks) {
		Assert.assertEquals(expectedNumberOfBooks, books.size());
	}

	@Test
	public void getBooks_3Books_Return3Books() {
		givenLibraryWithBooks(3);
		whenGetBooks();
		thenNumberOfBooksFoundSholdBe(3);
	}

}
