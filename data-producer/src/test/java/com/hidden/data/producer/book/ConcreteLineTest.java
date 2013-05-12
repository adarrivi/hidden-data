package com.hidden.data.producer.book;

import org.junit.Assert;
import org.junit.Test;

public class ConcreteLineTest {

	private static final String LINE_CONTENT = "line content";
	private static final int BOOK_ID = 2;
	private static final int ROW = 1;
	private ConcreteLine<String> victim;

	@Test
	public void testCreation() {
		victim = new ConcreteLine<String>(ROW, BOOK_ID, LINE_CONTENT);
		Assert.assertNotNull(victim);
		Assert.assertEquals(ROW, victim.getRow());
		Assert.assertEquals(BOOK_ID, victim.getBookId());
		Assert.assertEquals(LINE_CONTENT, victim.getLineContent());
	}

}
