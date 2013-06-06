package com.hidden.data.queue.connection;

import java.io.Serializable;

public interface JmsProducerConnection {

	void close();

	void sendMessage(Serializable message);

}
