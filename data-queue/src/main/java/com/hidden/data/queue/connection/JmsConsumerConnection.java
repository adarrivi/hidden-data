package com.hidden.data.queue.connection;

import java.io.Serializable;

public interface JmsConsumerConnection {

	void close();

	Serializable waitUntilReceive();

}
