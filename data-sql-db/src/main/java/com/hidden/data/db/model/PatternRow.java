package com.hidden.data.db.model;

import java.util.List;

public class PatternRow implements PersistentEntity {

	private Integer id;
	private List<PatternItem> content;

	PatternRow() {
		// Used by Hibernate
	}

	List<PatternItem> getContent() {
		return content;
	}

	@Override
	public Integer getId() {
		return id;
	}
}