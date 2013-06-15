package com.hidden.data.db.model;

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

	public void setName(String name) {
		this.name = name;
	}

	public List<PatternRow> getRows() {
		return rows;
	}

	@Override
	public boolean isEmpty() {
		return name == null;
	}

	public String prettyFormat() {
		StringBuilder sb = new StringBuilder();
		sb.append("[id: ").append(id.intValue()).append(", name: ")
				.append(name).append(", {\n");
		for (PatternRow row : rows) {
			for (PatternItem item : row.getContent()) {
				if (item.isEmpty()) {
					sb.append("*");
				} else {
					sb.append(item.getValue());
				}
			}
			sb.append("\n");
		}
		sb.append("}]");
		return sb.toString();
	}

}
