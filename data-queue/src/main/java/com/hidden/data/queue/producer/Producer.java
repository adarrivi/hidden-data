package com.hidden.data.queue.producer;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.common.property.FileProperties;
import com.common.property.PropertiesFactory;
import com.hidden.data.queue.QueueConnection;
import com.hidden.data.queue.model.SimplifiedBookRow;

public class Producer {

	private static final Logger LOG = Logger.getLogger(Producer.class);

	private static final FileProperties PROPERTIES = PropertiesFactory
			.getInstance().getPropertiesFromRelativePath("/queue.properties");

	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				PROPERTIES.getProperty("url"));

		QueueConnection connection = new QueueConnection(connectionFactory);
		connection.open();
		MessageProducer producer = connection.createProducer();

		// We will send a small text message saying 'Hello' in Japanese
		boolean[] content = new boolean[] { false, false, true };
		SimplifiedBookRow row = new SimplifiedBookRow(content, 3,
				Integer.valueOf(23));
		ObjectMessage objectMessage = connection.createObjectMessage(row);

		// Here we are sending the message!
		producer.send(objectMessage);
		LOG.debug("Sent message");

		connection.close();
	}
}