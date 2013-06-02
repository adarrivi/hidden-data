package com.hidden.data.db.model;

public class Author implements NotNullEntity, PersistentEntity {
	private Integer id;
	private String name;

	protected Author() {
		// Protected to be used by Hibernate
	}

	public static Author createEmptyAuthor() {
		return new Author();
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

	@Override
	public boolean isEmpty() {
		return name == null;
	}

}
