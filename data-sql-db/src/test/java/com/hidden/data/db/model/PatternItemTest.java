package com.hidden.data.db.model;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.common.reflexion.Reflexion;
import com.hidden.data.db.model.verifier.NotNulEntityTestable;
import com.hidden.data.db.model.verifier.NotNullEntityVerifier;
import com.hidden.data.db.model.verifier.PersistentEntityTestable;
import com.hidden.data.db.model.verifier.PersistentEntityVerifier;

public class PatternItemTest implements PersistentEntityTestable,
		NotNulEntityTestable {

	private static final String SPACE = " ";
	private static final String CHAR = "c";
	private static final Integer ITEM_ID = Integer.valueOf(1);
	private static final String ITEM_VALUE = SPACE;

	private PatternItem victim;
	private String value;
	private boolean matches;

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

	@Override
	public NotNullEntity givenEmptyEntity() {
		givenEmptyItem();
		return victim;
	}

	@Override
	public NotNullEntity givenNotEmptyEntity() {
		givenSpacePItem();
		return victim;
	}

	@Override
	public PersistentEntity givenNewEntity() {
		givenEmptyItem();
		return victim;
	}

	private void givenEmptyItem() {
		victim = PatternItem.createEmptyItem();
	}

	@Override
	public PersistentEntity givenExistingEntity() {
		givenSpacePItem();
		return victim;
	}

	private void givenSpacePItem() {
		givenEmptyItem();
		Reflexion.getInstance().setMember(victim, "id", ITEM_ID);
		Reflexion.getInstance().setMember(victim, "value", ITEM_VALUE);
	}

	@Test
	public void getValue_ReturnsExpectedValue() {
		givenExistingEntity();
		whenGetValue();
		thenExpectValue(ITEM_VALUE);
	}

	private void whenGetValue() {
		value = victim.getValue();
	}

	private void thenExpectValue(String expectedValue) {
		Assert.assertEquals(expectedValue, value);
	}

	@Test
	public void matches_EmtpyAndEmptyPItem_ReturnsTrue() {
		givenEmptyItem();
		whenMatches(StringUtils.EMPTY);
		thenExpectMatches(true);
	}

	private void whenMatches(String valueToMatch) {
		matches = victim.matches(valueToMatch);
	}

	private void thenExpectMatches(boolean expectedValue) {
		Assert.assertEquals(expectedValue, matches);
	}

	@Test
	public void matches_CharAndEmptyPItem_ReturnsTrue() {
		givenEmptyItem();
		whenMatches(CHAR);
		thenExpectMatches(true);
	}

	@Test
	public void matches_SpaceAndEmptyPItem_ReturnsTrue() {
		givenEmptyItem();
		whenMatches(SPACE);
		thenExpectMatches(true);
	}

	@Test
	public void matches_SpaceAndSpacePItem_ReturnsTrue() {
		givenSpacePItem();
		whenMatches(SPACE);
		thenExpectMatches(true);
	}

	@Test
	public void matches_EmptyAndSpacePItem_ReturnsFalse() {
		givenSpacePItem();
		whenMatches(StringUtils.EMPTY);
		thenExpectMatches(false);
	}

	@Test
	public void matches_CharAndSpacePItem_ReturnsFalse() {
		givenSpacePItem();
		whenMatches(CHAR);
		thenExpectMatches(false);
	}

}
