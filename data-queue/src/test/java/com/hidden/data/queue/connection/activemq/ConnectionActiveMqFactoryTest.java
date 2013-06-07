package com.hidden.data.queue.connection.activemq;

import org.junit.Assert;
import org.junit.Test;

import com.hidden.data.queue.connection.ConsumerConnection;
import com.hidden.data.queue.connection.ProducerConnection;
import com.hidden.data.queue.connection.QueueConnectionFactory;

public class ConnectionActiveMqFactoryTest {

	private QueueConnectionFactory victim = ConnectionActiveMqFactory
			.getInstance();

	@Test
	public void createConsumerConnection_ReturnsConnection() {
		ConsumerConnection consumerConnection = victim
				.createConsumerConnection();
		Assert.assertNotNull(consumerConnection);
	}

	@Test
	public void createProducerConnection_ReturnsConnection() {
		ProducerConnection producerConnection = victim
				.createProducerConnection();
		Assert.assertNotNull(producerConnection);
	}

}
