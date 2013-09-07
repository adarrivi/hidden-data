package com.hidden.data.common.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Reflection {

	private static final Reflection INSTANCE = new Reflection();

	private MethodAccessor methodAccessor;
	private FieldAccessor fieldAccessor;

	private Reflection() {
		methodAccessor = new MethodAccessorApache();
		fieldAccessor = new FieldAccessorApache();
	}

	public static final Reflection getInstance() {
		return INSTANCE;
	}

	protected void setMethodAccessor(MethodAccessor methodAccessor) {
		this.methodAccessor = methodAccessor;
	}

	protected void setFieldAccessor(FieldAccessor fieldAccessor) {
		this.fieldAccessor = fieldAccessor;
	}

	public void setField(Object victim, String fieldName, Object value) {
		try {
			setMemberWithValue(victim, fieldName, value);
		} catch (SecurityException e) {
			throw new ReflectionException(e);
		} catch (IllegalArgumentException e) {
			throw new ReflectionException(e);
		} catch (IllegalAccessException e) {
			throw new ReflectionException(e);
		}
	}

	private void setMemberWithValue(Object victim, String fieldName,
			Object value) throws SecurityException, IllegalArgumentException,
			IllegalAccessException {
		Field field = fieldAccessor
				.getField(victim.getClass(), fieldName, true);
		if (field == null) {
			throw new ReflectionException("Field not found: " + fieldName);
		}
		field.setAccessible(true);
		field.set(victim, value);
	}

	public Object invokeAccessibleMethod(Object victim, String methodName,
			Object... args) {
		try {
			return methodAccessor.invokeMethod(victim, methodName, args);
		} catch (NoSuchMethodException e) {
			throw new ReflectionException(e);
		} catch (IllegalAccessException e) {
			throw new ReflectionException(e);
		} catch (InvocationTargetException e) {
			throw new ReflectionException(e);
		}
	}

	public Object readField(Object victim, String fieldName) {
		try {
			return fieldAccessor.readField(victim, fieldName, true);
		} catch (IllegalAccessException e) {
			throw new ReflectionException(e);
		} catch (IllegalArgumentException e) {
			throw new ReflectionException(e);
		}
	}
}
