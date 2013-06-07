package com.hidden.data.queue.consumer;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.common.property.FileProperties;
import com.common.property.PropertiesFactory;
import com.hidden.data.queue.connection.ConnectionActiveMqFactory;
import com.hidden.data.queue.connection.JmsConsumerConnection;
import com.hidden.data.queue.model.SimplifiedBookRow;

public class Consumer {

	private static final Logger LOG = Logger.getLogger(ConsumerTest.class);
	private static final FileProperties PROPERTIES = PropertiesFactory
			.getInstance().getPropertiesFromRelativePath("/queue.properties");

	private static final Consumer INSTANCE = new Consumer();

	public static Consumer getInstance() {
		return INSTANCE;
	}

	private Consumer() {
		// To limit scope
	}

	public void run() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				PROPERTIES.getProperty("url"));

		JmsConsumerConnection consumerConnection = ConnectionActiveMqFactory
				.getInstance().createConsumerConnection(connectionFactory);

		SimplifiedBookRow row = (SimplifiedBookRow) consumerConnection
				.waitUntilReceive();

		LOG.debug("Received message " + row.getRowNumber() + " "
				+ row.getBookId().intValue());
		consumerConnection.close();
	}
}