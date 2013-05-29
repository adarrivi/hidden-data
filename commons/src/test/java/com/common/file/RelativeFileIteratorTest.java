package com.common.file;

import java.io.File;
import java.util.Iterator;

import org.junit.Before;

import com.common.iterator.ArrayIteratorStub;
import com.common.test.IteratorTestTemplate;
import com.common.util.TestCommonsObjectFactory;

public class RelativeFileIteratorTest extends
		IteratorTestTemplate<RelativeFile> {

	private Iterator<File> iterator;
	private File file = TestCommonsObjectFactory.getInstance()
			.getExistingFile();

	@Before
	public void setUp() {
		createVictim();
	}

	private void createVictim() {
		victim = new RelativeFileIterator(iterator);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void givenEmptyContent() throws Exception {
		iterator = ArrayIteratorStub.createEmptyIterator();
		createVictim();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void givenSingleItemContent() throws Exception {
		File[] files = new File[] { file };
		iterator = new ArrayIteratorStub(files);
		createVictim();
	}

	@Override
	protected RelativeFile getSingleContentItem() throws Exception {
		return new RelativeFile(file.getPath());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void givenMultipleItemsContent() throws Exception {
		File[] files = new File[] { file, file, file };
		iterator = new ArrayIteratorStub(files);
		createVictim();
	}

	@Override
	protected RelativeFile getNullItem() {
		return RelativeFile.createEmpty();
	}

}
