package com.hidden.data.db.model;

import org.junit.Assert;
import org.junit.Test;

import com.common.reflexion.Reflexion;

public class PatternTest {

	private static final Integer PATTERN_ID = Integer.valueOf(1);
	private static final String PATTERN_NAME = "3x3 Column in middle";
	private Pattern victim;
	private Integer id;
	private String name;
	private boolean isEmpty;

	@Test
	public void getId_ReturnsCreationId() {
		givenPattern();
		whenGetId();
		thenIdShoudBeUsedInCreation();
	}

	private void givenPattern() {
		givenEmptyPattern();
		Reflexion.getInstance().setMember(victim, "id", PATTERN_ID);
		victim.setName(PATTERN_NAME);
	}

	private void givenEmptyPattern() {
		victim = Pattern.createEmptyPatter();
	}

	private void whenGetId() {
		id = victim.getId();
	}

	private void thenIdShoudBeUsedInCreation() {
		Assert.assertEquals(PATTERN_ID, id);
	}

	@Test
	public void getName_ReturnsCreationName() {
		givenPattern();
		whenGetName();
		thenNameShoudlBeUsedInCreation();
	}

	private void whenGetName() {
		name = victim.getName();
	}

	private void thenNameShoudlBeUsedInCreation() {
		Assert.assertEquals(PATTERN_NAME, name);
	}

	@Test
	public void isEmpty_CreateEmpty_ReturnsTrue() {
		givenEmptyPattern();
		whenIsEmpty();
		thenEmptyShouldBe(true);
	}

	private void whenIsEmpty() {
		isEmpty = victim.isEmpty();
	}

	private void thenEmptyShouldBe(boolean expectedValue) {
		Assert.assertEquals(expectedValue, isEmpty);
	}

	@Test
	public void isEmpty_NotNullName_ReturnsFalse() {
		givenPattern();
		whenIsEmpty();
		thenEmptyShouldBe(false);
	}

}
