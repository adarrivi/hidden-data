package com.hidden.data.queue.connection.activemq;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hidden.data.queue.connection.ConsumerConnection;
import com.hidden.data.queue.connection.ProducerConnection;
import com.hidden.data.queue.connection.QueueConnectionFactory;

public class ConnectionActiveMqFactoryTest {

	private QueueConnectionFactory victim = ConnectionActiveMqFactory
			.getInstance();

	@Before
	public void setUp() {
		((ConnectionActiveMqFactory) victim).setVmQueue();
	}

	@Test
	public void createFilterConsumerConnection_ReturnsNotNullConnection() {
		ConsumerConnection consumerConnection = victim
				.createFilterConsumerConnection();
		Assert.assertNotNull(consumerConnection);
	}

	@Test
	public void createFilterProducerConnection_ReturnsNotNullConnection() {
		ProducerConnection producerConnection = victim
				.createFilterProducerConnection();
		Assert.assertNotNull(producerConnection);
	}

	@Test
	public void createAggregateConsumerConnection_ReturnsNotNullConnection() {
		ConsumerConnection consumerConnection = victim
				.createAggregateConsumerConnection();
		Assert.assertNotNull(consumerConnection);
	}

	@Test
	public void createAggregateProducerConnection_ReturnsNotNullConnection() {
		ProducerConnection producerConnection = victim
				.createAggregateProducerConnection();
		Assert.assertNotNull(producerConnection);
	}

}
