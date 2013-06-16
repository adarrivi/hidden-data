package com.hidden.data.db.model;

import org.junit.Assert;
import org.junit.Test;

import com.common.reflexion.Reflexion;
import com.hidden.data.db.model.verifier.NotNulEntityTestable;
import com.hidden.data.db.model.verifier.NotNullEntityVerifier;
import com.hidden.data.db.model.verifier.PersistentEntityTestable;
import com.hidden.data.db.model.verifier.PersistentEntityVerifier;

public class BookTest implements NotNulEntityTestable, PersistentEntityTestable {

	private static final String BOOK_CONTENT = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	private static final String BOOK_TITLE = "Lorem ipsum";
	private static final Integer BOOK_ID = Integer.valueOf(1);
	private static final Author BOOK_AUTHOR = new Author();

	private Book victim;
	private String title;
	private String content;
	private Author author;

	public static Book createBook() {
		Book book = Book.createEmptyBook();
		Reflexion.getInstance().setMember(book, "id", BOOK_ID);
		book.setTitle(BOOK_TITLE);
		book.setContent(BOOK_CONTENT);
		book.setAuthor(BOOK_AUTHOR);
		return book;

	}

	private void givenEmptyBook() {
		victim = Book.createEmptyBook();
	}

	private void givenABook() {
		victim = createBook();
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

	@Test
	public void getAuthor_Book_ReturnsAuthor() {
		givenABook();
		whenGetAuthor();
		thenAutorShouldBeEquals();
	}

	private void whenGetAuthor() {
		author = victim.getAuthor();
	}

	private void thenAutorShouldBeEquals() {
		Assert.assertEquals(BOOK_AUTHOR, author);
	}

	@Override
	public PersistentEntity givenNewEntity() {
		givenEmptyBook();
		return victim;
	}

	@Override
	public PersistentEntity givenExistingEntity() {
		givenABook();
		return victim;
	}

	@Override
	public NotNullEntity givenEmptyEntity() {
		givenEmptyBook();
		return victim;
	}

	@Override
	public NotNullEntity givenNotEmptyEntity() {
		givenABook();
		return victim;
	}

	@Test
	public void verifyPersistentEntity() {
		PersistentEntityVerifier verifier = new PersistentEntityVerifier(this);
		verifier.verify();
	}

	@Test
	public void verifyNotNullEntity() {
		NotNullEntityVerifier verifier = new NotNullEntityVerifier(this);
		verifier.verify();
	}
}
