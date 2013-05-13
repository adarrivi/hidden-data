package com.hidden.data.producer.book.text;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.producer.TestObjectCreator;
import com.hidden.data.producer.file.FileBufferReader;

public class TextFileTest {

	@Mock
	private FileBufferReader reader;
	@InjectMocks
	private TextFile victim = new TextFile(reader);

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void readLineReturnsNull() {
		testReadLine(null);
	}

	private void testReadLine(String line) {
		Mockito.when(reader.readLine()).thenReturn(line);
		String firstLine = victim.readLine();
		Assert.assertEquals(line, firstLine);
		Mockito.verify(reader).readLine();
	}

	@Test
	public void readLineReturnsEmpty() {
		testReadLine(StringUtils.EMPTY);
	}

	@Test
	public void readLineReturnsContent() {
		testReadLine(TestObjectCreator.BOOK_CONTENT[0]);
	}

	@Test
	public void readWholeBookContent() {
		victim = TestObjectCreator.getInstance().createTextFile();
		assertBookContent(TestObjectCreator.BOOK_CONTENT);
		victim.closeFile();
	}

	@Test
	public void closeFileClosesReader() {
		victim.closeFile();
		Mockito.verify(reader).close();
	}

	private void assertBookContent(String[] bookContent) {
		int i = 0;
		String line = victim.readLine();
		while (line != null) {
			Assert.assertEquals(bookContent[i++], line);
			line = victim.readLine();
		}
		Assert.assertEquals(bookContent.length, i);
	}

}
