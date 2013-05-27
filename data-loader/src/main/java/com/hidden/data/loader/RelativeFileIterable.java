package com.hidden.data.loader;

import com.common.file.RelativeFile;

abstract class RelativeFileIterable<K> implements Iterable<K> {

	protected RelativeFile folder;

	protected RelativeFileIterable(RelativeFile folder) {
		this.folder = folder;
	}

}
