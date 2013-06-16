package com.hidden.data.db.model.verifier;

import com.hidden.data.db.model.PersistentEntity;

public interface PersistentEntityTestable {

	PersistentEntity givenNewEntity();

	PersistentEntity givenExistingEntity();

}
