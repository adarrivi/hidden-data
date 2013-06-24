package com.hidden.data.nosql.model.discovery;

import org.junit.Assert;
import org.junit.Test;

public class LineTest {

	private static final int LINE_NUMBER = 1;
	private static final String CONTENT = "Lorem ipsum dolor";
	private Line victim;
	private int intResult;
	private String stringResult;

	private static final LineTest INSTANCE = new LineTest();

	protected static LineTest getInstance() {
		return INSTANCE;
	}

	@Test
	public void getLineNumber_ReturnsCreationLineNumber() {
		createVictim();
		whenGetLineNumber();
		thenLineNumberShouldBeAsInCreation();
	}

	protected Line createVictim() {
		victim = new Line(LINE_NUMBER, CONTENT);
		return victim;
	}

	private void whenGetLineNumber() {
		intResult = victim.getLineNumber();
	}

	private void thenLineNumberShouldBeAsInCreation() {
		Assert.assertEquals(LINE_NUMBER, intResult);
	}

	@Test
	public void getContent_ReturnsCreationContent() {
		createVictim();
		whenGetContent();
		thenContentShouldBeAsInCreation();
	}

	private void whenGetContent() {
		stringResult = victim.getContent();
	}

	private void thenContentShouldBeAsInCreation() {
		Assert.assertEquals(CONTENT, stringResult);
	}

}
