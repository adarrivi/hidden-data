package com.hidden.data.db.model;

import java.util.ArrayList;
import java.util.List;

public class Pattern implements NotNullEntity, PersistentEntity {

	private Integer id;
	private String name;
	private List<PatternRow> rows;

	public static Pattern createEmptyPatter() {
		return new Pattern();
	}

	protected Pattern() {
		// Protected to be used by Hibernate
	}

	@Override
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean isEmpty() {
		return name == null;
	}

	public List<List<PatternItem>> getContent() {
		List<List<PatternItem>> items = new ArrayList<List<PatternItem>>();
		if (rows != null) {
			for (PatternRow row : rows) {
				items.add(row.getContent());
			}
		}
		return items;
	}
}
