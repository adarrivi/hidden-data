package com.hidden.data.loader;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class BookFile {

	private static final String TITLE_AUTHOR_SEPARATOR = ".by.";
	private String title;
	private String author;
	private File file;

	public BookFile(File file) {
		this.file = file;
	}

	private void setBookPropertiesIfNecesary() {
		if (author == null) {
			String fileName = FilenameUtils.removeExtension(file.getName());
			String[] fileNameComponents = fileName
					.split(TITLE_AUTHOR_SEPARATOR);
			title = fileNameComponents[0];
			author = fileNameComponents[1];
		}
	}

	public String getTitle() {
		setBookPropertiesIfNecesary();
		return title;
	}

	public File getFile() {
		return file;
	}

	public String getAuthor() {
		setBookPropertiesIfNecesary();
		return author;
	}

}
