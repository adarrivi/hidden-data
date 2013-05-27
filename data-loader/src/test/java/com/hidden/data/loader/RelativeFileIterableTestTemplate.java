package com.hidden.data.loader;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import com.common.file.RelativeFile;
import com.hidden.data.loader.util.TestObjectFactory;

public abstract class RelativeFileIterableTestTemplate<K> {

	private RelativeFileIterable<K> parentVictim;
	protected RelativeFile folder;
	protected Iterator<K> iterator;

	@Test
	public void iterator_EmptyFolder_emptyIterator() {
		givenEmptyFolder();
		whenIterator();
		thenEmptyIterator();
	}

	private void givenEmptyFolder() {
		folder = TestObjectFactory.getInstance().getEmptyFolder();
		createParentVictim();
	}

	private void createParentVictim() {
		parentVictim = getNewVictim();
	}

	protected abstract RelativeFileIterable<K> getNewVictim();

	private void whenIterator() {
		iterator = parentVictim.iterator();
	}

	private void thenEmptyIterator() {
		Assert.assertNotNull(iterator);
		Assert.assertFalse(iterator.hasNext());
	}

	@Test
	public void iterator_Folder_NotEmptyIterator() {
		givenFolder();
		whenIterator();
		thenNotEmptyIterator();
	}

	private void givenFolder() {
		folder = TestObjectFactory.getInstance().getAuthorFolder();
		createParentVictim();
	}

	private void thenNotEmptyIterator() {
		Assert.assertNotNull(iterator);
		Assert.assertTrue(iterator.hasNext());
	}
}
