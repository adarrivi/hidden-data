package com.hidden.data.db.model.verifier;

import org.junit.Assert;

import com.hidden.data.db.model.PersistentEntity;

public class PersistentEntityVerifier {

	private PersistentEntityTestable testVictim;

	public PersistentEntityVerifier(PersistentEntityTestable victimToTest) {
		this.testVictim = victimToTest;
	}

	public void verify() {
		getId_NewEntity_ReturnsNull();
		getId_ExistingEntity_ReturnsId();
	}

	private void getId_NewEntity_ReturnsNull() {
		PersistentEntity entity = testVictim.givenNewEntity();
		Assert.assertNull(entity.getId());
	}

	private void getId_ExistingEntity_ReturnsId() {
		PersistentEntity entity = testVictim.givenExistingEntity();
		Assert.assertNotNull(entity.getId());
	}

}
