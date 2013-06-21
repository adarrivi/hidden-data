package com.hidden.data.aggregator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.dao.PatternDao;
import com.hidden.data.db.model.Book;
import com.hidden.data.db.model.Pattern;
import com.hidden.data.db.model.PatternItem;
import com.hidden.data.nosql.dao.BookDiscoveryDao;
import com.hidden.data.nosql.dao.FilteredBlockDao;
import com.hidden.data.nosql.model.FilteredBlock;
import com.hidden.data.nosql.model.discovery.BookDiscovery;
import com.hidden.data.nosql.model.discovery.Line;

@Component
public class BlockDataAggregator implements Runnable {

	private static final Logger LOG = Logger
			.getLogger(BlockDataAggregator.class);

	@Autowired
	private BookDiscoveryDao bookDiscoveryDao;
	@Autowired
	private FilteredBlockDao filteredBlockDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private PatternDao patternDao;

	@Override
	public void run() {
		FilteredBlock filteredBlock = filteredBlockDao.findOneAndRemove();
		int times = 0;
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		while (filteredBlock != null && times < 50) {
			Book book = bookDao.findById(filteredBlock.getBookId());
			Pattern pattern = patternDao.findById(filteredBlock.getPatternId());
			List<Line> lines = new ArrayList<Line>();
			for (int i = 0; i < filteredBlock.getLines().size(); i++) {
				String lineContent = filteredBlock.getLines().get(i);
				Line line = new Line(filteredBlock.getStartLineNumber() + i,
						lineContent);
				lines.add(line);
			}

			List<List<Character>> patternContent = new ArrayList<List<Character>>();
			for (List<PatternItem> itemsPerLine : pattern.getContent()) {
				List<Character> charactersPerLine = new ArrayList<Character>();
				for (PatternItem item : itemsPerLine) {
					Character character = Character.SPACE_SEPARATOR;
					if (!item.isEmpty()) {
						character = item.getValue().toCharArray()[0];
					}
					charactersPerLine.add(character);
				}
				patternContent.add(charactersPerLine);
			}
			com.hidden.data.nosql.model.discovery.Pattern discoveryPattern = new com.hidden.data.nosql.model.discovery.Pattern(
					pattern.getName(), patternContent);
			BookDiscovery bookDiscovery = new BookDiscovery(book.getTitle(),
					book.getAuthor().getName(), lines, discoveryPattern);
			bookDiscoveryDao.save(bookDiscovery);
			times++;
		}
		stopWatch.stop();
		LOG.debug(stopWatch.prettyPrint());
	}
}
