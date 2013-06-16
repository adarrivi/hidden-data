package com.hidden.data.queue.consumer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.hidden.data.queue.connection.QueueConnectionFactory;
import com.hidden.data.queue.model.FilterItem;

class RowConsumerStub extends RowConsumerTemplate {

	private List<List<FilterItem>> messagesReceived = new ArrayList<List<FilterItem>>();
	private int messagesReceivedBeforeClosingConnection;

	RowConsumerStub(QueueConnectionFactory connectionFactory,
			int messagesReceivedBeforeClosingConnection) {
		super(connectionFactory);
		this.messagesReceivedBeforeClosingConnection = messagesReceivedBeforeClosingConnection;
		if (this.messagesReceivedBeforeClosingConnection == 0) {
			closeConnection();
		}
	}

	@Override
	protected void consumeRows(List<FilterItem> rows) {
		messagesReceived.add(rows);
		if (messagesReceived.size() == messagesReceivedBeforeClosingConnection) {
			closeConnection();
		}
	}

	public void assertConsumeRowsWith(List<List<FilterItem>> expectedRows) {
		Assert.assertEquals(expectedRows.size(), messagesReceived.size());
		for (int i = 0; i < expectedRows.size(); i++) {
			Assert.assertEquals(expectedRows.get(i), messagesReceived.get(i));
		}
	}
}
