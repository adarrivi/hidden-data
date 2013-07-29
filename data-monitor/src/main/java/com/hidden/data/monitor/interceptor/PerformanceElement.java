package com.hidden.data.monitor.interceptor;

public class PerformanceElement {

	private long executions;
	private long minExecutionTime = -1;
	private long maxExecutionTime = -1;
	private double averageExecutionTime = -1;
	private long currentExecutionTime;

	public void addNewExecution(long executionTimeMs) {
		currentExecutionTime = executionTimeMs;
		executions++;
		updateMinExecutionTime();
		updateMaxExecutionTime();
		updateAverageExecutionTime();
	}

	private void updateMinExecutionTime() {
		if ((minExecutionTime == -1)
				|| (minExecutionTime > currentExecutionTime)) {
			minExecutionTime = currentExecutionTime;
		}
	}

	private void updateMaxExecutionTime() {
		if (currentExecutionTime > maxExecutionTime) {
			maxExecutionTime = currentExecutionTime;
		}
	}

	private void updateAverageExecutionTime() {
		if (averageExecutionTime == -1) {
			averageExecutionTime = currentExecutionTime;
		} else {
			averageExecutionTime *= executions - 1;
			averageExecutionTime += currentExecutionTime;
			averageExecutionTime /= executions;
		}
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

	public double getAverageExecutionTime() {
		return averageExecutionTime;
	}

	protected PerformanceElement getCopy() {
		PerformanceElement copy = new PerformanceElement();
		copy.executions = this.executions;
		copy.averageExecutionTime = this.averageExecutionTime;
		copy.minExecutionTime = this.minExecutionTime;
		copy.maxExecutionTime = this.maxExecutionTime;
		return copy;
	}

}
