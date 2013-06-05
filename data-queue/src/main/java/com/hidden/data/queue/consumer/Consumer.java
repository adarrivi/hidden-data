package com.hidden.data.queue.consumer;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.common.property.FileProperties;
import com.common.property.PropertiesFactory;
import com.hidden.data.queue.connection.QueueConnection;
import com.hidden.data.queue.model.SimplifiedBookRow;

public class Consumer {

	private static final Logger LOG = Logger.getLogger(Consumer.class);
	private static final FileProperties PROPERTIES = PropertiesFactory
			.getInstance().getPropertiesFromRelativePath("/queue.properties");

	public static void main(String[] args) throws JMSException {

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				PROPERTIES.getProperty("url"));

		QueueConnection connection = new QueueConnection(connectionFactory);
		connection.open();

		MessageConsumer consumer = connection.createConsumer();

		// Here we receive the message.
		// By default this call is blocking, which means it will wait
		// for a message to arrive on the queue.
		Message message = consumer.receive();

		// There are many types of Message and TextMessage
		// is just one of them. Producer sent us a TextMessage
		// so we must cast to it to get access to its .getText()
		// method.
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			SimplifiedBookRow row = (SimplifiedBookRow) objectMessage
					.getObject();
			LOG.debug("Received message " + row.getRowNumber() + " "
					+ row.getBookId().intValue());
		}
		connection.close();
	}
}