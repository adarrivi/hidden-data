package com.hidden.data.db.model;

public class Book implements NotNullEntity, PersistentEntity {

	private Integer id;
	private String title;
	private String content;
	private Author author;

	public static Book createEmptyBook() {
		return new Book();
	}

	protected Book() {
		// Used by Hibernate
	}

	@Override
	public boolean isEmpty() {
		return getId() == null && getTitle() == null;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}
