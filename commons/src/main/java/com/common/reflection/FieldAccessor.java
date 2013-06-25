package com.common.reflection;

import java.lang.reflect.Field;

interface FieldAccessor {

	Object readField(Object target, String fieldName, boolean forceAccess)
			throws IllegalAccessException;

	Field getField(final Class<?> cls, String fieldName, boolean forceAccess);
}
