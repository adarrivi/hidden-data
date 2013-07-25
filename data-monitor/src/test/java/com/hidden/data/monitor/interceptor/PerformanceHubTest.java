package com.hidden.data.monitor.interceptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PerformanceHubTest {

	private static final long EXECUTION_TIME = 1000;
	private static final String EXECUTION_ID = "executionId";
	private static final String NOT_EXISTING_EXECUTION_ID = "notExistingId";
	private PerformanceHub victim;
	private List<Long> executionTimeList;
	private String executionId;
	private Map<String, List<Long>> executionsMap;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

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
		whenAddExecution_Standard();
	}

	private void whenAddExecution_Standard() {
		whenAddExecution(EXECUTION_ID, EXECUTION_TIME);
	}

	private void whenAddExecution(String execId, long executionTime) {
		victim.addExecution(execId, executionTime);
	}

	@Test
	public void getExecutionTimeListById_ExistingId_ReturnsExecutionTime() {
		givenExistingIdOneExecution();
		whenAddExecution_Standard();
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
		whenAddExecution_Standard();
		whenAddExecution_Standard();
		whenGetExecutionTimeListById();
		thenExecutionTimeListShouldBe(Arrays.asList(EXECUTION_TIME,
				EXECUTION_TIME));
	}

	@Test
	public void getExecutionsMap_Empty_ReturnsEmptyMap() {
		givenEmptyHub();
		whenGetExecutionsMap();
		thenExecutionsMapShouldBeEmpty();
	}

	private void whenGetExecutionsMap() {
		executionsMap = victim.getExecutionsMap();
	}

	private void thenExecutionsMapShouldBeEmpty() {
		Assert.assertTrue(executionsMap.isEmpty());
	}

	@Test
	public void getExecutionsMap_Twice_ReturnsDifferentObject() {
		givenExistingIdOneExecution();
		whenAddExecution(EXECUTION_ID, EXECUTION_TIME);
		whenGetExecutionsMap();
		Map<String, List<Long>> previousExecution = executionsMap;
		whenGetExecutionsMap();
		thenExecutionMapObjectShouldBeDifferentTo(previousExecution);
	}

	private void thenExecutionMapObjectShouldBeDifferentTo(
			Map<String, List<Long>> map) {
		Assert.assertFalse(map == executionsMap);
	}

	@Test
	public void getExecutionsMap_ReturnsUnmodifiableMap() {
		expectedException.expect(UnsupportedOperationException.class);
		givenExistingIdOneExecution();
		whenAddExecution(EXECUTION_ID, EXECUTION_TIME);
		whenGetExecutionsMap();
		whenModifyingResultMap();
	}

	private void whenModifyingResultMap() {
		executionsMap.put(EXECUTION_ID,
				Collections.singletonList(EXECUTION_TIME));
	}

	// TODO Test some concurrency

}
