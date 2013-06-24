package com.hidden.data.nosql.model.discovery;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.common.reflexion.Reflexion;

public class BookDiscoveryTest {

	private static final String BOOK_ID = "A1224BC03";
	private static final String AUTHOR = "James Joyce";
	private static final String BOOK_TITLE = "Ulysses";
	private static final List<Line> LINES = Collections.nCopies(5, LineTest
			.getInstance().createVictim());
	private static final Pattern PATTERN = PatternTest.getInstance()
			.createVictim();

	private BookDiscovery victim;
	private String stringResult;
	private List<Line> listResult;
	private Pattern patternResult;

	@Test
	public void getId_ReturnsCreationId() {
		createVictim();
		whenGetId();
		thenIdShouldBeAsInCreation();
	}

	private void createVictim() {
		victim = new BookDiscovery(BOOK_TITLE, AUTHOR, LINES, PATTERN);
		Reflexion.getInstance().setMember(victim, "id", BOOK_ID);
	}

	private void whenGetId() {
		stringResult = victim.getId();
	}

	private void thenIdShouldBeAsInCreation() {
		Assert.assertEquals(BOOK_ID, stringResult);
	}

	@Test
	public void getBookTitle_ReturnsCreationBookTitle() {
		createVictim();
		whenGetBookTitle();
		thenBookTitleShouldBeAsInCreation();
	}

	private void whenGetBookTitle() {
		stringResult = victim.getBookTitle();
	}

	private void thenBookTitleShouldBeAsInCreation() {
		Assert.assertEquals(BOOK_TITLE, stringResult);
	}

	@Test
	public void getAuthor_ReturnsCreationAuthor() {
		createVictim();
		whenGetAuthor();
		thenAuthorShouldBeAsInCreation();
	}

	private void whenGetAuthor() {
		stringResult = victim.getAuthor();
	}

	private void thenAuthorShouldBeAsInCreation() {
		Assert.assertEquals(AUTHOR, stringResult);
	}

	@Test
	public void getLines_ReturnsCreationLines() {
		createVictim();
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
	public void getPattern_ReturnsCreationPattern() {
		createVictim();
		whenGetPattern();
		thenPatternShouldBeAsInCreation();
	}

	private void whenGetPattern() {
		patternResult = victim.getPattern();
	}

	private void thenPatternShouldBeAsInCreation() {
		Assert.assertEquals(PATTERN, patternResult);
	}
}
