package com.common.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.common.reflexion.ReflexionException;

public class AccessorVerifierTest {

	private static final String EXISTING_SETTER = "setStringField";
	private static final String NOT_EXISTING_SETTER = "setNotExisting";
	private static final String SET_DUPLICATED_STRING = "setDuplicatedStringField";
	private static final String GET_DUPLICATED_STRING_METHOD = "getDuplicatedStringField";
	private static final String EXISTING_FIELD_VALUE = "stringValue";
	private static final String EXISTING_GETTER = "getStringField";
	private static final String NOT_EXISTING_GETTER = "getNotExistingField";
	private static final String NOT_EXISTING_FIELD = "notExistingField";
	private static final String EXISTING_FIELD = "stringField";
	private AccessorVerifier victim;
	private AccessorVerifierObjectStub objectToVerify;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void verifyDirectGetters_Empty_NoExceptionThrown() {
		givenEmptyObjectToVerify();
		whenVerifyDirectGetters();
	}

	private void givenEmptyObjectToVerify() {
		createEmptyObjectToVeirfy();
		createVictim();
	}

	private void createEmptyObjectToVeirfy() {
		objectToVerify = new AccessorVerifierObjectStub();
	}

	private void createVictim() {
		victim = new AccessorVerifier(objectToVerify);
	}

	private void whenVerifyDirectGetters() {
		victim.verifyDirectGetters();
	}

	@Test
	public void verifyDirectGetters_GetterDoesntExist_ThrowsReflexionEx() {
		expectedException.expect(ReflexionException.class);
		givenNotExistingGetter();
		whenVerifyDirectGetters();
	}

	private void givenNotExistingGetter() {
		givenEmptyObjectToVerify();
		victim.addGetterToVerify(NOT_EXISTING_GETTER, EXISTING_FIELD, null);
	}

	@Test
	public void verifyDirectGetters_FieldDoesntExist_ThrowsReflexion() {
		expectException(ReflexionException.class);
		givenNotExistingFieldGetter();
		whenVerifyDirectGetters();
	}

	private void expectException(Class<? extends Throwable> exceptionClass) {
		expectedException.expect(exceptionClass);
	}

	private void givenNotExistingFieldGetter() {
		givenEmptyObjectToVerify();
		victim.addGetterToVerify(EXISTING_GETTER, NOT_EXISTING_FIELD, null);
	}

	@Test
	public void verifyDirectGetters_GetterDoesntReturnFieldValue_ThrowsAssertionError() {
		expectedException.expect(AssertionError.class);
		givenGetterReturningUnexpectedValue();
		whenVerifyDirectGetters();
	}

	private void givenGetterReturningUnexpectedValue() {
		givenEmptyObjectToVerify();
		victim.addGetterToVerify(GET_DUPLICATED_STRING_METHOD, EXISTING_FIELD,
				EXISTING_FIELD_VALUE);
	}

	@Test
	public void verifyDirectGetters_OneGetter_DoesNotThrowEx() {
		givenOneGetter();
		whenVerifyDirectGetters();
	}

	private void givenOneGetter() {
		givenEmptyObjectToVerify();
		addExistingGetter();
	}

	private void addExistingGetter() {
		victim.addGetterToVerify(EXISTING_GETTER, EXISTING_FIELD,
				EXISTING_FIELD_VALUE);
	}

	@Test
	public void verifyDirectGetters_TwoGetters_DoesNotThrowEx() {
		givenTwoGetters();
		whenVerifyDirectGetters();
	}

	private void givenTwoGetters() {
		givenOneGetter();
		addExistingGetter();
	}

	@Test
	public void verifyDirectSetters_Empty_NoExceptionThrown() {
		givenEmptyObjectToVerify();
		whenVerifyDirectSetters();
	}

	private void whenVerifyDirectSetters() {
		victim.verifyDirectSetters();
	}

	@Test
	public void verifyDirectSetters_SetterDoesntExist_ThrowsReflexionEx() {
		expectException(ReflexionException.class);
		givenNotExistingSetter();
		whenVerifyDirectSetters();
	}

	private void givenNotExistingSetter() {
		givenEmptyObjectToVerify();
		victim.addSetterToVerify(NOT_EXISTING_SETTER, EXISTING_FIELD,
				EXISTING_FIELD_VALUE);
	}

	@Test
	public void verifyDirectSetters_FieldDoesntExist_ThrowsReflexionEx() {
		expectException(ReflexionException.class);
		givenNotExistingFieldSetter();
		whenVerifyDirectSetters();
	}

	private void givenNotExistingFieldSetter() {
		givenEmptyObjectToVerify();
		victim.addSetterToVerify(EXISTING_SETTER, NOT_EXISTING_FIELD,
				EXISTING_FIELD_VALUE);
	}

	@Test
	public void verifyDirectSetters_SetterDoesntSetFieldValue_ThrowsAssertionError() {
		expectException(AssertionError.class);
		givenSetterDoesntSetFiledValue();
		whenVerifyDirectSetters();
	}

	private void givenSetterDoesntSetFiledValue() {
		givenEmptyObjectToVerify();
		victim.addSetterToVerify(SET_DUPLICATED_STRING, EXISTING_FIELD,
				EXISTING_FIELD_VALUE);
	}

	@Test
	public void verifyDirectSetters_OneSetter_DoesNotThrowEx() {
		givenOneSetter();
		whenVerifyDirectSetters();
	}

	private void givenOneSetter() {
		givenEmptyObjectToVerify();
		addExistingSetter();
	}

	private void addExistingSetter() {
		victim.addSetterToVerify(EXISTING_SETTER, EXISTING_FIELD,
				EXISTING_FIELD_VALUE);
	}

	@Test
	public void verifyDirectSetters_TwoSetters_DoesNotThrowEx() {
		givenTwoSetters();
		whenVerifyDirectSetters();
	}

	private void givenTwoSetters() {
		givenOneSetter();
		addExistingSetter();
	}

}
