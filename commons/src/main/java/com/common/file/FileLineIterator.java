package com.common.file;

import java.util.Iterator;

public interface FileLineIterator<K> extends Iterator<K> {
	void close();
}
