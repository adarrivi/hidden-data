package com.hidden.data.loader;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.loader.service.BookService;

public class LibraryLoaderTest {

	@Mock
	private BookService bookService;
	@Mock
	private AuthorDao authorDao;
	@InjectMocks
	private LibraryLoader victim = new LibraryLoader();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void run_() {

	}

}
