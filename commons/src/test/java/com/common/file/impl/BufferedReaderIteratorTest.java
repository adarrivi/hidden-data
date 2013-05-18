package com.common.file.impl;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.common.file.FileException;
import com.common.file.FileLineIterator;

public class BufferedReaderIteratorTest {

	private static final String FIRST_LINE = "first line";
	private static final String SECOND_LINE = "";
	private static final String THRID_LINE = "third line";
	private FileLineIterator<String> victim;
	private boolean hasNext;
	private String currentLineReaded;
	@Mock
	private BufferedReader reader;
	@Mock
	private RelativeBufferedReader bufferedReaderProvider;
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() throws IOException {
		MockitoAnnotations.initMocks(this);
		givenSingleLineFile();
	}

	@Test
	public void hasNext_EmptyFile_ReturnsFalse() throws IOException {
		givenEmptyFile();
		whenHasNext();
		thenHasNextShouldBe(false);
	}

	private void givenEmptyFile() throws IOException {
		Mockito.when(reader.readLine()).thenReturn(null);
		victim = new BufferedReaderIterator(reader);
	}

	private void whenHasNext() {
		hasNext = victim.hasNext();
	}

	private void thenHasNextShouldBe(boolean value) throws IOException {
		Assert.assertEquals(value, hasNext);
		Mockito.verify(reader, Mockito.atLeast(1)).readLine();
	}

	@Test
	public void hasNext_EmptyFileTwice_ReturnsFalse() throws IOException {
		givenEmptyFile();
		whenHasNext();
		thenHasNextShouldBe(false);
		whenHasNext();
		thenHasNextShouldBe(false);
	}

	@Test
	public void remove_ThrowsUnsuported() {
		expectUnsupportedEx();
		whenRemove();
	}

	private void expectUnsupportedEx() {
		expectedException.expect(UnsupportedOperationException.class);
	}

	private void whenRemove() {
		victim.remove();
	}

	@Test
	public void next_SingleLineFile_ReturnsFirstLine() throws IOException {
		givenSingleLineFile();
		whenNext();
		thenLineGivenShouldBe(FIRST_LINE);
	}

	private void givenSingleLineFile() throws IOException {
		Mockito.when(reader.readLine()).thenReturn(FIRST_LINE, (String) null);
		victim = new BufferedReaderIterator(reader);
	}

	private void whenNext() {
		currentLineReaded = victim.next();
	}

	private void thenLineGivenShouldBe(String line) {
		Assert.assertEquals(line, currentLineReaded);
	}

	@Test
	public void hasNext_TwiceInSingleLineFile_ReturnsTrue() throws IOException {
		givenSingleLineFile();
		whenHasNext();
		thenHasNextShouldBe(true);
		whenHasNext();
		thenHasNextShouldBe(true);
	}

	@Test
	public void hasNext_AfterNextInSingleLineFile_ReturnsFalse()
			throws IOException {
		givenSingleLineFile();
		whenNext();
		whenHasNext();
		thenHasNextShouldBe(false);
	}

	@Test
	public void next_AfterNextInSingleLineFile_ReturnsNull() throws IOException {
		givenSingleLineFile();
		whenNext();
		whenNext();
		thenLineGivenShouldBe(null);
	}

	@Test
	public void next_ReadAllLinesInMultipleLineFile_ReturnsFileContent()
			throws IOException {
		givenMultipleLineFile();
		whenNext();
		thenLineGivenShouldBe(FIRST_LINE);
		whenNext();
		thenLineGivenShouldBe(SECOND_LINE);
		whenNext();
		thenLineGivenShouldBe(THRID_LINE);
	}

	@Test
	public void hasNext_AfterReadAllLinesInMultipleLineFile_ReturnsFalse()
			throws IOException {
		givenMultipleLineFile();
		whenNext();
		whenNext();
		whenNext();
		whenHasNext();
		thenHasNextShouldBe(false);
	}

	private void givenMultipleLineFile() throws IOException {
		Mockito.when(reader.readLine()).thenReturn(FIRST_LINE, SECOND_LINE,
				THRID_LINE, null);
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
		givenSingleLineFile();
		whenClose();
		thenReaderShouldBeClosed();
	}

	private void whenClose() {
		victim.close();
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
