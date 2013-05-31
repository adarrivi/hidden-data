package com.hidden.data.loader;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import com.common.file.RelativeFile;
import com.common.file.RelativeFileFactory;
import com.hidden.data.loader.exception.LoaderException;

class AuthorFolder extends RelativeFileIterable<RelativeFile> {

	private static final String AUTHOR_NAME_SEPARATOR = " ";
	private static final String FOLDER_NAME_SEPARATOR = ".";
	private String authorName;
	private RelativeFileFactory relativeFileFactory;

	AuthorFolder createEmpty() {
		return new AuthorFolder(relativeFileFactory.createEmptyRelativeFile(),
				relativeFileFactory);
	}

	AuthorFolder(RelativeFile folder, RelativeFileFactory relativeFileFactory) {
		super(folder);
		this.relativeFileFactory = relativeFileFactory;
		setAuthorNameFromFolder();
	}

	@Override
	public Iterator<RelativeFile> iterator() {
		return relativeFileFactory.createFolderFileIterator(folder);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + authorName.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof AuthorFolder)) {
			return false;
		}
		AuthorFolder other = (AuthorFolder) obj;
		return authorName.equals(other.authorName);
	}

}
