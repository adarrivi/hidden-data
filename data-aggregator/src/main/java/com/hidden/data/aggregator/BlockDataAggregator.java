package com.hidden.data.aggregator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import com.hidden.data.nosql.model.discovery.PatternDiscovery;

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

	private FilteredBlock currentFilteredBlock;
	private Pattern currentPattern;

	@Override
	public void run() {
		currentFilteredBlock = filteredBlockDao.findOneAndRemove();
		while (currentFilteredBlock != null) {
			createNewAggregatedData();
			currentFilteredBlock = filteredBlockDao.findOneAndRemove();
		}
		LOG.debug("Aggregation done");
	}

	private void createNewAggregatedData() {
		// TODO Add caching everywhere
		Book book = bookDao.findById(currentFilteredBlock.getBookId());
		currentPattern = patternDao.findById(currentFilteredBlock
				.getPatternId());
		List<Line> bookLines = getBooLinesFromCurrentFilteredBlock();
		PatternDiscovery discoveryPattern = createDiscoveryPatternFromCurrentFilteredBlock();
		BookDiscovery bookDiscovery = new BookDiscovery(book.getTitle(), book
				.getAuthor().getName(), bookLines, discoveryPattern, book
				.getBookLines().size());
		bookDiscoveryDao.save(bookDiscovery);
	}

	private PatternDiscovery createDiscoveryPatternFromCurrentFilteredBlock() {
		PatternDiscovery discoveryPattern = new PatternDiscovery(
				currentPattern.getName(),
				getPatternContentFromCurrentFilteredBlock());
		return discoveryPattern;
	}

	private List<List<Character>> getPatternContentFromCurrentFilteredBlock() {
		List<List<Character>> patternContent = new ArrayList<List<Character>>();
		for (List<PatternItem> itemsPerLine : currentPattern.getContent()) {
			List<Character> charactersPerLine = new ArrayList<Character>();
			for (PatternItem item : itemsPerLine) {
				Character character = null;
				if (!item.isEmpty()) {
					character = item.getValue().toCharArray()[0];
				}
				charactersPerLine.add(character);
			}
			patternContent.add(charactersPerLine);
		}
		return patternContent;
	}

	private List<Line> getBooLinesFromCurrentFilteredBlock() {
		List<Line> lines = new ArrayList<Line>();
		for (int i = 0; i < currentFilteredBlock.getLines().size(); i++) {
			String lineContent = currentFilteredBlock.getLines().get(i);
			Line line = new Line(currentFilteredBlock.getStartLineNumber() + i,
					lineContent);
			lines.add(line);
		}
		return lines;
	}

}
