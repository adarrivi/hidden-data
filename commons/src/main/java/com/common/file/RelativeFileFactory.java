package com.common.file;

import java.util.Iterator;

public interface RelativeFileFactory {

	Iterator<RelativeFile> createFolderFileIterator(RelativeFile folder);

	RelativeFile createRelativeFile(String relativePath);

	RelativeFile createEmptyRelativeFile();

}
