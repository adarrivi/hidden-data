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

import com.hidden.data.queue.exception.QueueException;

public class QueueConnection {

	private Connection connection;
	private Destination destination;
	private Session session;
	private ConnectionFactory connectionFactory;
	private String destinationQueue;

	public QueueConnection(ConnectionFactory connectionFactory,
			String destinationQueue) {
		this.connectionFactory = connectionFactory;
		this.destinationQueue = destinationQueue;
	}

	void open() {
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(destinationQueue);
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
