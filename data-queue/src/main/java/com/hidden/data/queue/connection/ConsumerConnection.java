package com.hidden.data.queue.connection;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;

import com.hidden.data.queue.exception.QueueException;

public class ConsumerConnection {

	private static final int ONE_SECOND = 1000;

	private QueueConnection connection;
	private MessageConsumer consumer;

	public ConsumerConnection(QueueConnection connection) {
		this.connection = connection;
		connection.open();
		consumer = connection.createConsumer();
	}

	public Serializable waitUntilReceive() {
		try {
			ObjectMessage message = (ObjectMessage) consumer
					.receive(ONE_SECOND);
			if (message == null) {
				return new TimeOut();
			}
			return message.getObject();
		} catch (JMSException e) {
			throw new QueueException(e);
		}
	}

	public void close() {
		connection.close();
	}

}
