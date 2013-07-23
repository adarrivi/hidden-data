package com.hidden.data.monitor.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
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
		Map<String, List<Long>> currentExecutionsMapCopy = getCurrentExecutionsMapCopy();
		List<Long> executionTimes = currentExecutionsMapCopy.get(executionId);
		if (executionTimes == null) {
			return Collections.<Long> emptyList();
		}
		return executionTimes;
	}

	private Map<String, List<Long>> getCurrentExecutionsMapCopy() {
		return Collections.unmodifiableMap(executionsMap);
	}

	public String prettyPrint() {
		StringBuffer sb = new StringBuffer();
		for (Entry<String, List<Long>> executionEntry : executionsMap
				.entrySet()) {
			List<Long> executionTimes = executionEntry.getValue();
			sb.append("Process ").append(executionEntry.getKey())
					.append(", times executed: ").append(executionTimes.size())
					.append(", average time: ")
					.append(getAverageTime(executionTimes)).append("ms.")
					.append("\n");
		}
		String prettyPrint = sb.toString();
		return StringUtils.removeEnd(prettyPrint, "\n");
	}

	private long getAverageTime(List<Long> executionTimes) {
		long total = 0;
		for (Long executionTime : executionTimes) {
			total += executionTime.longValue();
		}
		return total / executionTimes.size();
	}
}
