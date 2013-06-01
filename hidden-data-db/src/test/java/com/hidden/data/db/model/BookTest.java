package com.hidden.data.db.model;

import org.junit.Assert;
import org.junit.Test;

import com.common.reflexion.Reflexion;

public class BookTest {

	private static final String BOOK_CONTENT = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	private static final String BOOK_TITLE = "Lorem ipsum";
	private static final Integer BOOK_ID = Integer.valueOf(1);

	private Book victim;
	private boolean isEmpty;
	private Integer id;
	private String title;
	private String content;

	public static Book createBook() {
		Book book = Book.createEmptyBook();
		Reflexion.getInstance().setMember(book, "id", BOOK_ID);
		book.setTitle(BOOK_TITLE);
		book.setContent(BOOK_CONTENT);
		return book;

	}

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
		victim = createBook();
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

	@Test
	public void getContent_Book_ReturnsContent() {
		givenABook();
		whenGetContent();
		thenContentShouldBeEquals();
	}

	private void whenGetContent() {
		content = victim.getContent();
	}

	private void thenContentShouldBeEquals() {
		Assert.assertEquals(BOOK_CONTENT, content);
	}

}
