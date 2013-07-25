package com.hidden.data.monitor.view;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;

import com.hidden.data.monitor.interceptor.PerformanceHub;

public class UpdatePerformanceInfoTask extends TimerTask {

	private InformationPanel informationPanel;
	private PerformanceHub performanceHub;

	private String currentExecutionId;
	private List<Long> currentExecutionTimesList;
	private StringBuffer stringBuffer;

	public void setInformationPanel(InformationPanel informationPanel) {
		this.informationPanel = informationPanel;
	}

	public void setPerformanceHub(PerformanceHub performanceHub) {
		this.performanceHub = performanceHub;
	}

	@Override
	public void run() {
		Map<String, List<Long>> executionsMap = performanceHub
				.getExecutionsMap();
		stringBuffer = new StringBuffer();
		for (Entry<String, List<Long>> mapEntry : executionsMap.entrySet()) {
			currentExecutionId = mapEntry.getKey();
			currentExecutionTimesList = mapEntry.getValue();
			Collections.sort(currentExecutionTimesList);
			addExecutionStringBuffer();
		}
		addStringBufferToDisplay();
	}

	private void addExecutionStringBuffer() {
		stringBuffer.append(currentExecutionId).append(", Exec. Number: ")
				.append(getExecutionsNumber()).append(", Avrg: ")
				.append(getAverageExecutionTime()).append("ms, Max: ")
				.append(getMaxExecutionTime()).append("ms, Min: ")
				.append(getMinExecutionTime()).append("ms\n");
	}

	private long getExecutionsNumber() {
		return currentExecutionTimesList.size();
	}

	private long getAverageExecutionTime() {
		long total = 0;
		for (Long executionTime : currentExecutionTimesList) {
			total += executionTime.longValue();
		}
		return total / getExecutionsNumber();
	}

	private long getMaxExecutionTime() {
		return currentExecutionTimesList
				.get(currentExecutionTimesList.size() - 1);
	}

	private long getMinExecutionTime() {
		return currentExecutionTimesList.get(0);
	}

	private void addStringBufferToDisplay() {
		String stringToAdd = stringBuffer.toString();
		stringToAdd = StringUtils.removeEnd(stringToAdd, "\n");
		informationPanel.setDisplayText(stringToAdd);
	}

}
