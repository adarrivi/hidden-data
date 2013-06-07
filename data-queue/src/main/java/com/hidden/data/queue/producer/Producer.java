package com.hidden.data.queue.producer;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.common.property.FileProperties;
import com.common.property.PropertiesFactory;
import com.hidden.data.queue.connection.ConnectionActiveMqFactory;
import com.hidden.data.queue.connection.JmsProducerConnection;
import com.hidden.data.queue.model.SimplifiedBookRow;

public class Producer {

	private static final Logger LOG = Logger.getLogger(Producer.class);

	private static final FileProperties PROPERTIES = PropertiesFactory
			.getInstance().getPropertiesFromRelativePath("/queue.properties");

	private static final Producer INSTANCE = new Producer();

	public static Producer getInstance() {
		return INSTANCE;
	}

	private Producer() {
		// To limit scope
	}

	public void run() {

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				PROPERTIES.getProperty("url"));

		JmsProducerConnection producerConnection = ConnectionActiveMqFactory
				.getInstance().createProducerConnection(connectionFactory);

		boolean[] content = new boolean[] { false, false, true };
		SimplifiedBookRow row = new SimplifiedBookRow(content, 3,
				Integer.valueOf(23));

		producerConnection.sendMessage(row);
		LOG.debug("Sent message");
		producerConnection.close();
	}
}