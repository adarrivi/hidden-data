package com.hidden.data.producer.book.text;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hidden.data.producer.TestObjectCreator;
import com.hidden.data.producer.book.Line;

public class TextBookTest {

	private static final int MAX_LINES_IN_CONENT = TestObjectCreator.BOOK_CONTENT.length;
	private TextBook victim;

	@Before
	public void setUp() {
		victim = TestObjectCreator.getInstance().createTextBook();
	}

	@Test
	public void readNoLinesReturnsEmpty() {
		List<Line<String>> lines = victim.readNextLines(0);
		Assert.assertTrue(lines.isEmpty());
	}

	@Test
	public void readNegativeLinesReturnsEmpty() {
		List<Line<String>> lines = victim.readNextLines(-4);
		Assert.assertTrue(lines.isEmpty());
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

}
