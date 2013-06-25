package com.common.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ReflectionTest {

	private static final Class<StubObject> TARGET_CLASS = StubObject.class;
	private static final String FIELD_NAME = "name";
	private static final String FIELD_NEW_VALUE = "newValue";
	private static final String METHOD_NAME = "getName";
	private static final String METHOD_RESULT = "methodResult";

	private StubObject target = new StubObject();
	private Field field;
	private Object result;
	@Mock
	private FieldAccessor fieldAccessor;
	@Mock
	private MethodAccessor methodAccessor;
	@InjectMocks
	private Reflection victim = Reflection.getInstance();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() {
		tearDownSecurityManagerToThrowEx();
	}

	private void tearDownSecurityManagerToThrowEx() {
		System.setSecurityManager(null);
	}

	@Test
	public void setField_NotExistingMember_ThrowsReflectionEx() {
		expectReflectionEx();
		givenNoExistingMember();
		whenSetField();
	}

	private void expectReflectionEx() {
		expectedException.expect(ReflectionException.class);
	}

	private void givenNoExistingMember() {
		Mockito.when(fieldAccessor.getField(TARGET_CLASS, FIELD_NAME, true))
				.thenReturn(null);
	}

	private void whenSetField() {
		victim.setField(target, FIELD_NAME, FIELD_NEW_VALUE);
	}

	@Test
	public void setField_SecurityEx_ThrowsReflectionEx() {
		expectReflectionEx();
		givenExWhenGettingField(SecurityException.class);
		whenSetField();
	}

	private void givenExWhenGettingField(
			Class<? extends Exception> thrownExceptionClass) {
		Mockito.doThrow(thrownExceptionClass).when(fieldAccessor)
				.getField(TARGET_CLASS, FIELD_NAME, true);
	}

	@Test
	public void setField_IllegalArgumentEx_ThrowsReflectionEx() {
		expectReflectionEx();
		givenExWhenGettingField(IllegalArgumentException.class);
		whenSetField();
	}

	@Test
	public void setField_IllegalAccessEx_ThrowsReflectionEx() {
		expectReflectionEx();
		givenExWhenGettingField(IllegalAccessException.class);
		whenSetField();
	}

	@Test
	public void setField_ModifiesField() throws IllegalArgumentException,
			SecurityException, NoSuchFieldException {
		givenFieldFound();
		whenSetField();
		thenValueShouldBeSet();
	}

	private void givenFieldFound() throws SecurityException,
			NoSuchFieldException {
		field = TARGET_CLASS.getDeclaredField(FIELD_NAME);
		Mockito.when(fieldAccessor.getField(TARGET_CLASS, FIELD_NAME, true))
				.thenReturn(field);
	}

	private void thenValueShouldBeSet() {
		Assert.assertEquals(FIELD_NEW_VALUE, target.getName());
	}

	@Test
	public void invokeAccessibleMethod_NoSuchMethodException_ThrowsReflexEx()
			throws IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		expectReflectionEx();
		givenExWhenInvoking(NoSuchMethodException.class);
		whenInvokeAccessibleMethod();
	}

	private void givenExWhenInvoking(
			Class<? extends Exception> thrownExceptionClass)
			throws IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		Mockito.doThrow(thrownExceptionClass).when(methodAccessor)
				.invokeMethod(target, METHOD_NAME);
	}

	private void whenInvokeAccessibleMethod() {
		result = victim.invokeAccessibleMethod(target, METHOD_NAME);
	}

	@Test
	public void invokeAccessibleMethod_IllegalAccessException_ThrowsReflexEx()
			throws IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		expectReflectionEx();
		givenExWhenInvoking(IllegalAccessException.class);
		whenInvokeAccessibleMethod();
	}

	@Test
	public void invokeAccessibleMethod_InvocationTargetException_ThrowsReflexEx()
			throws IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		expectReflectionEx();
		givenExWhenInvoking(InvocationTargetException.class);
		whenInvokeAccessibleMethod();
	}

	@Test
	public void invokeAccessibleMethod_ExistingMetehod_ReturnsMethodResult()
			throws IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		givenInvokeExistingMethod();
		whenInvokeAccessibleMethod();
		thenResultShouldBeAsMethodResult();
	}

	private void givenInvokeExistingMethod() throws IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		Mockito.when(methodAccessor.invokeMethod(target, METHOD_NAME))
				.thenReturn(METHOD_RESULT);
	}

	private void thenResultShouldBeAsMethodResult() {
		Assert.assertEquals(METHOD_RESULT, result);
	}

	@Test
	public void readField_IllegalAccessException_ThrowsRelectionEx()
			throws IllegalAccessException {
		expectReflectionEx();
		givenExWhenReadingFile(IllegalAccessException.class);
		whenReadField();
	}

	private void givenExWhenReadingFile(
			Class<? extends Exception> thrownExceptionClass)
			throws IllegalAccessException {
		Mockito.doThrow(thrownExceptionClass).when(fieldAccessor)
				.readField(target, FIELD_NAME, true);
	}

	private void whenReadField() {
		result = victim.readField(target, FIELD_NAME);
	}

	@Test
	public void readField_IllegalArgumentException_ThrowsRelectionEx()
			throws IllegalAccessException {
		expectReflectionEx();
		givenExWhenReadingFile(IllegalArgumentException.class);
		whenReadField();
	}

	@Test
	public void readField_ExistingField_ReturnsFieldValue()
			throws IllegalAccessException {
		givenExistingField();
		whenReadField();
		thenShouldReturnFieldValue();
	}

	private void givenExistingField() throws IllegalAccessException {
		Mockito.when(fieldAccessor.readField(target, FIELD_NAME, true))
				.thenReturn(FIELD_NEW_VALUE);
	}

	private void thenShouldReturnFieldValue() {
		Assert.assertEquals(FIELD_NEW_VALUE, result);
	}

}
