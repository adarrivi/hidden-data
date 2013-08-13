package com.hidden.data.common.test;

class AccessorField {

	private String name;
	private Object value;

	AccessorField(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	String getName() {
		return name;
	}

	Object getValue() {
		return value;
	}

}
