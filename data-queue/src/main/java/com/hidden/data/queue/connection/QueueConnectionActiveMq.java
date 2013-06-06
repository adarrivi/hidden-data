package com.hidden.data.queue.connection;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import com.common.property.FileProperties;
import com.common.property.PropertiesFactory;
import com.hidden.data.queue.exception.QueueException;

class QueueConnectionActiveMq {

	private static final String QUEUE_PROPERTY_KEY = "queue";
	private static final FileProperties PROPERTIES = PropertiesFactory
			.getInstance().getPropertiesFromRelativePath("/queue.properties");
	private Connection connection;
	private Destination destination;
	private Session session;
	private ConnectionFactory connectionFactory;

	QueueConnectionActiveMq(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	void open() {
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(PROPERTIES
					.getProperty(QUEUE_PROPERTY_KEY));
		} catch (JMSException ex) {
			throw new QueueException(ex);
		}
	}

	void close() {
		try {
			assertConnectionAlreadyOpen();
			connection.close();
		} catch (JMSException e) {
			throw new QueueException(e);
		}
	}

	MessageConsumer createConsumer() {
		try {
			assertConnectionAlreadyOpen();
			return session.createConsumer(destination);
		} catch (JMSException e) {
			throw new QueueException(e);
		}
	}

	void assertConnectionAlreadyOpen() {
		if (connection == null) {
			throw new QueueException("The connection must be opened before");
		}
	}

	MessageProducer createProducer() {
		try {
			assertConnectionAlreadyOpen();
			return session.createProducer(destination);
		} catch (JMSException e) {
			throw new QueueException(e);
		}
	}

	ObjectMessage createObjectMessage(Serializable serialiazbleObject) {
		try {
			assertConnectionAlreadyOpen();
			return session.createObjectMessage(serialiazbleObject);
		} catch (JMSException e) {
			throw new QueueException(e);
		}
	}
}
