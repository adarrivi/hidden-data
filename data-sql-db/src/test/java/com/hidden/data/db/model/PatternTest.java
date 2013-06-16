package com.hidden.data.db.model;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.common.reflexion.Reflexion;
import com.hidden.data.db.model.verifier.NotNulEntityTestable;
import com.hidden.data.db.model.verifier.NotNullEntityVerifier;
import com.hidden.data.db.model.verifier.PersistentEntityTestable;
import com.hidden.data.db.model.verifier.PersistentEntityVerifier;

public class PatternTest implements NotNulEntityTestable,
		PersistentEntityTestable {

	private static final Integer PATTERN_ID = Integer.valueOf(1);
	private static final String PATTERN_NAME = "3x3 Column in middle";
	private Pattern victim;
	private String name;
	private List<List<PatternItem>> content;

	@Test
	public void getName_ReturnsCreationName() {
		givenPattern();
		whenGetName();
		thenNameShoudlBeUsedInCreation();
	}

	private void givenPattern() {
		givenEmptyPattern();
		Reflexion.getInstance().setMember(victim, "id", PATTERN_ID);
		Reflexion.getInstance().setMember(victim, "name", PATTERN_NAME);
	}

	private void givenEmptyPattern() {
		victim = Pattern.createEmptyPatter();
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
		Reflexion.getInstance().setMember(patternRow, "content", rowContent);
		List<PersistentEntity> rowList = Collections.nCopies(rows, patternRow);
		Reflexion.getInstance().setMember(victim, "rows", rowList);
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

}
