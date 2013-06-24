package com.common.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;

import com.common.reflexion.Reflexion;

public class AccessorVerifier {

	private Object victim;
	private Map<String, AccessorField> getterList = new HashMap<String, AccessorField>();
	private Map<String, AccessorField> setterList = new HashMap<String, AccessorField>();

	public AccessorVerifier(Object victim) {
		this.victim = victim;
	}

	public void verifyDirectGetters() {
		for (Entry<String, AccessorField> getterEntry : getterList.entrySet()) {
			String getterMethodName = getterEntry.getKey();
			String fieldName = getterEntry.getValue().getName();
			Object expectedGetterResult = getterEntry.getValue().getValue();
			Reflexion.getInstance().setMember(victim, fieldName,
					expectedGetterResult);
			Object invokeResult = Reflexion.getInstance()
					.invokeAccessibleMethod(victim, getterMethodName);
			Assert.assertEquals(expectedGetterResult, invokeResult);
		}
	}

	public void addGetterToVerify(String getterName, String attributeName,
			Object value) {
		AccessorField accessorItem = new AccessorField(attributeName, value);
		getterList.put(getterName, accessorItem);
	}

	public void addSetterToVerify(String setterName, String attributeName,
			Object value) {
		AccessorField accessorItem = new AccessorField(attributeName, value);
		setterList.put(setterName, accessorItem);
	}

	public void verifyDirectSetters() {
		for (Entry<String, AccessorField> setterEntry : setterList.entrySet()) {
			String setterMethodName = setterEntry.getKey();
			String fieldName = setterEntry.getValue().getName();
			Object expectedValueInField = setterEntry.getValue().getValue();
			Reflexion.getInstance().invokeAccessibleMethod(victim,
					setterMethodName, expectedValueInField);
			Object fieldValue = Reflexion.getInstance().readField(victim,
					fieldName);
			Assert.assertEquals(expectedValueInField, fieldValue);
		}
	}

}
