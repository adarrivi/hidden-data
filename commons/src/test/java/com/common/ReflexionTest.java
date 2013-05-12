package com.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.common.reflexion.Reflexion;
import com.common.reflexion.ReflexionException;

public class ReflexionTest {

	private static final String NOT_EXISTING_MEMBER = "bsnNumber";
	private static final String MEMBER_FILED = "numberOfPages";
	private static final String PARENT_MEMBER_FIELD = "identifier";
	private Reflexion victim;
	private ConcreteBookSample book = new ConcreteBookSample();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		victim = Reflexion.getInstance();
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
		ConcreteBookSample equalsBook = new ConcreteBookSample();
		Assert.assertFalse(equalsBook.equals(book));
	}

	@Test
	public void setParentMemberModifiesValue() {
		Integer newMeberValue = 5;
		victim.setMember(book, PARENT_MEMBER_FIELD, newMeberValue);
		verifyBookHasBeenModified();
		Assert.assertEquals(newMeberValue, book.getIdentifier());
	}
}
