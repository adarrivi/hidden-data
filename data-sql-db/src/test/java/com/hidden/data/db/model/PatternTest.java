package com.hidden.data.db.model;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.common.model.NotNullEntity;
import com.hidden.data.common.reflection.Reflection;
import com.hidden.data.db.model.verifier.NotNulEntityTestable;
import com.hidden.data.db.model.verifier.NotNullEntityVerifier;
import com.hidden.data.db.model.verifier.PersistentEntityTestable;
import com.hidden.data.db.model.verifier.PersistentEntityVerifier;

public class PatternTest implements NotNulEntityTestable,
		PersistentEntityTestable {

	private static final String MATCHING_STRING = "XXXXX";
	private static final Integer PATTERN_ID = Integer.valueOf(1);
	private static final String PATTERN_NAME = "3x3 Column in middle";
	private Pattern victim;
	private String name;
	private List<List<PatternItem>> content;
	private boolean matches;
	private int rowsNumber;

	@Mock
	private PatternRow alwaysMatchingRow;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getName_ReturnsCreationName() {
		givenPattern();
		whenGetName();
		thenNameShoudlBeUsedInCreation();
	}

	private void givenPattern() {
		givenEmptyPattern();
		Reflection.getInstance().setField(victim, "id", PATTERN_ID);
		Reflection.getInstance().setField(victim, "name", PATTERN_NAME);
	}

	private void givenEmptyPattern() {
		victim = Pattern.createEmptyPattern();
	}

	private void whenGetName() {
		name = victim.getName();
	}

	private void thenNameShoudlBeUsedInCreation() {
		Assert.assertEquals(PATTERN_NAME, name);
	}

	@Test
	public void getContent_Empty_ReturnsEmptyList() {
		givenEmptyPattern();
		whenGetContent();
		thenContentShouldBeEmpty();
	}

	private void whenGetContent() {
		content = victim.getContent();
	}

	private void thenContentShouldBeEmpty() {
		Assert.assertTrue(content.isEmpty());
	}

	@Test
	public void getContent_NotEmpty_ReturnsNotEmptyList() {
		givenPattern();
		whenGetContent();
		thenContentShouldBeEmpty();
	}

	@Test
	public void getContent_1x1_Returns1x1() {
		givenPatternWith(1, 1);
		whenGetContent();
		thenContentShouldHave(1, 1);
	}

	@Test
	public void getContent_3x5_Returns3x5() {
		givenPatternWith(3, 5);
		whenGetContent();
		thenContentShouldHave(3, 5);
	}

	private void givenPatternWith(int rows, int columns) {
		givenEmptyPattern();
		PersistentEntity patternRow = PatternRowTest.getInstance()
				.givenExistingEntity();
		List<PatternItem> rowContent = Collections.nCopies(columns,
				PatternItem.createEmptyItem());
		Reflection.getInstance().setField(patternRow, "content", rowContent);
		List<PersistentEntity> rowList = Collections.nCopies(rows, patternRow);
		Reflection.getInstance().setField(victim, "rows", rowList);
	}

	private void thenContentShouldHave(int rows, int columns) {
		Assert.assertEquals(rows, content.size());
		for (List<PatternItem> row : content) {
			Assert.assertEquals(columns, row.size());
		}
	}

	@Override
	public PersistentEntity givenNewEntity() {
		givenEmptyPattern();
		return victim;
	}

	@Override
	public PersistentEntity givenExistingEntity() {
		givenPattern();
		return victim;
	}

	@Override
	public NotNullEntity givenEmptyEntity() {
		givenEmptyPattern();
		return victim;

	}

	@Override
	public NotNullEntity givenNotEmptyEntity() {
		givenPattern();
		return victim;

	}

	@Test
	public void verifyPersistentEntity() {
		PersistentEntityVerifier verifier = new PersistentEntityVerifier(this);
		verifier.verify();
	}

	@Test
	public void verifyNotNullEntity() {
		NotNullEntityVerifier verifier = new NotNullEntityVerifier(this);
		verifier.verify();
	}

	@Test
	public void matches_EmptyPatterEmptyLines_ReturnsTrue() {
		givenEmptyPattern();
		whenMatches(Collections.<String> emptyList());
		thenMatchesShouldBe(true);
	}

	private void whenMatches(List<String> lines) {
		matches = victim.matches(lines);
	}

	private void thenMatchesShouldBe(boolean expectedValue) {
		Assert.assertEquals(expectedValue, matches);
	}

	@Test
	public void matches_3xPatter2xLines_ReturnsFalse() {
		givenAlwaysMatchingPattern(3);
		whenMatches(Collections.nCopies(2, StringUtils.EMPTY));
		thenMatchesShouldBe(false);
	}

	private void givenAlwaysMatchingPattern(int numberOfRows) {
		givenPattern(numberOfRows, true);
	}

	private void givenPattern(int numberOfRows, boolean matchesAll) {
		givenPattern();
		Mockito.when(
				alwaysMatchingRow.matches(Matchers.anyInt(),
						Matchers.anyString())).thenReturn(matchesAll);
		List<PatternRow> patternRows = Collections.nCopies(numberOfRows,
				alwaysMatchingRow);
		Reflection.getInstance().setField(victim, "rows", patternRows);
	}

	@Test
	public void matches_3xPatter4xLines_ReturnsFalse() {
		givenAlwaysMatchingPattern(3);
		whenMatches(Collections.nCopies(4, MATCHING_STRING));
		thenMatchesShouldBe(false);
	}

	@Test
	public void matches_3xPatter3xLines_ReturnsFalse() {
		givenAlwaysMatchingPattern(3);
		whenMatches(Collections.nCopies(3, MATCHING_STRING));
		thenMatchesShouldBe(true);
	}

	@Test
	public void matches_NotMatching3xPatter3xLines_ReturnsFalse() {
		givenNotMatchingPattern(3);
		whenMatches(Collections.nCopies(3, MATCHING_STRING));
		thenMatchesShouldBe(false);
	}

	private void givenNotMatchingPattern(int numberOfRows) {
		givenPattern(numberOfRows, false);
	}

	@Test
	public void matches_Matching_CallsMatchingForEveryChar() {
		givenNotMatchingPattern(3);
		whenMatches(Collections.nCopies(3, MATCHING_STRING));
		thenVerifyCallsMatchingForEveryChar(MATCHING_STRING);
	}

	private void thenVerifyCallsMatchingForEveryChar(String matchingString) {
		for (int i = 0; i < matchingString.length(); i++) {
			Mockito.verify(alwaysMatchingRow, Mockito.atLeastOnce()).matches(i,
					matchingString);
		}
	}

	@Test
	public void getRowsNumber_Empty_Returns0() {
		givenEmptyPattern();
		whenGetRowsNumber();
		thenExpectRowsNumber(0);
	}

	private void whenGetRowsNumber() {
		rowsNumber = victim.getRowsNumber();
	}

	private void thenExpectRowsNumber(int expectedNumber) {
		Assert.assertEquals(expectedNumber, rowsNumber);
	}

	@Test
	public void getRowsNumber_1Row_Returns1() {
		givenAlwaysMatchingPattern(1);
		whenGetRowsNumber();
		thenExpectRowsNumber(1);
	}

	@Test
	public void getRowsNumber_5Rows_Returns6() {
		givenAlwaysMatchingPattern(5);
		whenGetRowsNumber();
		thenExpectRowsNumber(5);
	}

}
