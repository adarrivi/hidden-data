package com.hidden.data.queue.connection.activemq;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.hidden.data.common.property.FileProperties;
import com.hidden.data.common.property.PropertiesFactory;
import com.hidden.data.queue.connection.ConsumerConnection;
import com.hidden.data.queue.connection.ProducerConnection;
import com.hidden.data.queue.connection.QueueConnection;
import com.hidden.data.queue.connection.QueueConnectionFactory;

public class ConnectionActiveMqFactory implements QueueConnectionFactory {

	private static final String AGGREGATE_QUEUE = "aggregateQueue";
	private static final String FILTER_QUEUE = "filterQueue";
	private static final String URL_KEY = "url";
	private static final String VM_URL_KEY = "vmUrl";
	private static String url = URL_KEY;
	private static FileProperties PROPERTIES = PropertiesFactory.getInstance()
			.getPropertiesFromRelativePath("/queue.properties");
	private static final ConnectionActiveMqFactory INSTANCE = new ConnectionActiveMqFactory();

	private ConnectionActiveMqFactory() {
		// limiting scope
	}

	public static ConnectionActiveMqFactory getInstance() {
		return INSTANCE;
	}

	public void setVmQueue() {
		url = VM_URL_KEY;
	}

	@Override
	public ConsumerConnection createFilterConsumerConnection() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				PROPERTIES.getProperty(url));
		return new ConsumerConnection(new QueueConnection(connectionFactory,
				PROPERTIES.getProperty(FILTER_QUEUE)));
	}

	@Override
	public ProducerConnection createFilterProducerConnection() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				PROPERTIES.getProperty(url));
		return new ProducerConnection(new QueueConnection(connectionFactory,
				PROPERTIES.getProperty(FILTER_QUEUE)));
	}

	@Override
	public ConsumerConnection createAggregateConsumerConnection() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				PROPERTIES.getProperty(url));
		return new ConsumerConnection(new QueueConnection(connectionFactory,
				PROPERTIES.getProperty(AGGREGATE_QUEUE)));
	}

	@Override
	public ProducerConnection createAggregateProducerConnection() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				PROPERTIES.getProperty(url));
		return new ProducerConnection(new QueueConnection(connectionFactory,
				PROPERTIES.getProperty(AGGREGATE_QUEUE)));

	}

}
