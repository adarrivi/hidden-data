package com.hidden.data.queue.connection;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;

import com.hidden.data.queue.exception.QueueException;

class ConsumerConnectionActiveMq implements JmsConsumerConnection {

	private QueueConnectionActiveMq connection;

	private MessageConsumer consumer;

	ConsumerConnectionActiveMq(QueueConnectionActiveMq connection) {
		this.connection = connection;
		connection.open();
		consumer = connection.createConsumer();

	}

	@Override
	public Serializable waitUntilReceive() {
		try {
			ObjectMessage message = (ObjectMessage) consumer.receive();
			return message.getObject();
		} catch (JMSException e) {
			throw new QueueException(e);
		}
	}

	@Override
	public void close() {
		connection.close();
	}

}
