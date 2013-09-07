package com.hidden.data.filter;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.common.reflection.Reflection;
import com.hidden.data.db.dao.PatternDao;
import com.hidden.data.db.model.Pattern;
import com.hidden.data.nosql.dao.FilteredBlockDao;
import com.hidden.data.nosql.model.FilteredBlock;
import com.hidden.data.queue.connection.ConsumerConnection;
import com.hidden.data.queue.connection.QueueConnectionFactory;
import com.hidden.data.queue.model.FilterItem;

public class RowComsumerTest {

	private static final Integer PATTERN_ID = Integer.valueOf(1);

	@Mock
	private FilteredBlockDao filteredBlockDao;
	@Mock
	private PatternDao patternDao;
	@Mock
	private QueueConnectionFactory connectionFactory;
	@Mock
	private ConsumerConnection connection;

	private PatternStub pattern = new PatternStub();
	private FilterItem filteredItem = new FilterItem(
			Collections.<String> emptyList(), 1, Integer.valueOf(0), PATTERN_ID);

	@InjectMocks
	private RowComsumer victim = new RowComsumer();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		setUpAllPattern();
		setUpConnection();
	}

	private void setUpAllPattern() {
		Reflection.getInstance().setField(pattern, "id", PATTERN_ID);
		PatternStub otherPattern = new PatternStub();
		otherPattern.setMatchesAll(false);
		Reflection.getInstance().setField(otherPattern, "id",
				Integer.valueOf(-1));
		Reflection.getInstance().setField(victim, "allPatterns",
				Arrays.asList((Pattern) otherPattern, (Pattern) pattern));
	}

	private void setUpConnection() {
		Mockito.when(connectionFactory.createFilterConsumerConnection())
				.thenReturn(connection);
	}

	@Test
	public void consumeRows_NotMatching_DoesNotSave() {
		givenMatchingBlock(false);
		whenConsumeRows();
		thenNoFilteredBlockShouldBeSaved();
	}

	private void givenMatchingBlock(boolean matchesAll) {
		pattern.setMatchesAll(matchesAll);
	}

	private void whenConsumeRows() {
		victim.consumeRows(filteredItem);
	}

	private void thenNoFilteredBlockShouldBeSaved() {
		Mockito.verify(filteredBlockDao, Mockito.never()).save(
				Matchers.any(FilteredBlock.class));
	}

	@Test
	public void consumeRows_Matching_SavesBlock() {
		givenMatchingBlock(true);
		whenConsumeRows();
		thenFilteredBlockShouldBeSaved();
	}

	private void thenFilteredBlockShouldBeSaved() {
		Mockito.verify(filteredBlockDao)
				.save(Matchers.any(FilteredBlock.class));
	}

	@Test
	public void run_LoadsAllPaterns() {
		givenCloseConnection();
		whenRun();
		thenAllPatternsShouldBeLoaded();
	}

	private void givenCloseConnection() {
		victim.closeConnection();
	}

	private void whenRun() {
		victim.run();
	}

	private void thenAllPatternsShouldBeLoaded() {
		Mockito.verify(patternDao).loadAll();
	}

}
