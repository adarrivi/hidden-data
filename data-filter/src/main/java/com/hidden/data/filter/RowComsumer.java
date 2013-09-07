package com.hidden.data.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hidden.data.db.dao.PatternDao;
import com.hidden.data.db.model.Pattern;
import com.hidden.data.nosql.dao.FilteredBlockDao;
import com.hidden.data.nosql.model.FilteredBlock;
import com.hidden.data.queue.consumer.RowConsumerTemplate;
import com.hidden.data.queue.model.FilterItem;

@Component
@Scope(value = "prototype")
public class RowComsumer extends RowConsumerTemplate implements Runnable {

	@Autowired
	private PatternDao patternDao;
	@Autowired
	private FilteredBlockDao filteredBlockDao;
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
			FilteredBlock block = new FilteredBlock(filterItem.getPatternId()
					.intValue(), filterItem.getBookId().intValue(),
					filterItem.getFirstLineNumber(), filterItem.getLines());
			filteredBlockDao.save(block);
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
