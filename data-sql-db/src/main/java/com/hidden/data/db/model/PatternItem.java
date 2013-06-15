package com.hidden.data.db.model;

public class PatternItem implements NotNullEntity, PersistentEntity {

	private Integer id;
	private String value;

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

}
