package com.common.file.impl;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.common.file.FileException;
import com.common.test.IteratorTestTemplate;

public class BufferedReaderIteratorTest extends IteratorTestTemplate<String> {

	private static final String FIRST_LINE = "first line";
	@Mock
	private BufferedReader reader;
	@Mock
	private RelativeBufferedReader bufferedReaderProvider;

	@Before
	public void setUp() throws IOException {
		MockitoAnnotations.initMocks(this);
		givenEmptyContent();
	}

	@Override
	protected void givenEmptyContent() throws IOException {
		Mockito.when(reader.readLine()).thenReturn(null);
		victim = new BufferedReaderIterator(reader);
	}

	@Override
	protected void givenSingleItemContent() throws IOException {
		Mockito.when(reader.readLine()).thenReturn(getSingleContentItem(),
				(String) null);
		victim = new BufferedReaderIterator(reader);
	}

	@Override
	protected String getSingleContentItem() {
		return FIRST_LINE;
	}

	@Override
	protected void givenTwoItemsContent() throws IOException {
		Mockito.when(reader.readLine()).thenReturn(getSingleContentItem(),
				getSingleContentItem(), null);
		victim = new BufferedReaderIterator(reader);
	}

	@Test
	public void next_IoExReading_ThrowsFileEx() throws IOException {
		expectFileExceptionContaining(IOException.class.getCanonicalName());
		givenIoExceptionWhenReading();
		whenNext();
	}

	private void expectFileExceptionContaining(String message) {
		expectedException.expect(FileException.class);
		expectedException.expectMessage(message);
	}

	private void givenIoExceptionWhenReading() throws IOException {
		Mockito.when(reader.readLine()).thenThrow(new IOException());
	}

	@Test
	public void close_SingleLineFile_ClosesReader() throws IOException {
		givenSingleItemContent();
		whenClose();
		thenReaderShouldBeClosed();
	}

	private void whenClose() {
		((BufferedReaderIterator) victim).close();
	}

	private void thenReaderShouldBeClosed() throws IOException {
		Mockito.verify(reader).close();
	}

	@Test
	public void close_IoExThrownClosing_ThrowsFileEx() throws IOException {
		expectFileExceptionContaining(IOException.class.getCanonicalName());
		givenIoExThrownWhenClosing();
		whenClose();
	}

	private void givenIoExThrownWhenClosing() throws IOException {
		Mockito.doThrow(new IOException()).when(reader).close();
	}

}
