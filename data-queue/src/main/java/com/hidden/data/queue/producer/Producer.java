package com.hidden.data.queue.producer;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.hidden.data.queue.QueueConnection;
import com.hidden.data.queue.model.SimplifiedBookRow;

public class Producer {

	private static final Logger LOG = Logger.getLogger(Producer.class);

	public static void main(String[] args) throws JMSException, IOException {
		QueueConnection connection = new QueueConnection();
		connection.open();
		MessageProducer producer = connection.createProducer();

		// We will send a small text message saying 'Hello' in Japanese
		boolean[] content = new boolean[] { false, false, true };
		SimplifiedBookRow row = new SimplifiedBookRow(content, 3,
				Integer.valueOf(23));
		ObjectMessage objectMessage = connection.createObjectMessage(row);

		// Here we are sending the message!
		producer.send(objectMessage);
		LOG.debug("Sent message");

		connection.close();
	}
}