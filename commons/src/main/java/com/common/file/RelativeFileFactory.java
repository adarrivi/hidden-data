package com.common.file;

import java.io.File;
import java.util.Iterator;

public interface RelativeFileFactory {

	Iterator<RelativeFile> createFolderFileIterator(RelativeFile folder);

	RelativeFile createRelativeFileFromPath(String relativePath);

	RelativeFile createRelativeFileFromFile(File file);

	RelativeFile createEmptyRelativeFile();

}
