package com.hidden.data.queue;

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

public class QueueConnection {

	private static final String QUEUE_PROPERTY_KEY = "queue";
	private static final FileProperties PROPERTIES = PropertiesFactory
			.getInstance().getPropertiesFromRelativePath("/queue.properties");
	private Connection connection;
	private Destination destination;
	private Session session;
	private ConnectionFactory connectionFactory;

	public QueueConnection(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public void open() {
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

	public void close() {
		try {
			assertConnectionAlreadyOpen();
			connection.close();
		} catch (JMSException e) {
			throw new QueueException(e);
		}
	}

	public MessageConsumer createConsumer() {
		try {
			return session.createConsumer(destination);
		} catch (JMSException e) {
			throw new QueueException(e);
		}
	}

	private void assertConnectionAlreadyOpen() {
		if (connection == null) {
			throw new QueueException("The connection must be opened before");
		}
	}

	public MessageProducer createProducer() {
		try {
			return session.createProducer(destination);
		} catch (JMSException e) {
			throw new QueueException(e);
		}
	}

	public ObjectMessage createObjectMessage(Serializable serialiazbleObject) {
		try {
			return session.createObjectMessage(serialiazbleObject);
		} catch (JMSException e) {
			throw new QueueException(e);
		}
	}
}
