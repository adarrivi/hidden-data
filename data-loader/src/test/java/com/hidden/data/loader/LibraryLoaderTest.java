package com.hidden.data.loader;

import java.io.File;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.common.file.CommonsFileUtils;
import com.hidden.data.db.model.Author;
import com.hidden.data.loader.service.AuthorService;
import com.hidden.data.loader.service.BookService;

public class LibraryLoaderTest {

	private static final String AUTHOR = "Isaac Asimov";
	private static final String BOOK_TITLE = "I, robot";

	@Mock
	private BookService bookService;
	@Mock
	private AuthorService authorService;
	@Mock
	private CommonsFileUtils fileUtils;
	@InjectMocks
	private LibraryLoader victim = new LibraryLoader();

	@Mock
	private File bookFile;
	@Mock
	private File libraryFolder;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		setUpBookFile();
	}

	private void setUpBookFile() {
		Mockito.when(bookFile.getName()).thenReturn(
				BOOK_TITLE + ".by." + AUTHOR + ".txt");
	}

	@Test
	public void run_ShouldSaveBook() {
		givenOneBookInLibrary();
		whenRun();
		thenShouldSaveBook();
	}

	private void givenOneBookInLibrary() {
		Mockito.when(fileUtils.getFileFromAbsolutePath(Matchers.anyString()))
				.thenReturn(libraryFolder);
		Mockito.when(fileUtils.getFilesFromFolder(libraryFolder)).thenReturn(
				Collections.singleton(bookFile));
	}

	private void whenRun() {
		victim.run();
	}

	private void thenShouldSaveBook() {
		Mockito.verify(bookService).saveBookIfDoesntExist(
				Matchers.any(BookFile.class), Matchers.any(Author.class));
	}

}
