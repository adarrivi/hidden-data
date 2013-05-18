package com.hidden.data.producer.book;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import com.hidden.data.producer.util.TestObjectFactory;

public class ConcreteBookTest {

	private Book<String> victim;
	private String title;
	private int id;
	private Iterator<Line<String>> iterator;

	@Test
	public void getTitle_ReturnsTitle() {
		givenABook();
		whenGetTitle();
		thenExpectBookTitle();
	}

	private void givenABook() {
		victim = TestObjectFactory.getInstance().createTextBook();
	}

	private void whenGetTitle() {
		title = victim.getTitle();
	}

	private void thenExpectBookTitle() {
		Assert.assertEquals(TestObjectFactory.BOOK_TITLE, title);
	}

	@Test
	public void getId_ReturnsId() {
		givenABook();
		whenGetId();
		thenExpectBookId();
	}

	private void whenGetId() {
		id = victim.getId();
	}

	private void thenExpectBookId() {
		Assert.assertEquals(TestObjectFactory.BOOK_ID, id);
	}

	@Test
	public void iterator_ReturnsIterator() {
		givenABook();
		whenIterator();
		thenIteratorShouldNotBeNull();
	}

	private void whenIterator() {
		iterator = victim.iterator();
	}

	private void thenIteratorShouldNotBeNull() {
		Assert.assertNotNull(iterator);
	}
}
