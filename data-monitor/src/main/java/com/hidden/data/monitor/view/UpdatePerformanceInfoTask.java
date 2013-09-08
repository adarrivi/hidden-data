package com.hidden.data.monitor.view;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;

import com.hidden.data.monitor.interceptor.PerformanceElement;
import com.hidden.data.monitor.interceptor.PerformanceHub;
import com.hidden.data.monitor.view.information.InformationPanel;

public class UpdatePerformanceInfoTask extends TimerTask {

	private InformationPanel informationPanel;
	private PerformanceHub performanceHub;

	private String currentExecutionId;
	private PerformanceElement currentPerformanceElement;
	private StringBuffer stringBuffer;

	public void setInformationPanel(InformationPanel informationPanel) {
		this.informationPanel = informationPanel;
	}

	public void setPerformanceHub(PerformanceHub performanceHub) {
		this.performanceHub = performanceHub;
	}

	@Override
	public void run() {
		Map<String, PerformanceElement> executionsMap = performanceHub
				.getExecutionsMap();
		stringBuffer = new StringBuffer();
		for (Entry<String, PerformanceElement> mapEntry : executionsMap
				.entrySet()) {
			currentExecutionId = mapEntry.getKey();
			currentPerformanceElement = mapEntry.getValue();
			addExecutionStringBuffer();
		}
		addStringBufferToDisplay();
	}

	private void addExecutionStringBuffer() {
		stringBuffer.append("Method id: ").append(currentExecutionId)
				.append("\n- Executions: ")
				.append(currentPerformanceElement.getExecutions())
				.append(", Avrg: ")
				.append(currentPerformanceElement.getAverageExecutionTime())
				.append("ms, Max: ")
				.append(currentPerformanceElement.getMaxExecutionTime())
				.append("ms, Min: ")
				.append(currentPerformanceElement.getMinExecutionTime())
				.append("ms\n");
	}

	private void addStringBufferToDisplay() {
		String stringToAdd = stringBuffer.toString();
		stringToAdd = StringUtils.removeEnd(stringToAdd, "\n");
		informationPanel.setDisplayText(stringToAdd);
	}

}
