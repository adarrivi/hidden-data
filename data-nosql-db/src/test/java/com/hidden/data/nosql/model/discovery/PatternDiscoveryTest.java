package com.hidden.data.nosql.model.discovery;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.common.test.AccessorVerifier;

public class PatternDiscoveryTest {

	private static final PatternDiscoveryTest INSTANCE = new PatternDiscoveryTest();
	private static final String PATTERN_NAME = "3x3 middle column";
	private List<List<Character>> LINES;
	private PatternDiscovery victim;

	protected static PatternDiscoveryTest getInstance() {
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

	protected PatternDiscovery createVictim() {
		List<Character> line = Collections.nCopies(5, new Character('a'));
		LINES = Collections.nCopies(5, line);
		victim = new PatternDiscovery(PATTERN_NAME, LINES);
		return victim;
	}
}
