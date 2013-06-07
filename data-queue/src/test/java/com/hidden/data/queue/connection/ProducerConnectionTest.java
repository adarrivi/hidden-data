package com.hidden.data.queue.connection;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.queue.exception.QueueException;

public class ProducerConnectionTest {

	private static final Serializable SERIALIZABLE_OBJECT = Integer.valueOf(0);
	private ProducerConnection victim;
	@Mock
	private QueueConnection connection;
	@Mock
	private MessageProducer producer;
	@Mock
	private ObjectMessage objectMessage;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void newInstance_OpensQueueConnection() {
		createVictim();
		thenShouldHaveOpenedConnection();
	}

	private void createVictim() {
		Mockito.when(connection.createProducer()).thenReturn(producer);
		Mockito.when(connection.createObjectMessage(SERIALIZABLE_OBJECT))
				.thenReturn(objectMessage);
		victim = new ProducerConnection(connection);
	}

	private void thenShouldHaveOpenedConnection() {
		Mockito.verify(connection).open();
	}

	@Test
	public void sendMessage_ThrowsJmsEx_ThrowsQueueEx() throws JMSException {
		expectQueueEx();
		givenJmsExCreatingMessage();
		whenSendMessage();
	}

	private void expectQueueEx() {
		expectedException.expect(QueueException.class);
	}

	private void givenJmsExCreatingMessage() throws JMSException {
		createVictim();
		Mockito.doThrow(new JMSException(StringUtils.EMPTY)).when(producer)
				.send(objectMessage);
	}

	private void whenSendMessage() {
		createVictim();
		victim.sendMessage(SERIALIZABLE_OBJECT);
	}

	@Test
	public void sendMessage_DoneThrougProducer() throws JMSException {
		createVictim();
		whenSendMessage();
		thenShouldBeSentByProducer();
	}

	private void thenShouldBeSentByProducer() throws JMSException {
		Mockito.verify(producer).send(objectMessage);
	}

	@Test
	public void close_ClosesConnection() {
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
