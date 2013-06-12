package com.hidden.data.filter;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.hidden.data.queue.model.SimplifiedBookRow;

public class PatternTest {

	private static final boolean[][] PATTERN_3x3 = { { true, true, false },
			{ true, false, true }, { false, false, false } };
	private static final int ROW_NUMBER = 1;
	private static final Integer BOOK_ID = Integer.valueOf(3);
	private Pattern victim;
	private boolean[][] pattern;
	private boolean matches;
	private List<SimplifiedBookRow> rows;

	@Test
	public void matches_EmptyPattern0_ReturnsTrue() {
		givenPattern(new boolean[0][]);
		whenMatches();
		thenMatchesShouldBe(true);
	}

	@Test
	public void matches_EmptyPattern0x0_ReturnsTrue() {
		givenPattern(new boolean[0][0]);
		whenMatches();
		thenMatchesShouldBe(true);
	}

	private void givenPattern(boolean aPattern[][]) {
		pattern = aPattern;
		createVictim();
	}

	private void createVictim() {
		victim = new Pattern(pattern);
	}

	private void whenMatches() {
		matches = victim.matches(rows);
	}

	private void thenMatchesShouldBe(boolean expectedValue) {
		Assert.assertEquals(expectedValue, matches);
	}

	@Test
	public void matches_MatchesAnd1x1_ReturnsTrue() {
		boolean[][] aPattern = new boolean[][] { { true } };
		givenPattern(aPattern);
		givenMatchingRows(aPattern);
		whenMatches();
		thenMatchesShouldBe(true);
	}

	private void givenMatchingRows(boolean[][] aPattern) {
		rows = createMatchingRows(aPattern);
	}

	private List<SimplifiedBookRow> createMatchingRows(boolean[][] aPattern) {
		List<SimplifiedBookRow> listOfRows = new ArrayList<SimplifiedBookRow>();
		for (boolean contentRow[] : aPattern) {
			listOfRows.add(new SimplifiedBookRow(contentRow, ROW_NUMBER,
					BOOK_ID));
		}
		return listOfRows;
	}

	@Test
	public void matches_MatchesAnd3x3_ReturnsTrue() {
		givenPattern(PATTERN_3x3);
		givenMatchingRows(PATTERN_3x3);
		whenMatches();
		thenMatchesShouldBe(true);
	}

	@Test
	public void matches_MatchesAndAsymmetrical_ReturnsTrue() {
		givenPattern(PATTERN_3x3);
		givenAsymmetricalRows();
		whenMatches();
		thenMatchesShouldBe(true);
	}

	private void givenAsymmetricalRows() {
		boolean[][] aPattern = {
				{ PATTERN_3x3[0][0], PATTERN_3x3[0][1], PATTERN_3x3[0][2],
						false, false, false },
				{ PATTERN_3x3[1][0], PATTERN_3x3[1][1], PATTERN_3x3[1][2] },
				{ PATTERN_3x3[2][0], PATTERN_3x3[2][1], PATTERN_3x3[2][2],
						false, false } };
		List<SimplifiedBookRow> listOfRows = new ArrayList<SimplifiedBookRow>();
		listOfRows.add(new SimplifiedBookRow(aPattern[0], ROW_NUMBER, BOOK_ID));
		listOfRows.add(new SimplifiedBookRow(aPattern[1], ROW_NUMBER, BOOK_ID));
		listOfRows.add(new SimplifiedBookRow(aPattern[2], ROW_NUMBER, BOOK_ID));
		rows = listOfRows;
	}

	@Test
	public void matches_Matches3x3Beginning_ReturnsTrue() {
		givenPattern(PATTERN_3x3);
		boolean[][] aPattern = {
				{ PATTERN_3x3[0][0], PATTERN_3x3[0][1], PATTERN_3x3[0][2],
						false, false, false },
				{ PATTERN_3x3[1][0], PATTERN_3x3[1][1], PATTERN_3x3[1][2] },
				{ PATTERN_3x3[2][0], PATTERN_3x3[2][1], PATTERN_3x3[2][2],
						false, false } };
		givenMatchingRows(aPattern);
		whenMatches();
		thenMatchesShouldBe(true);
	}

	@Test
	public void matches_Matches3x3Between_ReturnsTrue() {
		givenPattern(PATTERN_3x3);
		boolean[][] aPattern = {
				{ false, PATTERN_3x3[0][0], PATTERN_3x3[0][1],
						PATTERN_3x3[0][2], false, false, false },
				{ true, PATTERN_3x3[1][0], PATTERN_3x3[1][1],
						PATTERN_3x3[1][2], true, false },
				{ true, PATTERN_3x3[2][0], PATTERN_3x3[2][1],
						PATTERN_3x3[2][2], false, false } };
		givenMatchingRows(aPattern);
		whenMatches();
		thenMatchesShouldBe(true);
	}

	@Test
	public void matches_Matches3x3End_ReturnsTrue() {
		givenPattern(PATTERN_3x3);
		boolean[][] aPattern = {
				{ false, PATTERN_3x3[0][0], PATTERN_3x3[0][1],
						PATTERN_3x3[0][2] },
				{ true, PATTERN_3x3[1][0], PATTERN_3x3[1][1], PATTERN_3x3[1][2] },
				{ true, PATTERN_3x3[2][0], PATTERN_3x3[2][1], PATTERN_3x3[2][2] } };
		givenMatchingRows(aPattern);
		whenMatches();
		thenMatchesShouldBe(true);
	}

	@Test
	public void matches_NoMatchesPattern3x3Rows2x3_ReturnsFalse() {
		givenPattern(PATTERN_3x3);
		boolean[][] aPattern = {
				{ PATTERN_3x3[0][0], PATTERN_3x3[0][1] },
				{ true, PATTERN_3x3[1][0], PATTERN_3x3[1][1], PATTERN_3x3[1][2] },
				{ true, PATTERN_3x3[2][0], PATTERN_3x3[2][1], PATTERN_3x3[2][2] } };
		givenMatchingRows(aPattern);
		whenMatches();
		thenMatchesShouldBe(false);
	}

	@Test
	public void matches_NoMatches3x3End_ReturnsTrue() {
		givenPattern(PATTERN_3x3);
		boolean[][] aPattern = {
				{ false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false } };
		givenMatchingRows(aPattern);
		whenMatches();
		thenMatchesShouldBe(false);
	}
}
