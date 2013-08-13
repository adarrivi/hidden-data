package com.hidden.data.nosql.model.discovery;

import org.junit.Test;

import com.hidden.data.common.test.AccessorVerifier;

public class LineTest {

	private static final int LINE_NUMBER = 1;
	private static final String CONTENT = "Lorem ipsum dolor";
	private Line victim;

	private static final LineTest INSTANCE = new LineTest();

	protected static LineTest getInstance() {
		return INSTANCE;
	}

	@Test
	public void verifyDirectGetters() {
		createVictim();
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addGetterToVerify("getLineNumber", "lineNumber", LINE_NUMBER);
		verifier.addGetterToVerify("getContent", "content", CONTENT);
		verifier.verifyDirectGetters();
	}

	protected Line createVictim() {
		victim = new Line(LINE_NUMBER, CONTENT);
		return victim;
	}
}
