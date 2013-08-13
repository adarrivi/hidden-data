package com.hidden.data.common.reflection;

import java.lang.reflect.InvocationTargetException;

interface MethodAccessor {

	Object invokeMethod(Object victim, String methodName, Object... args)
			throws IllegalAccessException, NoSuchMethodException,
			InvocationTargetException;

}
