package com.hidden.data.web.service.impl;

import java.util.Comparator;

import com.hidden.data.nosql.model.discovery.BookDiscovery;

class PatternLocationComparator implements Comparator<BookDiscovery> {

	protected PatternLocationComparator() {
		super();
	}

	@Override
	public int compare(BookDiscovery bookDiscovery1,
			BookDiscovery bookDiscovery2) {
		Integer bookDiscovery1FirstLine = Integer.valueOf(bookDiscovery1
				.getFirstPatternLineNumber());
		Integer bookDiscovery2FirstLine = Integer.valueOf(bookDiscovery2
				.getFirstPatternLineNumber());
		return bookDiscovery1FirstLine.compareTo(bookDiscovery2FirstLine);
	}
}
