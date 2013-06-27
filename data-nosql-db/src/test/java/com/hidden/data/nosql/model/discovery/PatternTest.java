package com.hidden.data.nosql.model.discovery;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.common.test.AccessorVerifier;

public class PatternTest {

	private static final PatternTest INSTANCE = new PatternTest();
	private static final String PATTERN_NAME = "3x3 middle column";
	private List<List<Character>> LINES;
	private Pattern victim;

	protected static PatternTest getInstance() {
		return INSTANCE;
	}

	@Test
	public void verifyDirectGetters() {
		createVictim();
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addGetterToVerify("getName", "name", PATTERN_NAME);
		verifier.addGetterToVerify("getLines", "lines", LINES);
		verifier.verifyDirectGetters();
	}

	protected Pattern createVictim() {
		List<Character> line = Collections.nCopies(5, new Character('a'));
		LINES = Collections.nCopies(5, line);
		victim = new Pattern(PATTERN_NAME, LINES);
		return victim;
	}
}
