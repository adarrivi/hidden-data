package com.hidden.data.monitor.interceptor;

public class PerformanceElement {

	private long executions;
	private long minExecutionTime = -1;
	private long maxExecutionTime;
	private long averageExecutionTime = -1;
	private long currentExecutionTime;

	public void addNewExecution(long executionTimeMs) {
		currentExecutionTime = executionTimeMs;
		executions++;
	}

	public long getExecutions() {
		return executions;
	}

	public long getMinExecutionTime() {
		return minExecutionTime;
	}

	public long getMaxExecutionTime() {
		return maxExecutionTime;
	}

	public long getAverageExecitopmTime() {
		return averageExecutionTime;
	}

}
