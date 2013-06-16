package com.hidden.data.producer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hidden.data.queue.connection.ProducerConnection;
import com.hidden.data.queue.connection.QueueConnection;
import com.hidden.data.queue.model.FilterItem;

class ProducerConnectionStub extends ProducerConnection {

	private List<FilterItem> sentItems = new ArrayList<FilterItem>();
	private boolean connectionClosed;

	ProducerConnectionStub(QueueConnection connection) {
		super(connection);
	}

	@Override
	public void sendMessage(Serializable message) {
		sentItems.add((FilterItem) message);
	}

	int getNumberOfMessagesSent() {
		return sentItems.size();
	}

	List<FilterItem> getSentItems() {
		return sentItems;
	}

	@Override
	public void close() {
		connectionClosed = true;
	}

	protected boolean isConnectionClosed() {
		return connectionClosed;
	}

}
