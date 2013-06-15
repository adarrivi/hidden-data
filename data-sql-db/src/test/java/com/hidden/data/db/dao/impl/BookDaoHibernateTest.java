package com.hidden.data.db.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.model.Book;

public class BookDaoHibernateTest extends InMemoryDaoTest {

	protected static final String NOT_EXISTING_BOOK_TITLE = "notExisting";
	protected static final String EXISTING_BOOK_TITLE = "Youth";

	private String title;
	private Book book;

	@Autowired
	private BookDao victim;

	@Test
	public void findByTitle_NotExisting_ReturnsEmptyBook() {
		givenBookByTitle(NOT_EXISTING_BOOK_TITLE);
		whenFidnByTitle();
		thenBookShouldBeEmpty();
	}

	private void givenBookByTitle(String bookTitle) {
		this.title = bookTitle;
	}

	private void whenFidnByTitle() {
		book = victim.findByTitle(title);
	}

	private void thenBookShouldBeEmpty() {
		Assert.assertTrue(book.isEmpty());
	}

	@Test
	public void findByTitle_Existing_ReturnsSameBookTitle() {
		givenBookByTitle(EXISTING_BOOK_TITLE);
		whenFidnByTitle();
		thenTitleShouldBeTheSame(EXISTING_BOOK_TITLE);
	}

	private void thenTitleShouldBeTheSame(String expectedTitle) {
		Assert.assertEquals(expectedTitle, book.getTitle());
	}

	@Test
	public void findByTitle_Existing_ReturnsNotEmptyBook() {
		givenBookByTitle(EXISTING_BOOK_TITLE);
		whenFidnByTitle();
		thenShouldNotBeEmpty();
	}

	private void thenShouldNotBeEmpty() {
		Assert.assertFalse(book.isEmpty());
	}

}
