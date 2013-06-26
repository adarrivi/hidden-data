package com.hidden.data.db.model;

import org.junit.Test;

import com.common.reflection.Reflection;
import com.common.test.AccessorVerifier;
import com.hidden.data.db.model.verifier.NotNulEntityTestable;
import com.hidden.data.db.model.verifier.NotNullEntityVerifier;
import com.hidden.data.db.model.verifier.PersistentEntityTestable;
import com.hidden.data.db.model.verifier.PersistentEntityVerifier;

public class BookTest implements NotNulEntityTestable, PersistentEntityTestable {

	private static final String BOOK_CONTENT = "Lorem ipsum dolor sit amet.";
	private static final String BOOK_TITLE = "Lorem ipsum";
	private static final Integer BOOK_ID = Integer.valueOf(1);
	private static final Author BOOK_AUTHOR = new Author();

	private Book victim;

	public static Book createBook() {
		Book book = Book.createEmptyBook();
		Reflection.getInstance().setField(book, "id", BOOK_ID);
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

	@Test
	public void verifyDirectGetters() {
		AccessorVerifier verifier = new AccessorVerifier(createBook());
		verifier.addGetterToVerify("getId", "id", BOOK_ID);
		verifier.addGetterToVerify("getTitle", "title", BOOK_TITLE);
		verifier.addGetterToVerify("getContent", "content", BOOK_CONTENT);
		verifier.addGetterToVerify("getAuthor", "author", BOOK_AUTHOR);
		verifier.verifyDirectGetters();
	}

	@Test
	public void verifyDirectSetters() {
		AccessorVerifier verifier = new AccessorVerifier(createBook());
		verifier.addSetterToVerify("setContent", "content", BOOK_CONTENT);
		verifier.addSetterToVerify("setTitle", "title", BOOK_TITLE);
		verifier.addSetterToVerify("setAuthor", "author", BOOK_AUTHOR);
		verifier.verifyDirectSetters();
	}
}
