package com.hidden.data.monitor.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PerformanceHub {

	// ConcurrentHashMap could be an option, but in addExecution we do two
	// actions,find-if-exists and put, that should be done atomically.
	private Map<String, List<Long>> executionsMap = new HashMap<String, List<Long>>();

	public synchronized void addExecution(String executionId, long milliseconds) {
		List<Long> timesList = executionsMap.get(executionId);
		if (timesList == null) {
			timesList = new ArrayList<Long>();
			executionsMap.put(executionId, timesList);
		}
		timesList.add(Long.valueOf(milliseconds));
	}

	public List<Long> getExecutionTimeListById(String executionId) {
		Map<String, List<Long>> currentExecutionsMapCopy = getExecutionsMap();
		List<Long> executionTimes = currentExecutionsMapCopy.get(executionId);
		if (executionTimes == null) {
			return Collections.<Long> emptyList();
		}
		return executionTimes;
	}

	public Map<String, List<Long>> getExecutionsMap() {
		return Collections.unmodifiableMap(executionsMap);
	}
}
