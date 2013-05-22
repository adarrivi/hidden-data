package com.hidden.data.db.model;

public class Author {
	private Integer id;
	private String name;

	protected Author() {
		// Protected to be used by Hibernate
	}

	public static Author createEmptyAuthor() {
		return new Author();
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(id).append(", ").append(name).append("]");
		return sb.toString();

	}

}
