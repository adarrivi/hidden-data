package com.common.reflection;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.reflect.MethodUtils;

class MethodAccessorApache implements MethodAccessor {

	@Override
	public Object invokeMethod(Object victim, String methodName, Object... args)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return MethodUtils.invokeMethod(victim, methodName, args);
	}

}
