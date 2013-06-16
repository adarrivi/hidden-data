package com.hidden.data.queue.consumer;

import java.io.Serializable;

import com.hidden.data.queue.connection.ConsumerConnection;
import com.hidden.data.queue.connection.QueueConnectionFactory;
import com.hidden.data.queue.connection.TimeOut;
import com.hidden.data.queue.model.FilterItem;

public abstract class RowConsumerTemplate {

	private QueueConnectionFactory connectionFactory;
	private ConsumerConnection connection;
	private boolean closeConnection;

	public void setConnectionFactory(QueueConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public void receiveMessages() {
		connection = connectionFactory.createFilterConsumerConnection();
		while (closeConnection == false) {
			Serializable objectRetreived = connection.waitUntilReceive();
			if (!(objectRetreived instanceof TimeOut)) {
				consumeRows((FilterItem) objectRetreived);
			}
		}
		connection.close();
	}

	protected abstract void consumeRows(FilterItem filterItem);

	public void closeConnection() {
		closeConnection = true;
	}

}
