package com.hidden.data.monitor.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

@Component
public class PerformanceHub {

	// ConcurrentHashMap could be an option, but in addExecution we do two
	// actions,find-if-exists and put, that should be done atomically.
	private Map<String, PerformanceElement> executionsMap = new HashMap<String, PerformanceElement>();

	public synchronized void addExecution(String executionId, long milliseconds) {
		PerformanceElement performanceElement = executionsMap.get(executionId);
		if (performanceElement == null) {
			performanceElement = new PerformanceElement();
			executionsMap.put(executionId, performanceElement);
		}
		performanceElement.addNewExecution(milliseconds);
	}

	public Map<String, PerformanceElement> getExecutionsMap() {
		return getIndependentCopyOfExecutionsMap();
	}

	private Map<String, PerformanceElement> getIndependentCopyOfExecutionsMap() {
		Map<String, PerformanceElement> copyExecMap = executionsMap;
		Map<String, PerformanceElement> mapToReturn = new HashMap<String, PerformanceElement>();
		for (Entry<String, PerformanceElement> entry : copyExecMap.entrySet()) {
			mapToReturn.put(entry.getKey(), entry.getValue().getCopy());
		}
		return mapToReturn;
	}
}
