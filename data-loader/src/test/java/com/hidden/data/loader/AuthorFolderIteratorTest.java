package com.hidden.data.loader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.common.file.RelativeFile;
import com.common.file.RelativeFileFactory;
import com.common.file.impl.RelativeFileFactoryImpl;
import com.common.test.IteratorTestTemplate;
import com.hidden.data.loader.util.TestObjectFactory;

public class AuthorFolderIteratorTest extends
		IteratorTestTemplate<AuthorFolder> {

	private Iterator<RelativeFile> iterator = Collections
			.<RelativeFile> emptyList().iterator();
	private RelativeFile relativeFile = TestObjectFactory.getInstance()
			.getAuthorFolder();
	private RelativeFileFactory relativeFileFactory = RelativeFileFactoryImpl
			.getInstance();

	@Override
	protected void createVictim() {
		victim = new AuthorFolderIterator(iterator, relativeFileFactory);
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
		return new AuthorFolder(relativeFile, relativeFileFactory);
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
		return new AuthorFolder(relativeFileFactory.createEmptyRelativeFile(),
				relativeFileFactory);
	}

}
