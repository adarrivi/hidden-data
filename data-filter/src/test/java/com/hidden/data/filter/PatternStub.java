package com.hidden.data.filter;

import java.util.List;

import com.hidden.data.db.model.Pattern;

class PatternStub extends Pattern {

	private boolean matchesAll;

	void setMatchesAll(boolean value) {
		matchesAll = value;
	}

	@Override
	public boolean matches(List<String> lines) {
		return matchesAll;
	}

}
