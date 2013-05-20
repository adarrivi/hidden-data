package com.hidden.data.db;

import org.springframework.stereotype.Service;

@Service
public class BookDao {

	private String property;

	public void doSomething() {
		System.out.println("Something done: " + property);
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
