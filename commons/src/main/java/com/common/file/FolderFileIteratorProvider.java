package com.common.file;

import java.io.File;
import java.util.Iterator;

public interface FolderFileIteratorProvider {

	Iterator<File> getFileIterator();

}
