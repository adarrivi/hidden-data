package com.hidden.data.db.model;

import org.junit.Assert;
import org.junit.Test;

import com.hidden.data.common.model.NotNullEntity;
import com.hidden.data.common.reflection.Reflection;
import com.hidden.data.common.test.AccessorVerifier;
import com.hidden.data.common.test.NotNulEntityTestable;
import com.hidden.data.common.test.NotNullEntityVerifier;
import com.hidden.data.db.model.verifier.PersistentEntityTestable;
import com.hidden.data.db.model.verifier.PersistentEntityVerifier;

public class PatternItemTest implements PersistentEntityTestable,
		NotNulEntityTestable {

	private static final char SPACE = ' ';
	private static final char CHAR = 'c';
	private static final Integer ITEM_ID = Integer.valueOf(1);
	private static final String VALUE = " ";

	private PatternItem victim;
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
		givenEmptyPatternItem();
		return victim;
	}

	@Override
	public NotNullEntity givenNotEmptyEntity() {
		givenPatternItem();
		return victim;
	}

	@Override
	public PersistentEntity givenNewEntity() {
		givenEmptyPatternItem();
		return victim;
	}

	private void givenEmptyPatternItem() {
		victim = PatternItem.createEmptyItem();
	}

	@Override
	public PersistentEntity givenExistingEntity() {
		givenPatternItem();
		return victim;
	}

	private void givenPatternItem() {
		givenEmptyPatternItem();
		Reflection.getInstance().setField(victim, "id", ITEM_ID);
		Reflection.getInstance().setField(victim, "value", VALUE);
	}

	@Test
	public void matches_CharAndEmptyPItem_ReturnsTrue() {
		givenEmptyPatternItem();
		whenMatches(CHAR);
		thenExpectMatches(true);
	}

	private void whenMatches(char valueToMatch) {
		matches = victim.matches(valueToMatch);
	}

	private void thenExpectMatches(boolean expectedValue) {
		Assert.assertEquals(expectedValue, matches);
	}

	@Test
	public void matches_SpaceAndEmptyPItem_ReturnsTrue() {
		givenEmptyPatternItem();
		whenMatches(SPACE);
		thenExpectMatches(true);
	}

	@Test
	public void matches_SpaceAndSpacePItem_ReturnsTrue() {
		givenPatternItem();
		whenMatches(SPACE);
		thenExpectMatches(true);
	}

	@Test
	public void matches_CharAndSpacePItem_ReturnsFalse() {
		givenPatternItem();
		whenMatches(CHAR);
		thenExpectMatches(false);
	}

	@Test
	public void verifyDirectGetters() {
		givenPatternItem();
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addGetterToVerify("getId", "id", ITEM_ID);
		verifier.addGetterToVerify("getValue", "value", VALUE);
		verifier.addGetterToVerify("getDoNotMatchValue", "doNotMatchValue",
				VALUE);
		verifier.verifyDirectGetters();
	}

	@Test
	public void matches_MatchItemTrueDoNotMatchTrue_ReturnFalse() {
		givenMatchAndDoNotMatchPItem();
		whenMatches(SPACE);
		thenExpectMatches(false);
	}

	private void givenMatchAndDoNotMatchPItem() {
		givenEmptyPatternItem();
		Reflection.getInstance().setField(victim, "id", ITEM_ID);
		Reflection.getInstance().setField(victim, "value", VALUE);
		Reflection.getInstance().setField(victim, "doNotMatchValue", VALUE);
	}
}
