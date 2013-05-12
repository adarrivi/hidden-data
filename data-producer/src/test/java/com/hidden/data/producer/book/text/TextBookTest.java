package com.hidden.data.producer.book.text;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hidden.data.producer.TestObjectCreator;
import com.hidden.data.producer.book.Line;

public class TextBookTest {

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
	public void readLessLinesThanTheFullContent() {
		List<Line<String>> lines = victim.readNextLines(5);
		Assert.assertEquals(5, lines.size());
	}

}
