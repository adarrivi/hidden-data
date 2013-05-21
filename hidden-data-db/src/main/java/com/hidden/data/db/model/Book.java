package com.hidden.data.db.model;

import java.sql.Blob;

public class Book {

	private Integer id;
	private String title;
	private Blob content;

	public static Book createEmptyBook() {
		return new Book(null, null);
	}

	protected Book() {
		// Used by Hibernate
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

	public Blob getContent() {
		return content;
	}
}
