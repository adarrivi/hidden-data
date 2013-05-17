package com.common.reflexion;

final class StubBookSubClass extends StubBookClass {

	private static final Integer IDENTIFIER = new Integer(1);
	private static final String BOOK_NAME = "Stranger in a Strange Land";
	private static final long NUMBER_OF_PAGES = 256;
	private long numberOfPages;

	StubBookSubClass() {
		super(IDENTIFIER, BOOK_NAME);
		this.numberOfPages = NUMBER_OF_PAGES;
	}

	@Override
	long getNumberOfPages() {
		return numberOfPages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ (int) (getNumberOfPages() ^ (getNumberOfPages() >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof StubBookSubClass)) {
			return false;
		}
		StubBookSubClass other = (StubBookSubClass) obj;
		return getNumberOfPages() == other.getNumberOfPages();
	}

}
