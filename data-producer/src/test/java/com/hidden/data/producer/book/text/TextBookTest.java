package com.hidden.data.producer.book.text;

import java.util.BitSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.producer.TestObjectCreator;
import com.hidden.data.producer.book.Line;

public class TextBookTest {

	private static final int MAX_LINES_IN_CONENT = TestObjectCreator.BOOK_CONTENT.length;
	private TextBook victim;

	@Mock
	private TextFile mockedFile;
	@InjectMocks
	private TextBook mockedVictim = new TextBook(TestObjectCreator.BOOK_ID,
			TestObjectCreator.BOOK_TITLE, mockedFile);

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		victim = TestObjectCreator.getInstance().createTextBook();
	}

	@Test
	public void readNoLinesReturnsEmpty() {
		List<Line<String>> lines = victim.readNextLines(0);
		Assert.assertTrue(lines.isEmpty());
	}

	@Test
	public void readNegativeLinesReadsWholeFile() {
		List<Line<String>> lines = victim.readNextLines(-1);
		Assert.assertEquals(MAX_LINES_IN_CONENT, lines.size());
	}

	@Test
	public void readOnlyOneLine() {
		int linesToRead = 1;
		List<Line<String>> lines = victim.readNextLines(linesToRead);
		Assert.assertEquals(linesToRead, lines.size());
	}

	@Test
	public void readLessLinesThanTheFullContent() {
		int linesToRead = MAX_LINES_IN_CONENT - 3;
		List<Line<String>> lines = victim.readNextLines(linesToRead);
		Assert.assertEquals(linesToRead, lines.size());
	}

	@Test
	public void readFullContent() {
		int linesToRead = MAX_LINES_IN_CONENT;
		List<Line<String>> lines = victim.readNextLines(linesToRead);
		Assert.assertEquals(MAX_LINES_IN_CONENT, lines.size());
	}

	@Test
	public void readMoreLinesReturnsMaximunContent() {
		int linesToRead = MAX_LINES_IN_CONENT * 2;
		List<Line<String>> lines = victim.readNextLines(linesToRead);
		Assert.assertEquals(MAX_LINES_IN_CONENT, lines.size());
	}

	@Test
	public void transformEmptyFileReturnsEmptyList() {
		Mockito.when(mockedFile.readLine()).thenReturn(null);
		List<Line<BitSet>> transformedBook = mockedVictim.transformBook();
		Assert.assertNotNull(transformedBook);
		Assert.assertTrue(transformedBook.isEmpty());
		Mockito.verify(mockedFile).readLine();
	}

}
