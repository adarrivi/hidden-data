package com.hidden.data.queue.connection.activemq;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.common.property.FileProperties;
import com.common.property.PropertiesFactory;
import com.hidden.data.queue.connection.ConsumerConnection;
import com.hidden.data.queue.connection.ProducerConnection;
import com.hidden.data.queue.connection.QueueConnection;
import com.hidden.data.queue.connection.QueueConnectionFactory;

public class ConnectionActiveMqFactory implements QueueConnectionFactory {

	private static final String URL_KEY = "url";
	private static final FileProperties PROPERTIES = PropertiesFactory
			.getInstance().getPropertiesFromRelativePath("/queue.properties");
	private static final ConnectionActiveMqFactory INSTANCE = new ConnectionActiveMqFactory();

	private ConnectionActiveMqFactory() {
		// limiting scope
	}

	public static ConnectionActiveMqFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public ConsumerConnection createConsumerConnection() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				PROPERTIES.getProperty(URL_KEY));
		return new ConsumerConnection(new QueueConnection(connectionFactory));
	}

	@Override
	public ProducerConnection createProducerConnection() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				PROPERTIES.getProperty(URL_KEY));
		return new ProducerConnection(new QueueConnection(connectionFactory));
	}

}
