package com.hidden.data.db.model;

import com.hidden.data.common.model.NotNullEntity;

public class PatternItem implements NotNullEntity, PersistentEntity {

	private Integer id;
	private String value;
	private String doNotMatchValue;
	private char valueToMatch;

	public static PatternItem createEmptyItem() {
		return new PatternItem();
	}

	protected PatternItem() {
		// Used by Hibernate
	}

	public String getValue() {
		return value;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public String getDoNotMatchValue() {
		return doNotMatchValue;
	}

	@Override
	public boolean isEmpty() {
		return value == null && doNotMatchValue == null;
	}

	public boolean matches(char valueToMatch) {
		this.valueToMatch = valueToMatch;
		return matchesValue() && !matchesDoNotMatchValue();
	}

	private boolean matchesValue() {
		if (value == null) {
			return true;
		}
		return value.equals(String.valueOf(valueToMatch));
	}

	private boolean matchesDoNotMatchValue() {
		if (doNotMatchValue == null) {
			return false;
		}
		return doNotMatchValue.equals(String.valueOf(valueToMatch));
	}

}
