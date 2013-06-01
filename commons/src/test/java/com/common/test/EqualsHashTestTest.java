package com.common.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EqualsHashTestTest {

	private EqualsHashTest victim;

	private Object victimItem;
	private Object equalsItem;
	private Object[] differents;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void verify_BothNull_ThrowsAssertEx() {
		expectAssertEx();
		givenBothNull();
		whenVerify();
	}

	private void expectAssertEx() {
		expectedException.expect(AssertionError.class);
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

	@Test
	public void verify_FirstNull_ThrowsAssertEx() {
		expectAssertEx();
		givenFirstNull();
		whenVerify();
	}

	private void givenFirstNull() {
		victimItem = null;
		equalsItem = EqualsItemStub.createAlwaysEqualsHashcode1();
		createVictim();
	}

	@Test
	public void verify_SecondNull_ThrowsAssertEx() {
		expectAssertEx();
		givenSecondNull();
		whenVerify();
	}

	private void givenSecondNull() {
		victimItem = EqualsItemStub.createAlwaysEqualsHashcode1();
		equalsItem = null;
		createVictim();
	}

	@Test
	public void verify_NoEquals_ThrowsAssertEx() {
		expectAssertEx();
		givenNoEquals();
		whenVerify();
	}

	private void givenNoEquals() {
		victimItem = EqualsItemStub.createNeverEqualsItem();
		equalsItem = EqualsItemStub.createAlwaysEqualsHashcode1();
		createVictim();
	}

	@Test
	public void verify_SymmetricNoEquals_ThrowsAssertEx() {
		expectAssertEx();
		givenSymmetricNoEquals();
		whenVerify();
	}

	private void givenSymmetricNoEquals() {
		equalsItem = EqualsItemStub.createNeverEqualsItem();
		victimItem = EqualsItemStub.createAlwaysEqualsHashcode1();
		createVictim();
	}

	@Test
	public void verify_EqualsButDifferentHashCode_ThrowsAssertEx() {
		expectAssertEx();
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

	@Test
	public void verify_EqualsIsDifferentClass_ThrowAssertEx() {
		expectAssertEx();
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

	@Test
	public void verify_EqOkButDifferentsEq_ThrowsAssertEx() {
		expectAssertEx();
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
