package com.common.test;

import org.junit.Assert;

public class EqualsHashTest {

	private Object victim;
	private Object equals;
	private Object differents[];

	public EqualsHashTest(Object victim, Object equals, Object... different) {
		this.victim = victim;
		this.equals = equals;
		this.differents = different;
	}

	public void verify() {
		verifyEqualsValueIs(true, equals);
		verifyDifferents();
	}

	private void verifyEqualsValueIs(boolean value, Object item) {
		assertItemsNotNull(item);
		verifySymmetricalEqualsIs(value, item);
		verifyHashCodeEqualsValueIs(value, item);
	}

	private void assertItemsNotNull(Object item) {
		if (victim == null || item == null) {
			throw new AssertionError("Null does not have equals or hashcode");
		}
	}

	private void verifySymmetricalEqualsIs(boolean value, Object item) {
		Assert.assertEquals(value, victim.equals(item));
		Assert.assertEquals(value, item.equals(victim));
	}

	private void verifyHashCodeEqualsValueIs(boolean value, Object item) {
		Assert.assertEquals(value, victim.hashCode() == item.hashCode());
	}

	private void verifyDifferents() {
		if (differents == null || differents.length == 0) {
			return;
		}
		for (Object different : differents) {
			verifyEqualsValueIs(false, different);
		}
	}
}
