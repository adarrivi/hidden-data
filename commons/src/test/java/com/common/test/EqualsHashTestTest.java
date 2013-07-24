package com.common.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class EqualsHashTestTest {

	private EqualsHashTest victim;

	private Object victimItem;
	private Object equalsItem;
	private Object[] differents;

	// @Rule ExpectedException doesn't capture AssertionError (is a bug in junit
	// 4.11, but fix has not been released yet).
	// Using expected in @Test instead
	@Test(expected = AssertionError.class)
	public void verify_BothNull_ThrowsAssertEx() {
		givenBothNull();
		whenVerify();
	}

	private void givenBothNull() {
		victimItem = null;
		equalsItem = null;
		createVictim();
	}

	private void createVictim() {
		victim = new EqualsHashTest(victimItem, equalsItem, differents);
	}

	private void whenVerify() {
		victim.verify();
	}

	@Test(expected = AssertionError.class)
	public void verify_FirstNull_ThrowsAssertEx() {
		givenFirstNull();
		whenVerify();
	}

	private void givenFirstNull() {
		victimItem = null;
		equalsItem = EqualsItemStub.createAlwaysEqualsHashcode1();
		createVictim();
	}

	@Test(expected = AssertionError.class)
	public void verify_SecondNull_ThrowsAssertEx() {
		givenSecondNull();
		whenVerify();
	}

	private void givenSecondNull() {
		victimItem = EqualsItemStub.createAlwaysEqualsHashcode1();
		equalsItem = null;
		createVictim();
	}

	@Test(expected = AssertionError.class)
	public void verify_NoEquals_ThrowsAssertEx() {
		givenNoEquals();
		whenVerify();
	}

	private void givenNoEquals() {
		victimItem = EqualsItemStub.createNeverEqualsItem();
		equalsItem = EqualsItemStub.createAlwaysEqualsHashcode1();
		createVictim();
	}

	@Test(expected = AssertionError.class)
	public void verify_SymmetricNoEquals_ThrowsAssertEx() {
		givenSymmetricNoEquals();
		whenVerify();
	}

	private void givenSymmetricNoEquals() {
		equalsItem = EqualsItemStub.createNeverEqualsItem();
		victimItem = EqualsItemStub.createAlwaysEqualsHashcode1();
		createVictim();
	}

	@Test(expected = AssertionError.class)
	public void verify_EqualsButDifferentHashCode_ThrowsAssertEx() {
		givenEqualsButDifferentHashCode();
		whenVerify();
	}

	private void givenEqualsButDifferentHashCode() {
		victimItem = EqualsItemStub.createAlwaysEqualsHashcode1();
		equalsItem = EqualsItemStub.createAlwaysEqualsHashcode2();
		createVictim();
	}

	@Test
	public void verify_EqualsAndSameHashCode_noEx() {
		givenEqualsAndSameHashCode();
		whenVerify();
	}

	private void givenEqualsAndSameHashCode() {
		victimItem = EqualsItemStub.createAlwaysEqualsHashcode1();
		equalsItem = EqualsItemStub.createAlwaysEqualsHashcode1();
		createVictim();
	}

	@Test(expected = AssertionError.class)
	public void verify_EqualsIsDifferentClass_ThrowAssertEx() {
		givenEqualsIsDifferentClass();
		whenVerify();
	}

	private void givenEqualsIsDifferentClass() {
		victimItem = Integer.valueOf(0);
		equalsItem = StringUtils.EMPTY;
		createVictim();
	}

	@Test
	public void verify_EqOkAndDifferentsIsEmpty() {
		givenEqualsOKAndDifferentsIsEmpty();
		whenVerify();
	}

	private void givenEqualsOKAndDifferentsIsEmpty() {
		differents = new Object[0];
		givenEqualsAndSameHashCode();
	}

	@Test(expected = AssertionError.class)
	public void verify_EqOkButDifferentsEq_ThrowsAssertEx() {
		givenEqOkButDiffIsEquals();
		whenVerify();
	}

	private void givenEqOkButDiffIsEquals() {
		differents = new Object[] { victimItem };
		givenEqualsAndSameHashCode();
	}

	@Test
	public void verify_EqOkAndDiffIsOk() {
		givenEqOkAndDiffOk();
		whenVerify();
	}

	private void givenEqOkAndDiffOk() {
		victimItem = "test1";
		equalsItem = "test1";
		differents = new Object[] { "different1", "different2" };
		createVictim();
	}

}
