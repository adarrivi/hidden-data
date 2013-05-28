package com.hidden.data.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.common.file.RelativeFile;
import com.common.test.IteratorTestTemplate;

public class AuthorFolderIteratorDecoratorTest extends
		IteratorTestTemplate<AuthorFolder> {

	private static final String AUTHOR_NAME = "Anonymous";
	@Mock
	private Iterator<File> iterator;
	@Mock
	private File file;
	@Mock
	private RelativeFile relativeFile;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(file.getName()).thenReturn(AUTHOR_NAME);
		Mockito.when(relativeFile.getFile()).thenReturn(file);
	}

	@Override
	protected void givenEmptyContent() throws Exception {
		Mockito.when(iterator.hasNext()).thenReturn(false);
		createVictim();
	}

	private void createVictim() {
		// victim = new AuthorFolderIteratorDecorator(iterator);
	}

	@Override
	protected void givenSingleItemContent() throws Exception {
		Mockito.when(iterator.hasNext()).thenReturn(true, false);
		Mockito.when(iterator.next()).thenReturn(file, (File) null);
		createVictim();
	}

	@Override
	protected AuthorFolder getSingleContentItem() throws Exception {
		return new AuthorFolder(relativeFile);
	}

	@Override
	protected void givenMultipleItemsContent() throws Exception {
		Mockito.when(iterator.hasNext()).thenReturn(true, true, false);
		Mockito.when(iterator.next()).thenReturn(file, file, (File) null);
		createVictim();
	}

	@Override
	protected List<AuthorFolder> getMultipleItemsContent() throws Exception {
		List<AuthorFolder> multipleItems = new ArrayList<AuthorFolder>();
		multipleItems.add(getSingleContentItem());
		multipleItems.add(getSingleContentItem());
		return multipleItems;
	}

}
