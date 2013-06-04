package com.hidden.data.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.queue.exception.QueueException;

public class QueueConnectionTest {

	@Mock
	private Connection connection;
	@Mock
	private Session session;
	@Mock
	private ConnectionFactory connectionFactory;
	@Mock
	private Queue destination;
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private QueueConnection victim;

	@Before
	public void setUp() throws JMSException {
		MockitoAnnotations.initMocks(this);
		Mockito.when(connectionFactory.createConnection()).thenReturn(
				connection);
		Mockito.when(connection.createSession(false, Session.AUTO_ACKNOWLEDGE))
				.thenReturn(session);
		Mockito.when(session.createQueue(Matchers.anyString())).thenReturn(
				destination);
	}

	@Test
	public void open_ThrowsJmsEx_ThrowsQueueEx() throws JMSException {
		expectQueueEx();
		givenThrowsJmsExInStart();
		whenOpen();
	}

	private void expectQueueEx() {
		expectedException.expect(QueueException.class);
	}

	private void givenThrowsJmsExInStart() throws JMSException {
		Mockito.doThrow(new JMSException(StringUtils.EMPTY)).when(connection)
				.start();
		createVictim();
	}

	private void createVictim() {
		victim = new QueueConnection(connectionFactory);
	}

	private void whenOpen() {
		victim.open();
	}

	@Test
	public void close_ThrowsJmsEx_ThrowsQueueEx() throws JMSException {
		expectQueueEx();
		givenThrowJmsExWhenClosing();
		givenOpenConnection();
		whenClose();
	}

	private void givenThrowJmsExWhenClosing() throws JMSException {
		Mockito.doThrow(new JMSException(StringUtils.EMPTY)).when(connection)
				.close();
		createVictim();
	}

	private void whenClose() {
		victim.close();
	}

	@Test
	public void close_BeforeOpening_ThrowsQueueEx() {
		expectQueueEx();
		createVictim();
		whenClose();
	}

	@Test
	public void createConsumer_DoneThroughSession() throws JMSException {
		givenOpenConnection();
		whenCreateConsumer();
		thenCreateConsumerShouldBeTroughSession();
	}

	private void givenOpenConnection() {
		createVictim();
		whenOpen();
	}

	private void whenCreateConsumer() {
		victim.createConsumer();
	}

	private void thenCreateConsumerShouldBeTroughSession() throws JMSException {
		Mockito.verify(session).createConsumer(destination);
	}

}
