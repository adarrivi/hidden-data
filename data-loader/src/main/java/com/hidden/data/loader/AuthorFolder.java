package com.hidden.data.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.common.file.CommonsFileUtils;

class AuthorFolder {

	private String authorName;
	private File folder;
	private CommonsFileUtils commonsFileUtils;

	public AuthorFolder(File folder, CommonsFileUtils commonsFileUtils) {
		this.folder = folder;
		this.commonsFileUtils = commonsFileUtils;
		this.authorName = folder.getName();
	}

	public String getAuthorName() {
		return authorName;
	}

	public Collection<BookFile> getBookFiles() {
		List<BookFile> bookFiles = new ArrayList<BookFile>();
		for (File file : commonsFileUtils.getFilesFromFolder(folder)) {
			bookFiles.add(new BookFile(file));
		}
		return bookFiles;
	}
}
