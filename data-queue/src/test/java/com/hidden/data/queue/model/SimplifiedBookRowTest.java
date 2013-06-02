package com.hidden.data.queue.model;

import org.junit.Assert;
import org.junit.Test;

public class SimplifiedBookRowTest {

	private static final int ROW_NUMBER = 23;
	private static final Integer BOOK_ID = Integer.valueOf(34);
	private static final boolean[] ROW_CONTENT = new boolean[] { true, false,
			true };

	private SimplifiedBookRow victim;
	private boolean[] content;
	private int rowNumber;
	private Integer bookId;

	@Test
	public void getContent_Line_ReturnsConstructorContent() {
		givenLine();
		whenGetContent();
		thenContentShouldBeSameAsInCreation();
	}

	private void givenLine() {
		victim = new SimplifiedBookRow(ROW_CONTENT, ROW_NUMBER, BOOK_ID);
	}

	private void whenGetContent() {
		content = victim.getContent();
	}

	private void thenContentShouldBeSameAsInCreation() {
		Assert.assertEquals(ROW_CONTENT, content);
	}

	@Test
	public void getRowNumber_Line_ReturnConstructorRowNumber() {
		givenLine();
		whenGetRowNumber();
		thenExpectSameRowNumberAsInCreation();
	}

	private void whenGetRowNumber() {
		rowNumber = victim.getRowNumber();
	}

	private void thenExpectSameRowNumberAsInCreation() {
		Assert.assertEquals(ROW_NUMBER, rowNumber);
	}

	@Test
	public void getBookId() {
		givenLine();
		whenGetBookId();
		thenExpectSameBookIdAsInCreation();
	}

	private void whenGetBookId() {
		bookId = victim.getBookId();
	}

	private void thenExpectSameBookIdAsInCreation() {
		Assert.assertEquals(BOOK_ID, bookId);
	}

}
