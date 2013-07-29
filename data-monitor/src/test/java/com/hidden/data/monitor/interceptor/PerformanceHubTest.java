package com.hidden.data.monitor.interceptor;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class PerformanceHubTest {

	private static final long EXECUTION_TIME = 1000;
	private static final long DIFFERENT_EXECUTION_TIME = 500;
	private static final String EXECUTION_ID = "executionId";
	private PerformanceHub victim;
	private Map<String, PerformanceElement> executionsMap;

	@Test
	public void getExecutionsMap_Empty_ReturnsEmpty() {
		givenEmptyHub();
		whenGetExecutionsMap();
		thenMapShouldBeEmpty();
	}

	private void givenEmptyHub() {
		createVictim();
	}

	private void createVictim() {
		victim = new PerformanceHub();
	}

	private void whenGetExecutionsMap() {
		executionsMap = victim.getExecutionsMap();
	}

	private void thenMapShouldBeEmpty() {
		Assert.assertTrue(executionsMap.isEmpty());
	}

	@Test
	public void getExecutionsMap_OneId_ContainsId() {
		givenHubWithIds(EXECUTION_ID);
		whenGetExecutionsMap();
		thenMapShouldContainIds(EXECUTION_ID);
	}

	private void thenMapShouldContainIds(String... ids) {
		for (String id : ids) {
			Assert.assertTrue(executionsMap.containsKey(id));
			Assert.assertNotNull(executionsMap.get(id));
		}
	}

	private void givenHubWithIds(String... ids) {
		createVictim();
		for (String id : ids) {
			victim.addExecution(id, EXECUTION_TIME);
		}
	}

	@Test
	public void getExecutionsMap_DuplicatedId_ContainsId() {
		givenHubWithIds(EXECUTION_ID, EXECUTION_ID);
		whenGetExecutionsMap();
		thenMapShouldContainIds(EXECUTION_ID);
	}

	@Test
	public void getExecutionsMap_FiveIds_ContainsIds() {
		givenHubWithIds(EXECUTION_ID, "id2", "id3", "id4", "id5");
		whenGetExecutionsMap();
		thenMapShouldContainIds(EXECUTION_ID, "id2", "id3", "id4", "id5");
	}

	@Test
	public void getExecutionsMap_Twice_ReturnsDifferentObject() {
		givenHubWithIds(EXECUTION_ID);
		whenGetExecutionsMap();
		Map<String, PerformanceElement> firstMap = executionsMap;
		whenGetExecutionsMap();
		thenMapShouldBeDifferntObjectThan(firstMap);
	}

	private void thenMapShouldBeDifferntObjectThan(
			Map<String, PerformanceElement> firstMap) {
		Assert.assertTrue(firstMap != executionsMap);
	}

	@Test
	public void getExecutionsMap_modifyValue_DoesntAffectMap() {
		givenHubWithIds(EXECUTION_ID);
		whenGetExecutionsMap();
		Map<String, PerformanceElement> firstMap = executionsMap;
		firstMap.get(EXECUTION_ID).addNewExecution(DIFFERENT_EXECUTION_TIME);
		whenGetExecutionsMap();
		Assert.assertTrue(executionsMap.get(EXECUTION_ID).getExecutions() == 1);
	}

	// TODO Test some concurrency

}
