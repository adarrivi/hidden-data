package com.hidden.data.producer.book.text;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hidden.data.producer.book.Line;

public class TextBookTest {

	private TextBook victim;

	@Before
	public void setUp() {
		victim = new TextBook();
	}

	@Test
	public void read0LinesReturnsEmpty() {
		List<Line<String>> lines = victim.readNextLines(0);
		Assert.assertTrue(lines.isEmpty());
	}

}
