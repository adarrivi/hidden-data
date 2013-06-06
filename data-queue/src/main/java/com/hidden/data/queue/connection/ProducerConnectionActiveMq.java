package com.hidden.data.queue.connection;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;

import com.hidden.data.queue.exception.QueueException;

class ProducerConnectionActiveMq implements JmsProducerConnection {

	private QueueConnectionActiveMq connection;

	private MessageProducer producer;

	ProducerConnectionActiveMq(QueueConnectionActiveMq connection) {
		this.connection = connection;
		connection.open();
		producer = connection.createProducer();

	}

	@Override
	public void sendMessage(Serializable message) {
		try {
			ObjectMessage objectMessage = connection
					.createObjectMessage(message);
			producer.send(objectMessage);
		} catch (JMSException e) {
			throw new QueueException(e);
		}
	}

	@Override
	public void close() {
		connection.close();
	}

}
