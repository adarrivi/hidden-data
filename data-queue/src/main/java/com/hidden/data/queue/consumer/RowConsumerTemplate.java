package com.hidden.data.queue.consumer;

import java.io.Serializable;
import java.util.List;

import com.hidden.data.queue.connection.ConsumerConnection;
import com.hidden.data.queue.connection.QueueConnectionFactory;
import com.hidden.data.queue.connection.TimeOut;
import com.hidden.data.queue.model.FilterItem;

public abstract class RowConsumerTemplate {

	private ConsumerConnection connection;
	private boolean closeConnection;

	protected RowConsumerTemplate(QueueConnectionFactory connectionFactory) {
		connection = connectionFactory.createFilterConsumerConnection();

	}

	@SuppressWarnings("unchecked")
	public void receiveMessages() {
		while (closeConnection == false) {
			Serializable objectRetreived = connection.waitUntilReceive();
			if (!(objectRetreived instanceof TimeOut)) {
				consumeRows((List<FilterItem>) objectRetreived);
			}
		}
		connection.close();
	}

	protected abstract void consumeRows(List<FilterItem> rows);

	public void closeConnection() {
		closeConnection = true;
	}

}
