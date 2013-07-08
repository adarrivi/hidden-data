package com.hidden.data.web.dto;

import java.io.Serializable;

public class DatabaseInfoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	int numberOfBooks;
	int numberOfPatterns;
	int numberOfAuthors;

	public DatabaseInfoDto(int numberOfBooks, int numberOfPatterns,
			int numberOfAuthors) {
		this.numberOfBooks = numberOfBooks;
		this.numberOfPatterns = numberOfPatterns;
		this.numberOfAuthors = numberOfAuthors;
	}

	public int getNumberOfBooks() {
		return numberOfBooks;
	}

	public int getNumberOfPatterns() {
		return numberOfPatterns;
	}

	public int getNumberOfAuthors() {
		return numberOfAuthors;
	}

}
