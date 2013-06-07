package com.hidden.data.queue.connection;

public interface QueueConnectionFactory {

	ConsumerConnection createConsumerConnection();

	ProducerConnection createProducerConnection();

}
