package com.hidden.data.db.model;

public class Book implements NotNullEntity, PersistentEntity {

	private Integer id;
	private String title;
	private String content;

	public static Book createEmptyBook() {
		return new Book(null, null);
	}

	public static Book createBook(String title, String content) {
		return new Book(title, content);
	}

	protected Book() {
		// Used by Hibernate
	}

	protected Book(String title, String content) {
		this.title = title;
		this.content = content;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(getId()).append(", ").append(getTitle())
				.append(", ").append("]");
		return sb.toString();
	}

}
