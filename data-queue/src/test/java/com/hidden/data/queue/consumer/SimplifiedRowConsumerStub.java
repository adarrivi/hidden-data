package com.hidden.data.queue.consumer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.hidden.data.queue.connection.QueueConnectionFactory;
import com.hidden.data.queue.model.SimplifiedBookRow;

class SimplifiedRowConsumerStub extends SimplifiedRowConsumer {

	private List<List<SimplifiedBookRow>> messagesReceived = new ArrayList<List<SimplifiedBookRow>>();
	private int messagesReceivedBeforeClosingConnection;

	SimplifiedRowConsumerStub(QueueConnectionFactory connectionFactory,
			int messagesReceivedBeforeClosingConnection) {
		super(connectionFactory);
		this.messagesReceivedBeforeClosingConnection = messagesReceivedBeforeClosingConnection;
		if (this.messagesReceivedBeforeClosingConnection == 0) {
			closeConnection();
		}
	}

	@Override
	protected void consumeRows(List<SimplifiedBookRow> rows) {
		messagesReceived.add(rows);
		if (messagesReceived.size() == messagesReceivedBeforeClosingConnection) {
			closeConnection();
		}
	}

	public void assertConsumeRowsWith(List<List<SimplifiedBookRow>> expectedRows) {
		Assert.assertEquals(expectedRows.size(), messagesReceived.size());
		for (int i = 0; i < expectedRows.size(); i++) {
			Assert.assertEquals(expectedRows.get(i), messagesReceived.get(i));
		}
	}
}
