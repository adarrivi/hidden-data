package com.hidden.data.queue.consumer;

import java.io.Serializable;
import java.util.Collections;

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
	private FilterItem expectedConsumed;

	@Mock
	private QueueConnectionFactory connectionFactory;
	@Mock
	private ConsumerConnection connection;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(connectionFactory.createFilterConsumerConnection())
				.thenReturn(connection);
	}

	private void setExpectedConsumedRows() {
		expectedConsumed = new FilterItem(Collections.<String> emptyList(), 0,
				Integer.valueOf(1), Integer.valueOf(1));
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
				(Serializable) expectedConsumed, new TimeOut());
		createVictim(1);
	}

	private void createVictim(int messageNumber) {
		victim = new RowConsumerStub(messageNumber);
		victim.setConnectionFactory(connectionFactory);
	}

	private void whenReceiveMessages() {
		victim.receiveMessages();
	}

	private void thenConsumeRowsShouldBeExecuted() {
		victim.assertConsumeRowsWith(Collections
				.singletonList(expectedConsumed));
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
				expectedConsumed, expectedConsumed, expectedConsumed,
				new TimeOut());
		createVictim(3);
	}

	private void thenConsumeThreeRowsShouldBeExecuted() {
		victim.assertConsumeRowsWith(Collections.nCopies(3, expectedConsumed));
	}

	@Test
	public void receiveMessages_TimeOut_ConsumesNoRows() {
		givenTimeOut();
		whenReceiveMessages();
		thenConsumeRowsShouldNotBeExecuted();
	}

	private void givenTimeOut() {
		Mockito.when(connection.waitUntilReceive()).thenReturn(new TimeOut());
		createVictim(0);
	}

	private void thenConsumeRowsShouldNotBeExecuted() {
		victim.assertConsumeRowsWith(Collections.<FilterItem> emptyList());
	}

}
