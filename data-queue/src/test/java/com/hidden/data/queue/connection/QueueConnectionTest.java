package com.hidden.data.queue.connection;

import java.io.Serializable;

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

	private static final Serializable SERIALIZABLE_OBJECT = Integer.valueOf(0);
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
		victim = new QueueConnection(connectionFactory, StringUtils.EMPTY);
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
	public void close_DoneThroughConnection() throws JMSException {
		givenOpenConnection();
		whenClose();
		thenColsedThroughConnect();
	}

	private void thenColsedThroughConnect() throws JMSException {
		Mockito.verify(connection).close();
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

	@Test
	public void createConsumer_ThrowsJmsEx_ThrowsQueueEx() throws JMSException {
		expectQueueEx();
		givenJmsExCreatingConsumer();
		whenCreateConsumer();
	}

	private void givenJmsExCreatingConsumer() throws JMSException {
		givenOpenConnection();
		Mockito.doThrow(new JMSException(StringUtils.EMPTY)).when(session)
				.createConsumer(destination);
	}

	@Test
	public void createConsumer_ConnectionNotOpened_ThrowsQueueEx() {
		expectQueueEx();
		createVictim();
		whenCreateConsumer();
	}

	@Test
	public void createProducer_DoneThroughSession() throws JMSException {
		givenOpenConnection();
		whenCreateProducer();
		thenCreateProducerShouldBeTroughSession();
	}

	private void whenCreateProducer() {
		victim.createProducer();
	}

	private void thenCreateProducerShouldBeTroughSession() throws JMSException {
		Mockito.verify(session).createProducer(destination);
	}

	@Test
	public void createProducer_ThrowsJmsEx_ThrowsQueueEx() throws JMSException {
		expectQueueEx();
		givenJmsExCreatingProducer();
		whenCreateProducer();
	}

	private void givenJmsExCreatingProducer() throws JMSException {
		givenOpenConnection();
		Mockito.doThrow(new JMSException(StringUtils.EMPTY)).when(session)
				.createProducer(destination);
	}

	@Test
	public void createProducer_ConnectionNotOpened_ThrowsQueueEx() {
		expectQueueEx();
		createVictim();
		whenCreateProducer();
	}

	@Test
	public void createObjectMessage_DoneThroughSession() throws JMSException {
		givenOpenConnection();
		whenCreateObjectMessage();
		thenCreateObjectMessageShouldBeTroughSession();
	}

	private void whenCreateObjectMessage() {
		victim.createObjectMessage(SERIALIZABLE_OBJECT);
	}

	private void thenCreateObjectMessageShouldBeTroughSession()
			throws JMSException {
		Mockito.verify(session).createObjectMessage(SERIALIZABLE_OBJECT);
	}

	@Test
	public void createObjectMessage_ThrowsJmsEx_ThrowsQueueEx()
			throws JMSException {
		expectQueueEx();
		givenJmsExCreatingObjectMessage();
		whenCreateObjectMessage();
	}

	private void givenJmsExCreatingObjectMessage() throws JMSException {
		givenOpenConnection();
		Mockito.doThrow(new JMSException(StringUtils.EMPTY)).when(session)
				.createObjectMessage(SERIALIZABLE_OBJECT);
	}

	@Test
	public void createObjectMessage_ConnectionNotOpened_ThrowsQueueEx() {
		expectQueueEx();
		createVictim();
		whenCreateObjectMessage();
	}

}
