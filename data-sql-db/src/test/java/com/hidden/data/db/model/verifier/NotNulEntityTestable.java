package com.hidden.data.db.model.verifier;

import com.hidden.data.common.model.NotNullEntity;

public interface NotNulEntityTestable {

	NotNullEntity givenEmptyEntity();

	NotNullEntity givenNotEmptyEntity();
}
