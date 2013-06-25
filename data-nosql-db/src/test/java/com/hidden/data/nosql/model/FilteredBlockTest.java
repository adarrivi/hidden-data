package com.hidden.data.nosql.model;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.common.reflection.Reflection;

public class FilteredBlockTest {

	private static final int START_LINE_NUMBER = 3;
	private static final int BOOK_ID = 2;
	private static final int PATTERN_ID = 1;
	private static final String BLOCK_ID = "AD3245";
	private static final List<String> LINES = Collections.nCopies(5,
			"Lorem ipsum dolor");

	private FilteredBlock victim;
	private int intResult;
	private String stringResult;
	private List<String> listResult;

	@Test
	public void getBookId_NewInstance_ReturnsCreationBookId() {
		givenNewInstance();
		whenGetBookId();
		thenBookIdShouldBeAsInCreation();
	}

	private void givenNewInstance() {
		createVictim();
	}

	private void createVictim() {
		victim = new FilteredBlock(PATTERN_ID, BOOK_ID, START_LINE_NUMBER,
				LINES);
		Reflection.getInstance().setField(victim, "id", BLOCK_ID);
	}

	private void whenGetBookId() {
		intResult = victim.getBookId();
	}

	private void thenBookIdShouldBeAsInCreation() {
		Assert.assertEquals(BOOK_ID, intResult);
	}

	@Test
	public void getPatternId_NewInstance_ReturnsCreationPatternId() {
		givenNewInstance();
		whenGetPatternId();
		thenPatternIdShouldBeAsInCreation();
	}

	private void whenGetPatternId() {
		intResult = victim.getPatternId();
	}

	private void thenPatternIdShouldBeAsInCreation() {
		Assert.assertEquals(PATTERN_ID, intResult);
	}

	@Test
	public void getStartLineNumber_NewInstance_ReturnsCreationStartLineNumber() {
		givenNewInstance();
		whenGetStartLineNumber();
		thenStartLineNumberShouldBeAsInCreation();
	}

	private void whenGetStartLineNumber() {
		intResult = victim.getStartLineNumber();
	}

	private void thenStartLineNumberShouldBeAsInCreation() {
		Assert.assertEquals(START_LINE_NUMBER, intResult);
	}

	@Test
	public void getLines_NewInstance_ReturnsCreationLines() {
		givenNewInstance();
		whenGetLines();
		thenLinesShouldBeAsInCreation();
	}

	private void whenGetLines() {
		listResult = victim.getLines();
	}

	private void thenLinesShouldBeAsInCreation() {
		Assert.assertEquals(LINES, listResult);
	}

	@Test
	public void getId_NewInstance_ReturnsCreationId() {
		givenNewInstance();
		whenGetId();
		thenIdShouldBeAsInCreation();
	}

	private void whenGetId() {
		stringResult = victim.getId();
	}

	private void thenIdShouldBeAsInCreation() {
		Assert.assertEquals(stringResult, BLOCK_ID);
	}

}
