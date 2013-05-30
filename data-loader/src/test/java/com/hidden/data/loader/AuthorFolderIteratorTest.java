package com.hidden.data.loader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;

import com.common.file.RelativeFile;
import com.common.test.IteratorTestTemplate;
import com.hidden.data.loader.util.TestObjectFactory;

public class AuthorFolderIteratorTest extends
		IteratorTestTemplate<AuthorFolder> {

	private Iterator<RelativeFile> iterator = Collections
			.<RelativeFile> emptyList().iterator();
	private RelativeFile relativeFile = TestObjectFactory.getInstance()
			.getAuthorFolder();

	@Before
	public void setUp() {
		createVictim();
	}

	private void createVictim() {
		victim = new AuthorFolderIterator(iterator);
	}

	@Override
	protected void givenEmptyContent() throws Exception {
		createVictim();
	}

	@Override
	protected void givenSingleItemContent() throws Exception {
		iterator = Collections.singleton(relativeFile).iterator();
		createVictim();
	}

	@Override
	protected AuthorFolder getSingleContentItem() {
		return new AuthorFolder(relativeFile);
	}

	@Override
	protected void givenTwoItemsContent() {
		List<RelativeFile> files = new ArrayList<RelativeFile>();
		files.add(relativeFile);
		files.add(relativeFile);
		iterator = files.iterator();
		createVictim();
	}

	@Override
	protected AuthorFolder getNullItem() {
		return AuthorFolder.createEmpty();
	}

}
