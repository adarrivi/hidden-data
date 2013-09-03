package com.hidden.data.web.service.impl;

import java.util.ArrayList;
import java.util.List;

class PercentageList {

	private List<Integer> valueList;
	private List<Integer> pList;
	private double sumOfAllValues;
	private int numberOfValues;

	PercentageList(List<Integer> anyValuesList) {
		this.valueList = anyValuesList;
	}

	public List<Integer> getPercentaceList() {
		setupPercentageListIfNotInitiated();
		return pList;
	}

	private void setupPercentageListIfNotInitiated() {
		if (pList != null) {
			return;
		}
		setupPList();
	}

	private void setupPList() {
		for (Integer value : valueList) {
			sumOfAllValues += value.intValue();
		}
		numberOfValues = valueList.size();
		setupPercentagesInPList();
	}

	private void setupPercentagesInPList() {
		pList = new ArrayList<Integer>();
		for (int i = 0; i < numberOfValues; i++) {
			pList.add(getPercentageValue(valueList.get(i)));
		}
	}

	private int getPercentageValue(int value) {
		if (value == 0) {
			return 0;
		}
		double percentage = value / sumOfAllValues * 100;
		return Double.valueOf(percentage).intValue();
	}

}
