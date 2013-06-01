package com.common.test;

class EqualsItemStub {

	private static final int HASHCODE_1 = 11;
	private static final int HASHCODE_2 = 2;
	private boolean equalsValue;
	private int hashCode;

	static EqualsItemStub createNeverEqualsItem() {
		return new EqualsItemStub(false, HASHCODE_1);
	}

	static EqualsItemStub createAlwaysEqualsHashcode1() {
		return new EqualsItemStub(true, HASHCODE_1);
	}

	static EqualsItemStub createAlwaysEqualsHashcode2() {
		return new EqualsItemStub(true, HASHCODE_2);
	}

	private EqualsItemStub(boolean equalsValue, int hashCode) {
		this.equalsValue = equalsValue;
		this.hashCode = hashCode;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		return equalsValue;
	}

}
