package com.hidden.data.monitor.interceptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class PerformanceHubTest {

	private static final long EXECUTION_TIME = 1000;
	private static final long EXECUTION_TIME2 = 1500;
	private static final String EXECUTION_ID = "executionId";
	private static final String EXECUTION_ID2 = "executionId2";
	private static final String NOT_EXISTING_EXECUTION_ID = "notExistingId";
	private PerformanceHub victim;
	private List<Long> executionTimeList;
	private String executionId;
	private String prettyPrint;

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
	public void prettyPrint_Empty_ReturnsEmptyString() {
		givenEmptyHub();
		whenPrettyPrint();
		thenPrettyPrintShouldBe(StringUtils.EMPTY);
	}

	private void whenPrettyPrint() {
		prettyPrint = victim.prettyPrint();
	}

	private void thenPrettyPrintShouldBe(String expectedString) {
		Assert.assertEquals(expectedString, prettyPrint);
	}

	@Test
	public void prettyPrint_2ExecutionsSameId_Returns1Line() {
		givenExistingIdOneExecution();
		whenAddExecution_Standard();
		whenAddExecution_Standard();
		whenPrettyPrint();
		thenPrettyPrintShouldBe(getPrintLine(EXECUTION_ID, 2, EXECUTION_TIME,
				true));
	}

	private String getPrintLine(String executionId, int timesExecuted,
			long executionTime, boolean endLine) {
		StringBuffer sb = new StringBuffer();
		sb.append("Process ").append(executionId).append(", times executed: ")
				.append(timesExecuted).append(", average time: ")
				.append(executionTime).append("ms.");
		if (!endLine) {
			sb.append("\n");
		}
		return sb.toString();
	}

	@Test
	public void prettyPrint_2ExecutionsSameIdDifferentTime_Returns1LineAverage() {
		givenExistingIdOneExecution();
		whenAddExecution_Standard();
		whenAddExecution(EXECUTION_ID, EXECUTION_TIME2);
		whenPrettyPrint();
		thenPrettyPrintShouldBe(getPrintLine(EXECUTION_ID, 2,
				((EXECUTION_TIME + EXECUTION_TIME2) / 2), true));
	}

	@Test
	public void prettyPrint_2ExecutionsDifferentId_Returns2Lines() {
		givenExistingIdOneExecution();
		whenAddExecution_Standard();
		whenAddExecution(EXECUTION_ID2, EXECUTION_TIME);
		whenPrettyPrint();
		String line1 = getPrintLine(EXECUTION_ID, 1, EXECUTION_TIME, false);
		String line2 = getPrintLine(EXECUTION_ID2, 1, EXECUTION_TIME, true);
		thenPrettyPrintShouldBe(line1 + line2);
	}

	// TODO Test some concurrency

}
