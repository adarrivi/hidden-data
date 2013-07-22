package com.hidden.data.monitor.interceptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PerformanceHubTest {

	private static final long EXECUTION_TIME = 1000;
	private static final String EXECUTION_ID = "executionId";
	private static final String NOT_EXISTING_EXECUTION_ID = "notExistingId";
	private PerformanceHub victim;
	private List<Long> executionTimeList;
	private String executionId;

	@Test
	public void getExecutionTimeListById_Empty_ReturnsEmptyCollection() {
		givenEmptyHub();
		whenGetExecutionTimeListById();
		thenExecutionTimeListShouldBe(Collections.<Long> emptyList());
	}

	private void givenEmptyHub() {
		createVictim();
		executionId = EXECUTION_ID;
	}

	private void createVictim() {
		victim = new PerformanceHub();
	}

	private void whenGetExecutionTimeListById() {
		executionTimeList = victim.getExecutionTimeListById(executionId);
	}

	private void thenExecutionTimeListShouldBe(List<Long> expectedList) {
		Assert.assertEquals(expectedList, executionTimeList);
	}

	@Test
	public void getExecutionTimeListById_NotExistingId_ReturnsEmptyCollection() {
		givenNotExistingIdOneExecution();
		whenGetExecutionTimeListById();
		thenExecutionTimeListShouldBe(Collections.<Long> emptyList());
	}

	private void givenNotExistingIdOneExecution() {
		createVictim();
		executionId = NOT_EXISTING_EXECUTION_ID;
		whenAddExecution(EXECUTION_ID);
	}

	private void whenAddExecution(String execId) {
		victim.addExecution(execId, EXECUTION_TIME);
	}

	@Test
	public void getExecutionTimeListById_ExistingId_ReturnsExecutionTime() {
		givenExistingIdOneExecution();
		whenAddExecution(EXECUTION_ID);
		whenGetExecutionTimeListById();
		thenExecutionTimeListShouldBe(Collections.singletonList(EXECUTION_TIME));
	}

	private void givenExistingIdOneExecution() {
		createVictim();
		executionId = EXECUTION_ID;
	}

	@Test
	public void getExecutionTimeListById_2Executions_Returns2ExecutionTimes() {
		givenExistingIdOneExecution();
		whenAddExecution(EXECUTION_ID);
		whenAddExecution(EXECUTION_ID);
		whenGetExecutionTimeListById();
		thenExecutionTimeListShouldBe(Arrays.asList(EXECUTION_TIME,
				EXECUTION_TIME));
	}

	// TODO Test some concurrency

}
