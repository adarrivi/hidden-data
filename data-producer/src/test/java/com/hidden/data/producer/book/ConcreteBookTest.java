package com.hidden.data.producer.book;

import org.junit.Assert;
import org.junit.Test;

import com.hidden.data.producer.TestObjectCreator;

public class ConcreteBookTest {

	private ConcreteBook<String> victim = TestObjectCreator.getInstance()
			.createTextBook();

	@Test
	public void getTitle() {
		Assert.assertEquals(TestObjectCreator.BOOK_TITLE, victim.getTitle());
	}

	@Test
	public void getId() {
		Assert.assertEquals(TestObjectCreator.BOOK_ID, victim.getId());
	}

}
