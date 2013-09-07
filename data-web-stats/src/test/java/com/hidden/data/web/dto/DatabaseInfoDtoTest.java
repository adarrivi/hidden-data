package com.hidden.data.web.dto;

import org.junit.Test;

import com.hidden.data.common.test.AccessorVerifier;

public class DatabaseInfoDtoTest {

	private static final int NUMBER_OF_AUTHORS = 5;
	private static final int NUMBER_OF_PATTERNS = 4;
	private static final int NUMBER_OF_BOOKS = 3;
	private DatabaseInfoDto victim;

	private void givenVictim() {
		victim = new DatabaseInfoDto(NUMBER_OF_BOOKS, NUMBER_OF_PATTERNS,
				NUMBER_OF_AUTHORS);
	}

	@Test
	public void verifyDirectGetters() {
		givenVictim();
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addGetterToVerify("getNumberOfBooks", "numberOfBooks",
				NUMBER_OF_BOOKS);
		verifier.addGetterToVerify("getNumberOfPatterns", "numberOfPatterns",
				NUMBER_OF_PATTERNS);
		verifier.addGetterToVerify("getNumberOfAuthors", "numberOfAuthors",
				NUMBER_OF_AUTHORS);
		verifier.verifyDirectGetters();
	}

}
