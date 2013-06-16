package com.hidden.data.queue.consumer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.hidden.data.queue.model.FilterItem;

class RowConsumerStub extends RowConsumerTemplate {

	private List<FilterItem> messagesReceived = new ArrayList<FilterItem>();
	private int messagesReceivedBeforeClosingConnection;

	RowConsumerStub(int messagesReceivedBeforeClosingConnection) {
		this.messagesReceivedBeforeClosingConnection = messagesReceivedBeforeClosingConnection;
		if (this.messagesReceivedBeforeClosingConnection == 0) {
			closeConnection();
		}
	}

	@Override
	protected void consumeRows(FilterItem item) {
		messagesReceived.add(item);
		if (messagesReceived.size() == messagesReceivedBeforeClosingConnection) {
			closeConnection();
		}
	}

	public void assertConsumeRowsWith(List<FilterItem> expectedRows) {
		Assert.assertEquals(expectedRows.size(), messagesReceived.size());
	}
}
