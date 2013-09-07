package com.hidden.data.web.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PercentageListTest {

	private PercentageList victim;
	private List<Integer> percentageList;

	@Test
	public void getPercentageList_Empty_ReturnEmpty() {
		givenEmptyPercentaceList();
		whenGetPercentagetList();
		thenPListShouldBeEmpty();
	}

	private void givenEmptyPercentaceList() {
		victim = new PercentageList(Collections.<Integer> emptyList());
	}

	private void whenGetPercentagetList() {
		percentageList = victim.getPercentaceList();
	}

	private void thenPListShouldBeEmpty() {
		Assert.assertTrue(percentageList.isEmpty());
	}

	@Test
	public void getPercentageList_0Value_Returns0() {
		givenPListWithValues(0);
		whenGetPercentagetList();
		thenPListShouldBe(0);
	}

	private void givenPListWithValues(Integer... values) {
		victim = new PercentageList(Arrays.asList(values));
	}

	private void thenPListShouldBe(Integer... values) {
		Assert.assertEquals(Arrays.asList(values), percentageList);
	}

	@Test
	public void getPercentageList_1Value_Returns100() {
		givenPListWithValues(1);
		whenGetPercentagetList();
		thenPListShouldBe(100);
	}

	@Test
	public void getPercentageList_0_0Value_Returns0() {
		givenPListWithValues(0, 0);
		whenGetPercentagetList();
		thenPListShouldBe(0, 0);
	}

	@Test
	public void getPercentageList_2Value_Returns100() {
		givenPListWithValues(2);
		whenGetPercentagetList();
		thenPListShouldBe(100);
	}

	@Test
	public void getPercentageList_2_1Value_Returns66_33() {
		givenPListWithValues(2, 1);
		whenGetPercentagetList();
		thenPListShouldBe(66, 33);
	}

	@Test
	public void getPercentageList_2_1_1Value_Returns50_25_25() {
		givenPListWithValues(2, 1, 1);
		whenGetPercentagetList();
		thenPListShouldBe(50, 25, 25);
	}

	@Test
	public void getPercentageList_1_1_1_0Value_Returns50_25_25() {
		givenPListWithValues(1, 1, 1, 0);
		whenGetPercentagetList();
		thenPListShouldBe(33, 33, 33, 0);
	}

	@Test
	public void getPercentageList_Twice_ReturnsSameValue() {
		int expectedValue = 100;
		givenPListWithValues(1);
		whenGetPercentagetList();
		thenPListShouldBe(expectedValue);
		whenGetPercentagetList();
		thenPListShouldBe(expectedValue);
	}

}
