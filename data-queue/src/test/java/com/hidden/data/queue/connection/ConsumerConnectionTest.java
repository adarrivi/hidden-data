package com.hidden.data.queue.connection;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.queue.exception.QueueException;

public class ConsumerConnectionTest {

	private static final Serializable SERIALIZABLE_OBJECT = Integer.valueOf(0);
	private ConsumerConnection victim;
	private Serializable receivedSerializable;
	@Mock
	private QueueConnection connection;
	@Mock
	private MessageConsumer consumer;
	@Mock
	private ObjectMessage objectMessage;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void newInstance_OpensQueueConnection() throws JMSException {
		createVictim();
		thenShouldHaveOpenedConnection();
	}

	private void createVictim() throws JMSException {
		Mockito.when(connection.createConsumer()).thenReturn(consumer);
		Mockito.when(consumer.receive()).thenReturn(objectMessage);
		Mockito.when(objectMessage.getObject()).thenReturn(SERIALIZABLE_OBJECT);
		victim = new ConsumerConnection(connection);
	}

	private void thenShouldHaveOpenedConnection() {
		Mockito.verify(connection).open();
	}

	@Test
	public void waitUntilReceive_ThrowsJmsEx_ThrowsQueueEx()
			throws JMSException {
		expectQueueEx();
		givenJmsExReceivingMessage();
		whenWaitUntilReceiveMessage();
	}

	private void expectQueueEx() {
		expectedException.expect(QueueException.class);
	}

	private void givenJmsExReceivingMessage() throws JMSException {
		createVictim();
		Mockito.doThrow(new JMSException(StringUtils.EMPTY)).when(consumer)
				.receive();
	}

	private void whenWaitUntilReceiveMessage() {
		receivedSerializable = victim.waitUntilReceive();
	}

	@Test
	public void waitUntilReceive_DoneThrougConsumer() throws JMSException {
		createVictim();
		whenWaitUntilReceiveMessage();
		thenShouldWaitForConnection();
	}

	private void thenShouldWaitForConnection() throws JMSException {
		Mockito.verify(consumer).receive();
		Assert.assertEquals(receivedSerializable, SERIALIZABLE_OBJECT);
	}

	@Test
	public void close_ClosesConnection() throws JMSException {
		createVictim();
		whenClose();
		thenClosesConnection();
	}

	private void whenClose() {
		victim.close();
	}

	private void thenClosesConnection() {
		Mockito.verify(connection).close();
	}

}
