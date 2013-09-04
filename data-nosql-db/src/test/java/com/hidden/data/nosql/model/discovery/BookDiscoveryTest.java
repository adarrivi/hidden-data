package com.hidden.data.nosql.model.discovery;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.hidden.data.common.reflection.Reflection;
import com.hidden.data.common.test.AccessorVerifier;

public class BookDiscoveryTest {

	private static final String BOOK_ID = "A1224BC03";
	private static final String AUTHOR = "James Joyce";
	private static final String BOOK_TITLE = "Ulysses";
	private static final int BOOK_TOTAL_LINES = 100;
	private static final List<Line> LINES = Collections.nCopies(5, LineTest
			.getInstance().createVictim());
	private static final PatternDiscovery PATTERN = PatternDiscoveryTest
			.getInstance().createVictim();

	private BookDiscovery victim;
	private int firstLineNumber;
	private List<String> linesContent;

	@Test
	public void verifyDirectGetters() {
		givenBookDiscovery();
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addGetterToVerify("getId", "id", BOOK_ID);
		verifier.addGetterToVerify("getBookTitle", "bookTitle", BOOK_TITLE);
		verifier.addGetterToVerify("getAuthor", "author", AUTHOR);
		verifier.addGetterToVerify("getLines", "lines", LINES);
		verifier.addGetterToVerify("getPattern", "pattern", PATTERN);
		verifier.addGetterToVerify("getBookTotalLines", "bookTotalLines",
				BOOK_TOTAL_LINES);
		verifier.addGetterToVerify("getRandomizer", "randomizer", 0d);
		verifier.verifyDirectGetters();
	}

	private void givenBookDiscovery() {
		victim = new BookDiscovery(BOOK_TITLE, AUTHOR, LINES, PATTERN,
				BOOK_TOTAL_LINES);
		Reflection.getInstance().setField(victim, "id", BOOK_ID);
	}

	@Test
	public void getFirstPatternLineNumber_ReturnsFirstLineNumber() {
		givenBookDiscovery();
		whenGetFirstPatternLineNumber();
		thenFirstPatternLineNumberShouldBe(LINES.get(0).getLineNumber());
	}

	private void whenGetFirstPatternLineNumber() {
		firstLineNumber = victim.getFirstPatternLineNumber();
	}

	private void thenFirstPatternLineNumberShouldBe(int expectedValue) {
		Assert.assertEquals(expectedValue, firstLineNumber);
	}

	@Test
	public void getLinesContent_ReturnsLinesContent() {
		givenBookDiscovery();
		whenGetLinesContent();
		thenLinesContentShouldBe((Collections.nCopies(5, LINES.get(0)
				.getContent())));
	}

	private void whenGetLinesContent() {
		linesContent = victim.getLinesContent();
	}

	private void thenLinesContentShouldBe(List<String> expectedLines) {
		Assert.assertEquals(expectedLines, linesContent);
	}
}
