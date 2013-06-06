package com.hidden.data.queue.connection;

import javax.jms.ConnectionFactory;


public class ConnectionActiveMqFactory {

	private static final ConnectionActiveMqFactory INSTANCE = new ConnectionActiveMqFactory();

	private ConnectionActiveMqFactory() {
		// limiting scope
	}

	public static ConnectionActiveMqFactory getInstance() {
		return INSTANCE;
	}

	public JmsConsumerConnection createConsumerConnection(
			ConnectionFactory connectionFactory) {
		return new ConsumerConnectionActiveMq(new QueueConnectionActiveMq(
				connectionFactory));
	}

	public JmsProducerConnection createProducerConnection(
			ConnectionFactory connectionFactory) {
		return new ProducerConnectionActiveMq(new QueueConnectionActiveMq(
				connectionFactory));
	}

}
