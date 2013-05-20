package com.hidden.data.db.model;

public class Book {

	private Integer id;
	private String title;

	public static Book createEmptyBook() {
		return new Book(null, null);
	}

	protected Book(Integer id, String title) {
		this.id = id;
		this.title = title;
	}

	public boolean isEmpty() {
		return getId() == null && getTitle() == null;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

}
