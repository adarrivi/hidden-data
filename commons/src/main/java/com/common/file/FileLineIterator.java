package com.common.file;

import java.util.Iterator;

public interface FileLineIterator extends Iterator<String> {
	void close();
}
