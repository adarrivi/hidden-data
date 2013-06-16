package com.hidden.data.filter;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hidden.data.db.dao.PatternDao;
import com.hidden.data.db.model.Pattern;
import com.hidden.data.queue.consumer.RowConsumerTemplate;
import com.hidden.data.queue.model.FilterItem;

@Component
public class RowComsumer extends RowConsumerTemplate implements Runnable {

	protected static final String LINE_BREAK = System
			.getProperty("line.separator");
	private static final Logger LOG = Logger.getLogger(RowComsumer.class);

	@Autowired
	private PatternDao patternDao;
	private List<Pattern> allPatterns;
	private FilterItem currentItem;
	private Pattern currentPattern;

	@Override
	public void run() {
		allPatterns = patternDao.loadAll();
		receiveMessages();
	}

	@Override
	public void consumeRows(FilterItem filterItem) {
		currentItem = filterItem;
		getPatternFromCurrentFilterItem();
		if (currentPattern.matches(filterItem.getLines())) {
			printLines();
		}
	}

	private void getPatternFromCurrentFilterItem() {
		for (Pattern pattern : allPatterns) {
			if (pattern.getId().equals(currentItem.getPatternId())) {
				currentPattern = pattern;
			}
		}
	}

	private void printLines() {
		LOG.debug("Pattern: " + currentPattern.getName());
		StringBuilder sb = new StringBuilder(LINE_BREAK);
		for (String line : currentItem.getLines()) {
			sb.append(line).append(LINE_BREAK);
		}
		LOG.debug(sb.toString());
	}
}
