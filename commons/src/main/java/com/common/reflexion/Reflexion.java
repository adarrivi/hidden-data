package com.common.reflexion;

import java.lang.reflect.Field;

public class Reflexion {

	private static final Reflexion INSTANCE = new Reflexion();

	private Reflexion() {
		// to limit scope
	}

	public static final Reflexion getInstance() {
		return INSTANCE;
	}

	public void setMember(Object victim, String fieldName, Object value) {
		try {
			setMemberWithValue(victim, fieldName, value);
		} catch (SecurityException e) {
			throw new ReflexionException(e);
		} catch (NoSuchFieldException e) {
			throw new ReflexionException(e);
		} catch (IllegalArgumentException e) {
			throw new ReflexionException(e);
		} catch (IllegalAccessException e) {
			throw new ReflexionException(e);
		}
	}

	private void setMemberWithValue(Object victim, String fieldName,
			Object value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = getFieldFromClassHierarchy(victim.getClass(), fieldName);
		field.setAccessible(true);
		field.set(victim, value);
	}

	private Field getFieldFromClassHierarchy(Class<?> objectClass,
			String fieldName) throws NoSuchFieldException {
		try {
			return objectClass.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			Class<?> superClass = objectClass.getSuperclass();
			if (superClass == null) {
				throw e;
			}
			return getFieldFromClassHierarchy(superClass, fieldName);
		}
	}

	public Object getValue(Object victim, String fieldName) {
		return null;
	}

}
