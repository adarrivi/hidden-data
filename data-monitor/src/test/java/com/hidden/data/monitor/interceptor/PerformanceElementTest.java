package com.hidden.data.monitor.interceptor;

import org.junit.Assert;
import org.junit.Test;

public class PerformanceElementTest {

	private PerformanceElement victim;
	private long result;
	private double averageResult;
	private PerformanceElement copy;

	@Test
	public void getExecutions_Empty_ReturnsZero() {
		givenNoExecutions();
		whenGetExecutions();
		thenResultShouldBe(0);
	}

	private void givenNoExecutions() {
		createVictim();
	}

	private void createVictim() {
		victim = new PerformanceElement();
	}

	private void whenGetExecutions() {
		result = victim.getExecutions();
	}

	private void thenResultShouldBe(long expectedResult) {
		Assert.assertEquals(expectedResult, result);
	}

	@Test
	public void getMinExecutionTime_Empty_ReturnsMinusOne() {
		givenNoExecutions();
		whenGetMinExecutionTime();
		thenResultShouldBe(-1);
	}

	private void whenGetMinExecutionTime() {
		result = victim.getMinExecutionTime();
	}

	@Test
	public void getMaxExecutionTime_Empty_ReturnsMinusOne() {
		givenNoExecutions();
		whenGetMaxExecutionTime();
		thenResultShouldBe(-1);
	}

	private void whenGetMaxExecutionTime() {
		result = victim.getMaxExecutionTime();
	}

	@Test
	public void getAverageExecitopmTime_Empty_ReturnsMinusOne() {
		givenNoExecutions();
		whenGetAverageExecutionTime();
		thenAverageResultShouldBe(-1);
	}

	private void whenGetAverageExecutionTime() {
		averageResult = victim.getAverageExecutionTime();
	}

	private void thenAverageResultShouldBe(double expectedValue) {
		Assert.assertEquals(expectedValue, averageResult, 0);
	}

	@Test
	public void getExecutions_OneExec_ReturnsOne() {
		givenExecutions(10);
		whenGetExecutions();
		thenResultShouldBe(1);
	}

	private void givenExecutions(long... execTimes) {
		createVictim();
		for (long executionTime : execTimes) {
			victim.addNewExecution(executionTime);
		}
	}

	@Test
	public void getMinExecutionTime_OneExec_ReturnsExecTime() {
		givenExecutions(10);
		whenGetMinExecutionTime();
		thenResultShouldBe(10);
	}

	@Test
	public void getMaxExecutionTime_OneExec_ReturnsExecTime() {
		givenExecutions(10);
		whenGetMaxExecutionTime();
		thenResultShouldBe(10);
	}

	@Test
	public void getAverageExecutionTime_OneExec_ReturnsExecTime() {
		givenExecutions(10);
		whenGetAverageExecutionTime();
		thenAverageResultShouldBe(10);
	}

	@Test
	public void getExecutions_FiveExec_ReturnsFive() {
		givenExecutions(10, 10, 10, 10, 10);
		whenGetExecutions();
		thenResultShouldBe(5);
	}

	@Test
	public void getMinExecutionTime_FiveExec_ReturnsMin() {
		givenExecutions(10, 9, 4, 8, 7);
		whenGetMinExecutionTime();
		thenResultShouldBe(4);
	}

	@Test
	public void getMaxExecutionTime_FiveExec_ReturnsMax() {
		givenExecutions(10, 9, 4, 8, 7);
		whenGetMaxExecutionTime();
		thenResultShouldBe(10);
	}

	@Test
	public void getAverageExecutionTime_FiveExec_ReturnsAverage() {
		givenExecutions(10, 9, 4, 8, 7);
		whenGetAverageExecutionTime();
		thenAverageResultShouldBe(7.6);
	}

	@Test
	public void getCopy_HasSameContents() {
		givenExecutions(10);
		whenGetCopy();
		thenCopyShouldHaveSameContentThanVictim();
	}

	private void whenGetCopy() {
		copy = victim.getCopy();
	}

	private void thenCopyShouldHaveSameContentThanVictim() {
		Assert.assertEquals(victim.getExecutions(), copy.getExecutions());
		Assert.assertEquals(victim.getAverageExecutionTime(),
				copy.getAverageExecutionTime(), 0);
		Assert.assertEquals(victim.getMaxExecutionTime(),
				copy.getMaxExecutionTime());
		Assert.assertEquals(victim.getMinExecutionTime(),
				copy.getMinExecutionTime());
	}

	@Test
	public void getCopy_IsDifferentObject() {
		givenExecutions(10);
		whenGetCopy();
		thenCopyShouldBeDifferentObjectThanVictim();
	}

	private void thenCopyShouldBeDifferentObjectThanVictim() {
		Assert.assertFalse(copy == victim);
	}

}
