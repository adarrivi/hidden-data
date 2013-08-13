package com.hidden.data.common.reflection;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;

import com.hidden.data.common.reflection.FieldAccessor;
import com.hidden.data.common.reflection.FieldAccessorApache;

public class FieldAccessorApacheTest {

	private static final String FIELD_VALUE = "value";
	private static final String FIELD_NAME = "name";
	private FieldAccessor victim = new FieldAccessorApache();
	private StubObject target = new StubObject(FIELD_VALUE);
	private Object valueResult;
	private Field field;

	@Test
	public void readField_ReturnsFieldValue() throws IllegalAccessException {
		whenReadField();
		thenValueShouldBe(FIELD_VALUE);
	}

	private void whenReadField() throws IllegalAccessException {
		valueResult = victim.readField(target, FIELD_NAME, true);
	}

	private void thenValueShouldBe(Object expectedValue) {
		Assert.assertEquals(expectedValue, valueResult);
	}

	@Test
	public void getField_ReturnsNotNullField() {
		whenGetField();
		thenFieldShouldNotBeNull();
	}

	private void whenGetField() {
		field = victim.getField(target.getClass(), FIELD_NAME, true);
	}

	private void thenFieldShouldNotBeNull() {
		Assert.assertNotNull(field);
	}

}
