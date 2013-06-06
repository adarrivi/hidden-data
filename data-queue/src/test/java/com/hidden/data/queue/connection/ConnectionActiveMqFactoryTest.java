package com.hidden.data.queue.connection;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ConnectionActiveMqFactoryTest {

	private ConnectionActiveMqFactory victim = ConnectionActiveMqFactory
			.getInstance();

	@Mock
	private ConnectionFactory connectionFactory;
	@Mock
	private Connection connection;
	@Mock
	private Session session;

	@Before
	public void setUp() throws JMSException {
		MockitoAnnotations.initMocks(this);
		Mockito.when(connectionFactory.createConnection()).thenReturn(
				connection);
		Mockito.when(
				connection.createSession(Matchers.anyBoolean(),
						Matchers.anyInt())).thenReturn(session);
	}

	@Test
	public void createConsumerConnection_ReturnsConnection() {
		JmsConsumerConnection consumerConnection = victim
				.createConsumerConnection(connectionFactory);
		Assert.assertNotNull(consumerConnection);
	}

	@Test
	public void createProducerConnection_ReturnsConnection() {
		JmsProducerConnection producerConnection = victim
				.createProducerConnection(connectionFactory);
		Assert.assertNotNull(producerConnection);
	}

}
