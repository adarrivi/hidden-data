package com.hidden.data.producer.book;

import org.junit.Assert;
import org.junit.Test;

public class LineTest {

	private static final String LINE_CONTENT = "line content";
	private static final int BOOK_ID = 2;
	private static final int ROW = 1;
	private Line<String> victim;
	private boolean isEmptyResult;
	private Line<String> otherLine;
	private boolean equalsResult;

	@Test
	public void isEmpty_EmptyLine_ReturnsTrue() {
		givenEmptyLine();
		whenIsEmpty();
		thenLineShouldBeEmpty();
	}

	private void givenEmptyLine() {
		victim = Line.<String> createEmptyLine();
	}

	private void whenIsEmpty() {
		isEmptyResult = victim.isEmpty();
	}

	private void thenLineShouldBeEmpty() {
		Assert.assertTrue(isEmptyResult);
	}

	private void thenLineShouldNotBeEmpty() {
		Assert.assertFalse(isEmptyResult);
	}

	@Test
	public void isEmpty_NotEmptyLine_ReturnsTrue() {
		givenNotEmptyLine();
		whenIsEmpty();
		thenLineShouldNotBeEmpty();
	}

	private void givenNotEmptyLine() {
		createVictim();
	}

	private void createVictim() {
		victim = Line.<String> createEmptyLine();
		victim.setBookId(BOOK_ID);
		victim.setRow(ROW);
		victim.setRowContent(LINE_CONTENT);
	}

	@Test
	public void equals_Different_ReturnsFalse() {
		givenDifferentItem();
		whenEquals();
		thenShouldBe(false);
	}

	private void givenDifferentItem() {
		otherLine = Line.createEmptyLine();
		createVictim();
	}

	private void whenEquals() {
		equalsResult = victim.equals(otherLine);
	}

	private void thenShouldBe(boolean value) {
		Assert.assertEquals(value, equalsResult);
	}

	@Test
	public void equals_Equals_ReturnsTrue() {
		givenEqualItem();
		whenEquals();
		thenShouldBe(true);
	}

	private void givenEqualItem() {
		otherLine = Line.<String> createEmptyLine();
		otherLine.setBookId(BOOK_ID);
		otherLine.setRow(ROW);
		otherLine.setRowContent(LINE_CONTENT);
		createVictim();
	}
}