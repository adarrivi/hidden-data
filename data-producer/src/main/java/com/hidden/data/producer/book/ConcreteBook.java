package com.hidden.data.producer.book;

public abstract class ConcreteBook<K> implements Book<K> {

	private int id;
	private String title;

	protected ConcreteBook(int id, String title) {
		this.id = id;
		this.title = title;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getTitle() {
		return title;
	}

}
