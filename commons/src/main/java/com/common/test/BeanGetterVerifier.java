package com.common.test;

import java.util.HashMap;
import java.util.Map;

public class BeanGetterVerifier {

	private Object victim;
	private Map<String, Object> attributeMap = new HashMap<String, Object>();

	public BeanGetterVerifier(Object victim, Map<String, Object> attributeMap) {
		this.attributeMap = attributeMap;
		this.victim = victim;
	}

	public void verify() {

	}

}
