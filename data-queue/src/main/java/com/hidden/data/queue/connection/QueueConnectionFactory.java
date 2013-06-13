package com.hidden.data.queue.connection;

public interface QueueConnectionFactory {

	ConsumerConnection createFilterConsumerConnection();

	ProducerConnection createFilterProducerConnection();

	ConsumerConnection createAggregateConsumerConnection();

	ProducerConnection createAggregateProducerConnection();

}
