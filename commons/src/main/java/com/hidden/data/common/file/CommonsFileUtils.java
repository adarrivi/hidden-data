package com.hidden.data.common.file;

import java.io.File;
import java.util.Collection;

public interface CommonsFileUtils {

	File getFileFromRelativePath(String relativePath);

	File getFileFromAbsolutePath(String absolutePath);

	Collection<File> getFilesFromFolder(File folder);

	Collection<File> getSubFolders(File folder);

	String getFileContentAsString(File file);

}
