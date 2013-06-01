package com.common.file.reader;

import java.util.Iterator;

public interface FileLineIterator<K> extends Iterator<K> {
	void close();
}
