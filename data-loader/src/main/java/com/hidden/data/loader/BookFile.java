package com.hidden.data.loader;

import java.io.File;

public class BookFile {

	private static final String FILE_NAME_SUFFIX_START = ".";
	private String title;
	private File file;

	public BookFile(File file) {
		this.file = file;
		title = removeLastSuffixFromFileName();
	}

	private String removeLastSuffixFromFileName() {
		String modifiedFileName = file.getName();
		if (modifiedFileName.contains(FILE_NAME_SUFFIX_START)) {
			modifiedFileName = modifiedFileName.substring(0,
					modifiedFileName.lastIndexOf(FILE_NAME_SUFFIX_START));
		}
		return modifiedFileName;
	}

	public String getTitle() {
		return title;
	}

	public File getFile() {
		return file;
	}

}
