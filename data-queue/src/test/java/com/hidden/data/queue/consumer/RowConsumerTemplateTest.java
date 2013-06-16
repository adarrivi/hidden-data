package com.hidden.data.queue.consumer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.queue.connection.ConsumerConnection;
import com.hidden.data.queue.connection.QueueConnectionFactory;
import com.hidden.data.queue.connection.TimeOut;
import com.hidden.data.queue.model.FilterItem;

public class RowConsumerTemplateTest {

	private RowConsumerStub victim;
	private List<FilterItem> expectedConsumedRows;

	@Mock
	private QueueConnectionFactory connectionFactory;
	@Mock
	private ConsumerConnection connection;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(connectionFactory.createFilterConsumerConnection()).thenReturn(
				connection);
	}

	private void setExpectedConsumedRows() {
		expectedConsumedRows = new ArrayList<FilterItem>();
		expectedConsumedRows.add(new FilterItem(null, 1, Integer
				.valueOf(1)));
	}

	@Test
	public void receiveMessages_OneMessage_ConsumesRows() {
		givenOneMessage();
		whenReceiveMessages();
		thenConsumeRowsShouldBeExecuted();
	}

	private void givenOneMessage() {
		setExpectedConsumedRows();
		Mockito.when(connection.waitUntilReceive()).thenReturn(
				(Serializable) expectedConsumedRows, new TimeOut());
		victim = new RowConsumerStub(connectionFactory, 1);
	}

	private void whenReceiveMessages() {
		victim.receiveMessages();
	}

	private void thenConsumeRowsShouldBeExecuted() {
		victim.assertConsumeRowsWith(Collections
				.singletonList(expectedConsumedRows));
	}

	@Test
	public void receiveMessages_ThreeMessage_ConsumesRows() {
		givenThreeMessages();
		whenReceiveMessages();
		thenConsumeThreeRowsShouldBeExecuted();
	}

	private void givenThreeMessages() {
		setExpectedConsumedRows();
		Mockito.when(connection.waitUntilReceive()).thenReturn(
				(Serializable) expectedConsumedRows,
				(Serializable) expectedConsumedRows,
				(Serializable) expectedConsumedRows, new TimeOut());
		victim = new RowConsumerStub(connectionFactory, 3);
	}

	private void thenConsumeThreeRowsShouldBeExecuted() {
		victim.assertConsumeRowsWith(Collections.nCopies(3,
				expectedConsumedRows));
	}

	@Test
	public void receiveMessages_TimeOut_ConsumesNoRows() {
		givenTimeOut();
		whenReceiveMessages();
		thenConsumeRowsShouldNotBeExecuted();
	}

	private void givenTimeOut() {
		Mockito.when(connection.waitUntilReceive()).thenReturn(new TimeOut());
		victim = new RowConsumerStub(connectionFactory, 0);
	}

	private void thenConsumeRowsShouldNotBeExecuted() {
		victim.assertConsumeRowsWith(Collections
				.<List<FilterItem>> emptyList());
	}

}
