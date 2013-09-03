package com.hidden.data.db.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.hidden.data.common.reflection.Reflection;
import com.hidden.data.common.test.AccessorVerifier;
import com.hidden.data.db.model.verifier.NotNulEntityTestable;
import com.hidden.data.db.model.verifier.NotNullEntityVerifier;
import com.hidden.data.db.model.verifier.PersistentEntityTestable;
import com.hidden.data.db.model.verifier.PersistentEntityVerifier;

public class BookTest implements NotNulEntityTestable, PersistentEntityTestable {

	private static final String BOOK_LINE1 = "Lorem ipsum dolor sit amet, ";
	private static final String BOOK_LINE2 = "consectetur adipisicing elit.";
	private static final String BOOK_CONTENT = BOOK_LINE1 + Book.LINE_BREAK
			+ BOOK_LINE2;
	private static final String BOOK_TITLE = "Lorem ipsum";
	private static final Integer BOOK_ID = Integer.valueOf(1);
	private static final Author BOOK_AUTHOR = new Author();
	private AccessorVerifier verifier = new AccessorVerifier(createVictim());
	private Book victim;
	private List<String> bookLines;
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private Book createVictim() {
		Book book = Book.createEmptyBook();
		Reflection.getInstance().setField(book, "id", BOOK_ID);
		book.setTitle(BOOK_TITLE);
		book.setContent(BOOK_CONTENT);
		book.setAuthor(BOOK_AUTHOR);
		return book;

	}

	@Override
	public PersistentEntity givenNewEntity() {
		givenEmptyBook();
		return victim;
	}

	private void givenEmptyBook() {
		victim = Book.createEmptyBook();
	}

	@Override
	public PersistentEntity givenExistingEntity() {
		givenABook();
		return victim;
	}

	private void givenABook() {
		victim = createVictim();
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
		verifier.addGetterToVerify("getId", "id", BOOK_ID);
		verifier.addGetterToVerify("getTitle", "title", BOOK_TITLE);
		verifier.addGetterToVerify("getAuthor", "author", BOOK_AUTHOR);
		verifier.addGetterToVerify("isProcessed", "processed", false);
		verifier.verifyDirectGetters();
	}

	@Test
	public void verifyDirectSetters() {
		verifier.addSetterToVerify("setContent", "content", BOOK_CONTENT);
		verifier.addSetterToVerify("setTitle", "title", BOOK_TITLE);
		verifier.addSetterToVerify("setAuthor", "author", BOOK_AUTHOR);
		verifier.addSetterToVerify("setProcessed", "processed", false);
		verifier.verifyDirectSetters();
	}

	@Test
	public void getBookLines_Empty_ReturnsEmptyCollection() {
		givenEmptyBook();
		whenGetBookLines();
		thenBookLinesShouldBe(Collections.<String> emptyList());
	}

	private void whenGetBookLines() {
		bookLines = victim.getBookLines();
	}

	private void thenBookLinesShouldBe(List<String> expectedLines) {
		Assert.assertEquals(expectedLines, bookLines);
	}

	@Test
	public void getBookLines_Once_ReturnsSplittedContent() {
		givenABook();
		whenGetBookLines();
		thenBookLinesShouldBe(Arrays.asList(BOOK_LINE1, BOOK_LINE2));
	}

	@Test
	public void getBookLines_Twice_ReturnsSameList() {
		givenABook();
		whenGetBookLines();
		thenSecondCallShouldReturnSameList();
	}

	private void thenSecondCallShouldReturnSameList() {
		List<String> firstCallList = bookLines;
		whenGetBookLines();
		Assert.assertTrue(firstCallList.equals(bookLines));
	}

	@Test
	public void getBookLines_ReturnsUnmodifiableList() {
		expectUnsupportedEx();
		givenABook();
		whenGetBookLines();
		whenModifyingBookLinesResult();
	}

	private void expectUnsupportedEx() {
		expectedException.expect(UnsupportedOperationException.class);
	}

	private void whenModifyingBookLinesResult() {
		bookLines.add(StringUtils.EMPTY);
	}

}
