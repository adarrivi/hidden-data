package com.hidden.data.loader.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.db.model.Author;
import com.hidden.data.loader.service.AuthorService;

public class AuthorServiceImplTest {

	private static final String AUTHOR = "Isaac Asimov";

	@Mock
	private AuthorDao authorDao;

	@InjectMocks
	private AuthorService victim = new AuthorServiceImpl();

	private Author author = Author.createEmptyAuthor();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		setUpAuthor();
	}

	private void setUpAuthor() {
		Mockito.when(authorDao.findByName(AUTHOR)).thenReturn(author);
	}

	@Test
	public void createAuthorIfDoesntExist_AuthorDoesntExist_SavesAuthor() {
		whencreateAuthorIfDoesntExist();
		thenAuthorShouldBeSaved();
	}

	private void whencreateAuthorIfDoesntExist() {
		author = victim.createAuthorIfDoesntExist(AUTHOR);
	}

	private void thenAuthorShouldBeSaved() {
		Mockito.verify(authorDao).save(Matchers.any(Author.class));
	}

	@Test
	public void createAuthorIfDoesntExist_AuthoExist_DoesntSaveAuthor() {
		givenAuthorExists();
		whencreateAuthorIfDoesntExist();
		thenAuthorShouldNotBeSaved();
	}

	private void givenAuthorExists() {
		author.setName(AUTHOR);
	}

	private void thenAuthorShouldNotBeSaved() {
		Mockito.verify(authorDao, Mockito.never()).save(
				Matchers.any(Author.class));
	}

}
