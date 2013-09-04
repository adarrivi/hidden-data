package com.hidden.data.db.model.verifier;

import org.junit.Assert;

import com.hidden.data.common.model.NotNullEntity;

public class NotNullEntityVerifier {

	private NotNulEntityTestable testVictim;

	public NotNullEntityVerifier(NotNulEntityTestable victimToTest) {
		this.testVictim = victimToTest;
	}

	public void verify() {
		isEmpty_EmptyEntity_ReturnsTrue();
		isEmpty_NotEmptyEntity_ReturnsFalse();
	}

	private void isEmpty_EmptyEntity_ReturnsTrue() {
		NotNullEntity entity = testVictim.givenEmptyEntity();
		Assert.assertTrue(entity.isEmpty());
	}

	private void isEmpty_NotEmptyEntity_ReturnsFalse() {
		NotNullEntity entity = testVictim.givenNotEmptyEntity();
		Assert.assertFalse(entity.isEmpty());
	}

}
