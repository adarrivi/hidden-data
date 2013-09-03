package com.hidden.data.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.hidden.data.common.file.CommonsFileUtils;

public class Library {

	private File folder;
	private CommonsFileUtils commonsFileUtils;

	public Library(File folder, CommonsFileUtils commonsFileUtils) {
		this.folder = folder;
		this.commonsFileUtils = commonsFileUtils;
	}

	public List<BookFile> getBooks() {
		List<BookFile> books = new ArrayList<BookFile>();
		for (File file : commonsFileUtils.getFilesFromFolder(folder)) {
			books.add(new BookFile(file));
		}
		return books;
	}

}
