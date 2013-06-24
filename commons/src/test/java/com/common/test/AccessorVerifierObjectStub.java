package com.common.test;

public class AccessorVerifierObjectStub {

	private String stringField;
	private int intField;

	AccessorVerifierObjectStub() {
	}

	public String getStringField() {
		return stringField;
	}

	public String getDuplicatedStringField() {
		return getStringField() + getStringField();
	}

	public int getIntField() {
		return intField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	public void setIntField(int intField) {
		this.intField = intField;
	}

	public void setDuplicatedStringField(String stringValue) {
		this.stringField = stringValue + stringValue;
	}

}
