package com.hidden.data.aggregator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.dao.PatternDao;
import com.hidden.data.nosql.dao.BookDiscoveryDao;
import com.hidden.data.nosql.dao.FilteredBlockDao;
import com.hidden.data.nosql.model.discovery.BookDiscovery;

public class BlockDataAggregatorTest {

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

}
