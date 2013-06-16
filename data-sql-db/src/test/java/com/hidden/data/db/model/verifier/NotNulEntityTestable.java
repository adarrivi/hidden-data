package com.hidden.data.db.model.verifier;

import com.hidden.data.db.model.NotNullEntity;

public interface NotNulEntityTestable {

	NotNullEntity givenEmptyEntity();

	NotNullEntity givenNotEmptyEntity();
}
