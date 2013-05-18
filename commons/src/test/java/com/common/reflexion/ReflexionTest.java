package com.common.reflexion;

import java.security.Permission;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;

public class ReflexionTest {

	private static final String NOT_EXISTING_MEMBER = "bsnNumber";
	private static final String MEMBER_FILED = "numberOfPages";
	private static final String PARENT_MEMBER_FIELD = "identifier";
	private static final String STATIC_FINAL_MEMBER = "NUMBER_OF_PAGES";
	private Reflexion victim;
	private StubBookSubClass book = new StubBookSubClass();
	private String memberName;
	private Object value;
	private static final Integer NEW_VALUE = new Integer(3);
	private static final String INCORRECT_VALUE = "33234";

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		victim = Reflexion.getInstance();
	}

	@After
	public void tearDown() {
		tearDownSecurityManagerToThrowEx();
	}

	private void tearDownSecurityManagerToThrowEx() {
		System.setSecurityManager(null);
	}

	@Test
	public void setMember_NotExistingMember_ThrowsReflxEx() {
		expectReflexionEx();
		givenNoExistingMember();
		givenCorrectValue();
		whenSetMember();
	}

	private void expectReflexionEx() {
		expectedException.expect(ReflexionException.class);
	}

	private void givenNoExistingMember() {
		memberName = NOT_EXISTING_MEMBER;
	}

	private void givenCorrectValue() {
		value = NEW_VALUE;
	}

	private void whenSetMember() {
		victim.setMember(book, memberName, value);
	}

	@Test
	public void setMember_InvalidValue_ThrowsReflexEx() {
		expectReflexionEx();
		givenCorrectMember();
		givenIncorrectValue();
		whenSetMember();
	}

	private void givenCorrectMember() {
		memberName = MEMBER_FILED;
	}

	private void givenIncorrectValue() {
		value = INCORRECT_VALUE;
	}

	@Test
	public void setMember_NullValue_ThrowsReflexEx() {
		expectReflexionEx();
		givenCorrectMember();
		givenNullValue();
		whenSetMember();
	}

	private void givenNullValue() {
		value = null;
	}

	@Test
	public void setMember_CorrectMemValue_ModifiesValue() {
		givenCorrectMember();
		givenCorrectValue();
		whenSetMember();
		thenNewValueShouldHasBeenSet();
	}

	private void thenNewValueShouldHasBeenSet() {
		Assert.assertEquals(NEW_VALUE.intValue(), book.getNumberOfPages());
	}

	@Test
	public void setMember_FromParentClass_ModifiesValue() {
		givenParentMember();
		whenSetMember();
		thenNewParentValueShouldHasBeenSet();
	}

	private void givenParentMember() {
		memberName = PARENT_MEMBER_FIELD;
		value = NEW_VALUE;
	}

	private void thenNewParentValueShouldHasBeenSet() {
		Assert.assertTrue(NEW_VALUE.equals(book.getIdentifier()));
	}

	@Test
	public void setMember_SecurityEx_ThrowReflexEx() throws SecurityException {
		expectReflexionExContaining(SecurityException.class.getCanonicalName());
		givenSecurityExAccessingMember();
		givenCorrectMember();
		givenCorrectValue();
		whenSetMember();
	}

	private void expectReflexionExContaining(String message) {
		expectReflexionEx();
		expectedException.expectMessage(message);
	}

	private void givenSecurityExAccessingMember() {
		System.setSecurityManager(new SecurityManager() {
			@Override
			public void checkMemberAccess(Class<?> clazz, int which) {
				throw new SecurityException("Not allowed");
			}

			@Override
			public void checkPermission(Permission perm) {
				// nothing to do
			}
		});
	}

	@Test
	public void setMember_StaticFinalMember_ThrowsReflexEx()
			throws SecurityException {
		expectReflexionExContaining(IllegalAccessException.class
				.getCanonicalName());
		givenStaticFinalMember();
		whenSetMember();
	}

	private void givenStaticFinalMember() {
		memberName = STATIC_FINAL_MEMBER;
	}

}
