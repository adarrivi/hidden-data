package com.hidden.data.db.model;

import org.junit.Assert;
import org.junit.Test;

public class BookTest {

	private static final Integer BOOK_ID = Integer.valueOf(1);
	private static final String BOOK_TITLE = "Dune";
	private Book victim;
	private boolean isEmpty;
	private Integer id;
	private String title;

	@Test
	public void isEmpty_EmptyBook_ReturnsTrue() {
		givenEmptyBook();
		whenIsEmpty();
		thenShouldBeEmpty(true);
	}

	private void givenEmptyBook() {
		victim = Book.createEmptyBook();
	}

	private void whenIsEmpty() {
		isEmpty = victim.isEmpty();
	}

	private void thenShouldBeEmpty(boolean value) {
		Assert.assertEquals(value, isEmpty);
	}

	@Test
	public void isEmpty_NotEmptyBook_ReturnsFalse() {
		givenABook();
		whenIsEmpty();
		thenShouldBeEmpty(false);
	}

	private void givenABook() {
		victim = new Book(BOOK_ID, BOOK_TITLE);
	}

	@Test
	public void getId_Book_ReturnsId() {
		givenABook();
		whenGetId();
		thenBookIdShouldBeGiven();
	}

	private void whenGetId() {
		id = victim.getId();
	}

	private void thenBookIdShouldBeGiven() {
		Assert.assertEquals(BOOK_ID, id);
	}

	@Test
	public void getId_EmptyBook_ReturnsNull() {
		givenEmptyBook();
		whenGetId();
		thenBookIdNullShouldBeGiven();
	}

	private void thenBookIdNullShouldBeGiven() {
		Assert.assertEquals(null, id);
	}

	@Test
	public void getTitle_EmptyBook_ReturnsNull() {
		givenEmptyBook();
		whenGetTitle();
		thenBookTitleNullShouldBeGiven();
	}

	private void whenGetTitle() {
		title = victim.getTitle();
	}

	private void thenBookTitleNullShouldBeGiven() {
		Assert.assertNull(title);
	}

	@Test
	public void getTitle_Book_ReturnsTitle() {
		givenABook();
		whenGetTitle();
		thenBookTitleShouldBeGiven();
	}

	private void thenBookTitleShouldBeGiven() {
		Assert.assertEquals(BOOK_TITLE, title);
	}

}
