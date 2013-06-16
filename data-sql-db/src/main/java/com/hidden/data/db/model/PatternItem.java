package com.hidden.data.db.model;

public class PatternItem implements NotNullEntity, PersistentEntity {

	private Integer id;
	private String value;

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

	@Override
	public boolean isEmpty() {
		return value == null;
	}

	public boolean matches(String valueToMatch) {
		if (isEmpty()) {
			return true;
		}
		return getValue().equals(valueToMatch);
	}

}
