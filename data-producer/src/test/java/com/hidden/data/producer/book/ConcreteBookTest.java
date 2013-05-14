package com.hidden.data.producer.book;

import org.junit.Assert;
import org.junit.Test;

import com.hidden.data.producer.TestObjectFactory;

public class ConcreteBookTest {

	private ConcreteBook<String> victim = TestObjectFactory.getInstance()
			.createTextBook();

	@Test
	public void getTitle() {
		Assert.assertEquals(TestObjectFactory.BOOK_TITLE, victim.getTitle());
	}

	@Test
	public void getId() {
		Assert.assertEquals(TestObjectFactory.BOOK_ID, victim.getId());
	}

}
