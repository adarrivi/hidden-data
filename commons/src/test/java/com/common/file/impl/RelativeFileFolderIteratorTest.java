package com.common.file.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.common.file.RelativeFile;
import com.common.test.IteratorTestTemplate;
import com.common.util.TestCommonsObjectFactory;

public class RelativeFileFolderIteratorTest extends
		IteratorTestTemplate<RelativeFile> {

	private Iterator<File> iterator;
	private RelativeFile relativeFile = TestCommonsObjectFactory.getInstance()
			.getRelativeFile();

	@Override
	protected void createVictim() {
		victim = new RelativeFileFolderIterator(iterator,
				RelativeFileFactoryImpl.getInstance());
	}

	@Override
	protected void givenEmptyContent() throws Exception {
		iterator = Collections.<File> emptyList().iterator();
		createVictim();
	}

	@Override
	protected void givenSingleItemContent() throws Exception {
		iterator = Collections.singletonList(relativeFile.getFile()).iterator();
		createVictim();
	}

	@Override
	protected RelativeFile getSingleContentItem() throws Exception {
		return relativeFile;
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
	protected RelativeFileImpl getNullItem() {
		return RelativeFileImpl.createEmpty();
	}

}
