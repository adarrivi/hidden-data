package com.common.reflexion;

abstract class StubBookClass {

	private Integer identifier;
	private String name;

	StubBookClass(Integer identifier, String name) {
		this.identifier = identifier;
		this.name = name;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public String getName() {
		return getPrivateName();
	}

	private String getPrivateName() {
		return name;
	}

	abstract long getNumberOfPages();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getIdentifier().hashCode();
		result = prime * result + getName().hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StubBookClass)) {
			return false;
		}
		StubBookClass other = (StubBookClass) obj;
		return getIdentifier().equals(other.getIdentifier())
				&& getName().equals(other.getName());
	}

}