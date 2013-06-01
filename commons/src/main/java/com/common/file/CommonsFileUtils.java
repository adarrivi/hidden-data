package com.common.file;

import java.io.File;
import java.util.Collection;

public interface CommonsFileUtils {

	File getFileFromRelativePath(String relativePath);

	Collection<File> getFilesFromFolder(File folder);

	Collection<File> getSubFolders(File folder);

}
