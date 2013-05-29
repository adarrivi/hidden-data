package com.hidden.data.loader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;

import com.common.file.RelativeFile;
import com.common.iterator.ArrayIteratorStub;
import com.common.test.IteratorTestTemplate;
import com.hidden.data.loader.util.TestObjectFactory;

public class AuthorFolderIteratorDecoratorTest extends
		IteratorTestTemplate<AuthorFolder> {

	@SuppressWarnings("unchecked")
	private Iterator<RelativeFile> iterator = ArrayIteratorStub
			.createEmptyIterator();
	private RelativeFile relativeFile = TestObjectFactory.getInstance()
			.getAuthorFolder();

	@Before
	public void setUp() {
		createVictim();
	}

	private void createVictim() {
		victim = new AuthorFolderIteratorDecorator(iterator);
	}

	@Override
	protected void givenEmptyContent() throws Exception {
		createVictim();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void givenSingleItemContent() throws Exception {
		RelativeFile[] array = new RelativeFile[1];
		array[0] = relativeFile;
		iterator = new ArrayIteratorStub(array);
		createVictim();
	}

	@Override
	protected AuthorFolder getSingleContentItem() {
		return new AuthorFolder(relativeFile);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void givenMultipleItemsContent() {
		RelativeFile[] array = new RelativeFile[2];
		array[0] = relativeFile;
		array[1] = relativeFile;
		iterator = new ArrayIteratorStub(array);
		createVictim();
	}

	@Override
	protected List<AuthorFolder> getMultipleItemsContent() throws Exception {
		List<AuthorFolder> multipleItems = new ArrayList<AuthorFolder>();
		multipleItems.add(getSingleContentItem());
		multipleItems.add(getSingleContentItem());
		return multipleItems;
	}

	@Override
	protected AuthorFolder getNullItem() {
		return AuthorFolder.createEmpty();
	}

}
