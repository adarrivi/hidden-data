package com.hidden.data.loader.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.model.Author;
import com.hidden.data.db.model.Book;
import com.hidden.data.loader.BookFile;
import com.hidden.data.loader.service.BookService;

public class BookServiceImplTest {

	private static final String BOOK_TITLE = "I, robot";

	@Mock
	private BookDao bookDao;
	@Mock
	private BookFile bookFile;

	@InjectMocks
	private BookService victim = new BookServiceImpl();

	private Book book = Book.createEmptyBook();
	private Author author = Author.createEmptyAuthor();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(bookDao.findByTitle(BOOK_TITLE)).thenReturn(book);
		Mockito.when(bookFile.getTitle()).thenReturn(BOOK_TITLE);
	}

	@Test
	public void saveBookIfDoesntExist_BookExist_DontSave() {
		givenBookInDb();
		whenSaveBookIfDoesntExist();
		thenBookShouldntBeSaved();
	}

	private void givenBookInDb() {
		book.setTitle(BOOK_TITLE);
	}

	private void whenSaveBookIfDoesntExist() {
		victim.saveBookIfDoesntExist(bookFile, author);
	}

	private void thenBookShouldntBeSaved() {
		Mockito.verify(bookDao, Mockito.never()).save(Matchers.any(Book.class));
	}

	@Test
	public void saveBookIfDoesntExist_BookDoesntExist_Save() {
		whenSaveBookIfDoesntExist();
		thenBookShouldntBeSaved();
	}

}
