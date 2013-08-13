package com.hidden.data.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.hidden.data.common.file.CommonsFileUtils;

public class Library {

	private File folder;
	private CommonsFileUtils commonsFileUtils;

	public Library(File folder, CommonsFileUtils commonsFileUtils) {
		this.folder = folder;
		this.commonsFileUtils = commonsFileUtils;
	}

	public Collection<AuthorFolder> getAuthors() {
		List<AuthorFolder> authors = new ArrayList<AuthorFolder>();
		for (File authorFolder : commonsFileUtils.getSubFolders(folder)) {
			authors.add(new AuthorFolder(authorFolder, commonsFileUtils));
		}
		return authors;

	}

}
