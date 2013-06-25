package com.common.reflection;

import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.FieldUtils;

class FieldAccessorApache implements FieldAccessor {

	@Override
	public Object readField(Object target, String fieldName, boolean forceAccess)
			throws IllegalAccessException {
		return FieldUtils.readField(target, fieldName, forceAccess);
	}

	@Override
	public Field getField(Class<?> cls, String fieldName, boolean forceAccess) {
		return FieldUtils.getField(cls, fieldName, forceAccess);
	}

}
