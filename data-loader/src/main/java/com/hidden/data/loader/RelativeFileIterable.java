package com.hidden.data.loader;

import com.common.file.RelativeFile;

abstract class RelativeFileIterable<K> implements Iterable<K> {

	protected RelativeFile folder;

	public boolean isEmpty() {
		return folder.isEmpty();
	}

	protected RelativeFileIterable(RelativeFile folder) {
		this.folder = folder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + folder.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RelativeFileIterable)) {
			return false;
		}
		RelativeFileIterable<?> other = (RelativeFileIterable<?>) obj;
		return folder.equals(other.folder);
	}

}
