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
	private Reflexion victim;
	private StubBookSubClass book = new StubBookSubClass();

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
	public void setNotExistingMembeThrowsException() {
		expectedException.expect(ReflexionException.class);
		victim.setMember(book, NOT_EXISTING_MEMBER, new Integer(3));
	}

	@Test
	public void setInvalidValueThrowsException() {
		expectedException.expect(ReflexionException.class);
		victim.setMember(book, MEMBER_FILED, "3345");
	}

	@Test
	public void setMemberModifiesValue() {
		long newMeberValue = 5;
		victim.setMember(book, MEMBER_FILED, newMeberValue);
		verifyBookHasBeenModified();
		Assert.assertEquals(newMeberValue, book.getNumberOfPages());
	}

	private void verifyBookHasBeenModified() {
		StubBookSubClass equalsBook = new StubBookSubClass();
		Assert.assertFalse(equalsBook.equals(book));
	}

	@Test
	public void setParentMemberModifiesValue() {
		Integer newMeberValue = 5;
		victim.setMember(book, PARENT_MEMBER_FIELD, newMeberValue);
		verifyBookHasBeenModified();
		Assert.assertEquals(newMeberValue, book.getIdentifier());
	}

	@Test
	public void securityExThrowsReflexionEx() throws SecurityException {
		expectedException.expect(ReflexionException.class);
		expectedException.expectMessage(SecurityException.class
				.getCanonicalName());
		setUpSecurityManagerToThrowEx();
		victim.setMember(book, MEMBER_FILED, 0);
	}

	private void setUpSecurityManagerToThrowEx() {
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
	public void illegalAccessExThrowsReflexionEx() throws SecurityException {
		expectedException.expect(ReflexionException.class);
		expectedException.expectMessage(IllegalAccessException.class
				.getCanonicalName());
		victim.setMember(book, "NUMBER_OF_PAGES", true);
	}

}
