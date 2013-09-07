package com.hidden.data.aggregator;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.common.reflection.Reflection;
import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.dao.PatternDao;
import com.hidden.data.db.model.Author;
import com.hidden.data.db.model.Book;
import com.hidden.data.db.model.Pattern;
import com.hidden.data.db.model.PatternItem;
import com.hidden.data.db.model.PatternRow;
import com.hidden.data.nosql.dao.BookDiscoveryDao;
import com.hidden.data.nosql.dao.FilteredBlockDao;
import com.hidden.data.nosql.model.FilteredBlock;
import com.hidden.data.nosql.model.discovery.BookDiscovery;

public class BlockDataAggregatorTest {

	private static final int START_LINE_NUMBER = 1;
	private static final int BOOK_ID = 1;
	private static final int PATTERN_ID = 1;
	@Mock
	private BookDiscoveryDao bookDiscoveryDao;
	@Mock
	private FilteredBlockDao filteredBlockDao;
	@Mock
	private BookDao bookDao;
	@Mock
	private PatternDao patternDao;
	@InjectMocks
	private BlockDataAggregator victim = new BlockDataAggregator();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void run_NoFilteredBlocks_DoesntSaveBookDiscoverys() {
		givenNoFilteredBocks();
		whenRun();
		thenNoBookDiscoveriesShouldHaveBeenSaved();
	}

	private void givenNoFilteredBocks() {
		Mockito.when(filteredBlockDao.findOneAndRemove()).thenReturn(null);
	}

	private void whenRun() {
		victim.run();
	}

	private void thenNoBookDiscoveriesShouldHaveBeenSaved() {
		Mockito.verify(bookDiscoveryDao, Mockito.never()).save(
				Matchers.any(BookDiscovery.class));
	}

	@Test
	public void run_FilteredBlock_SavesNewAggregatedData() {
		givenOneFilteredBlock();
		whenRun();
		thenShouldHaveSavedBookDiscoveries(1);
	}

	private void givenOneFilteredBlock() {
		FilteredBlock block = new FilteredBlock(PATTERN_ID, BOOK_ID,
				START_LINE_NUMBER, Collections.singletonList("Lorem ipsum"));
		Mockito.when(filteredBlockDao.findOneAndRemove()).thenReturn(block,
				(FilteredBlock) null);
		givenAPattern();
		Book book = Book.createEmptyBook();
		Author author = Author.createEmptyAuthor();
		author.setName("Isaac Asimov");
		book.setAuthor(author);
		Mockito.when(bookDao.findById(BOOK_ID)).thenReturn(book);
	}

	private void givenAPattern() {
		PatternItem item = PatternItem.createEmptyItem();
		Reflection.getInstance().setField(item, "value", "a");
		List<PatternItem> rowContent = Collections.singletonList(item);
		PatternRow row = new PatternRow();
		Pattern pattern = Pattern.createEmptyPattern();

		Reflection.getInstance().setField(row, "content", rowContent);
		Reflection.getInstance().setField(pattern, "rows",
				Collections.singletonList(row));
		Mockito.when(patternDao.findById(PATTERN_ID)).thenReturn(pattern);
	}

	private void thenShouldHaveSavedBookDiscoveries(int numerOfBookDiscoveries) {
		Mockito.verify(bookDiscoveryDao, Mockito.times(numerOfBookDiscoveries))
				.save(Matchers.any(BookDiscovery.class));
	}

}
