package com.hidden.data.queue;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueConnection {

	private Connection connection;
	private Destination destination;
	private Session session;

	public void open() throws JMSException, IOException {

		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream("/queue.properties"));

		// Getting JMS connection from the server
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				properties.getProperty("url"));
		connection = connectionFactory.createConnection();
		connection.start();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		destination = session.createQueue(properties.getProperty("queue"));
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
