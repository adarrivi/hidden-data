package com.hidden.data.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hidden.data.db.dao.PatternDao;
import com.hidden.data.db.model.Pattern;
import com.hidden.data.queue.consumer.RowConsumerTemplate;
import com.hidden.data.queue.model.FilterItem;

@Component
public class RowComsumer extends RowConsumerTemplate implements Runnable {

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
			// TODO save in nosql
		}
	}

	private void getPatternFromCurrentFilterItem() {
		for (Pattern pattern : allPatterns) {
			if (pattern.getId().equals(currentItem.getPatternId())) {
				currentPattern = pattern;
			}
		}
	}

}
