package com.common.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public abstract class IteratorTestTemplate<T> {

	protected Iterator<T> victim;
	private boolean hasNext;
	private T currentItemRead;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void hasNext_EmptyContent_ReturnsFalse() throws Exception {
		givenEmptyContent();
		whenHasNext();
		thenHasNextShouldBe(false);
	}

	protected abstract void givenEmptyContent() throws Exception;

	protected void whenHasNext() {
		hasNext = victim.hasNext();
	}

	private void thenHasNextShouldBe(boolean value) {
		Assert.assertEquals(value, hasNext);
	}

	@Test
	public void hasNext_EmptyContentTwice_ReturnsFalse() throws Exception {
		givenEmptyContent();
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
		createVictim();
	}

	protected abstract void createVictim();

	protected void whenRemove() {
		victim.remove();
	}

	@Test
	public void next_SingleItemContent_ReturnsItem() throws Exception {
		givenSingleItemContent();
		whenNext();
		thenItemGivenShouldBe(getSingleContentItem());
	}

	protected abstract void givenSingleItemContent() throws Exception;

	protected void whenNext() {
		currentItemRead = victim.next();
	}

	private void thenItemGivenShouldBe(T item) {
		Assert.assertEquals(item, currentItemRead);
	}

	protected abstract T getSingleContentItem() throws Exception;

	@Test
	public void hasNext_SingleItemContentTwice_ReturnsTrue() throws Exception {
		givenSingleItemContent();
		whenHasNext();
		thenHasNextShouldBe(true);
		whenHasNext();
		thenHasNextShouldBe(true);
	}

	@Test
	public void hasNext_AfterNextInSingleItemContent_ReturnsFalse()
			throws Exception {
		givenSingleItemContent();
		whenNext();
		whenHasNext();
		thenHasNextShouldBe(false);
	}

	@Test
	public void next_AfterNextInSingleItemContent_ReturnsNull()
			throws Exception {
		givenSingleItemContent();
		whenNext();
		whenNext();
		thenItemGivenShouldBe(getNullItem());
	}

	protected T getNullItem() {
		return null;
	}

	@Test
	public void next_ReadAllItems_ReturnWholeContent() throws Exception {
		givenTwoItemsContent();
		List<T> multipleItemsContent = setTwoItemsContent();
		for (T item : multipleItemsContent) {
			whenNext();
			thenItemGivenShouldBe(item);
		}
	}

	protected abstract void givenTwoItemsContent() throws Exception;

	private List<T> setTwoItemsContent() throws Exception {
		List<T> multipleItems = new ArrayList<T>();
		multipleItems.add(getSingleContentItem());
		multipleItems.add(getSingleContentItem());
		return multipleItems;
	}

	@Test
	public void hasNext_ReadAllItems_ReturnFalse() throws Exception {
		givenTwoItemsContent();
		List<T> multipleItemsContent = setTwoItemsContent();
		for (int i = 0; i < multipleItemsContent.size(); i++) {
			whenNext();
		}
		thenHasNextShouldBe(false);
	}

}
