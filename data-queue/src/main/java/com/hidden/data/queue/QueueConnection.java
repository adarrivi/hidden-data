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

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueConnection {

	// URL of the JMS server. DEFAULT_BROKER_URL will just mean
	// that JMS server is on localhost
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	// Name of the queue we will be sending messages to
	private static String subject = "TESTQUEUE";

	private Connection connection;
	private Destination destination;
	private Session session;

	public void open() throws JMSException {
		// Getting JMS connection from the server
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		connection = connectionFactory.createConnection();
		connection.start();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		destination = session.createQueue(subject);
	}

	public void close() throws JMSException {
		connection.close();
	}

	public MessageConsumer createConsumer() throws JMSException {
		return session.createConsumer(destination);
	}

	public MessageProducer createProducer() throws JMSException {
		return session.createProducer(destination);
	}

	public ObjectMessage createObjectMessage(Serializable serialiazbleObject)
			throws JMSException {
		return session.createObjectMessage(serialiazbleObject);
	}

}
