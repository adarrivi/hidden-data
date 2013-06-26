package com.common.reflection;

import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Test;

public class MethodAccessorApacheTest {

	private MethodAccessor victim = new MethodAccessorApache();
	private static final String FIELD_VALUE = "value";
	private static final String METHOD_NAME = "getName";
	private StubObject target = new StubObject(FIELD_VALUE);
	private Object valueResult;

	@Test
	public void invokeMethod_ReturnsMethodValue() throws Throwable,
			NoSuchMethodException, InvocationTargetException {
		whenInvokeMethod();
		thenReturnedValueShouldBe(FIELD_VALUE);
	}

	private void whenInvokeMethod() throws IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		valueResult = victim.invokeMethod(target, METHOD_NAME);
	}

	private void thenReturnedValueShouldBe(Object expectedValue) {
		Assert.assertEquals(expectedValue, valueResult);
	}
}
