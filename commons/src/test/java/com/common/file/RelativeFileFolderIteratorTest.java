package com.common.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;

import com.common.test.IteratorTestTemplate;
import com.common.util.TestCommonsObjectFactory;

public class RelativeFileFolderIteratorTest extends
		IteratorTestTemplate<RelativeFile> {

	private Iterator<File> iterator;
	private File file = TestCommonsObjectFactory.getInstance()
			.getExistingFile();

	@Before
	public void setUp() {
		createVictim();
	}

	private void createVictim() {
		victim = new RelativeFileFolderIterator(iterator);
	}

	@Override
	protected void givenEmptyContent() throws Exception {
		iterator = Collections.<File> emptyList().iterator();
		createVictim();
	}

	@Override
	protected void givenSingleItemContent() throws Exception {
		iterator = Collections.singletonList(file).iterator();
		createVictim();
	}

	@Override
	protected RelativeFile getSingleContentItem() throws Exception {
		return new RelativeFile(file.getPath());
	}

	@Override
	protected void givenTwoItemsContent() throws Exception {
		List<File> fileList = new ArrayList<File>();
		fileList.add(getSingleContentItem().getFile());
		fileList.add(getSingleContentItem().getFile());
		iterator = fileList.iterator();
		createVictim();
	}

	@Override
	protected RelativeFile getNullItem() {
		return RelativeFile.createEmpty();
	}

}
