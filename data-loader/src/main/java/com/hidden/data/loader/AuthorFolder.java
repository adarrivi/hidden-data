package com.hidden.data.loader;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;

import com.common.file.RelativeFile;
import com.hidden.data.loader.exception.LoaderException;

class AuthorFolder extends RelativeFileIterable<File> {

	private static final String AUTHOR_NAME_SEPARATOR = " ";
	private static final String FOLDER_NAME_SEPARATOR = ".";
	private String authorName;

	AuthorFolder(RelativeFile folder) {
		super(folder);
		setAuthorNameFromFolder();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<File> iterator() {
		return FileUtils.iterateFiles(folder.getFile(),
				TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
	}

	public String getAuthorName() {
		return authorName;
	}

	private void setAuthorNameFromFolder() {
		authorName = folder.getFile().getName();
		assertNotEmptyAuthorName();
		parseAuthorNameFromFolder();
	}

	private void assertNotEmptyAuthorName() {
		if (StringUtils.isEmpty(authorName)) {
			throw new LoaderException(
					"The author (folder name) cannot be empty.");
		}
	}

	private void parseAuthorNameFromFolder() {
		authorName = authorName.replace(FOLDER_NAME_SEPARATOR,
				AUTHOR_NAME_SEPARATOR);
	}

}
