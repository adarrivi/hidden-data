package com.hidden.data.filter;

import java.util.List;

import org.apache.log4j.Logger;

import com.hidden.data.queue.consumer.SimplifiedRowConsumer;
import com.hidden.data.queue.model.SimplifiedBookRow;

public class RowComsumer extends SimplifiedRowConsumer {

	private static final Logger LOG = Logger.getLogger(RowComsumer.class);

	private Pattern pattern;

	public RowComsumer(Pattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public void consumeRows(List<SimplifiedBookRow> rows) {
		if (pattern.matches(rows)) {
			LOG.debug("pattern matched");
		}

	}
}
