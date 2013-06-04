package com.hidden.data.queue;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;

import com.hidden.data.queue.exception.QueueException;

public class ProducerQueueConnection {

	private QueueConnection connection;

	private MessageProducer producer;

	public ProducerQueueConnection(QueueConnection connection) {
		this.connection = connection;
	}

	public void open() {
		connection.open();
		producer = connection.createProducer();
	}

	public void sendMessage(Serializable message) {
		try {
			ObjectMessage objectMessage = connection
					.createObjectMessage(message);
			producer.send(objectMessage);
		} catch (JMSException e) {
			throw new QueueException(e);
		}
	}

}
