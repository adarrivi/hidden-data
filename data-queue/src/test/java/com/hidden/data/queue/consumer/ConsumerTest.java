package com.hidden.data.queue.consumer;

import org.junit.Test;

import com.hidden.data.queue.producer.Producer;

public class ConsumerTest {

	@Test
	public void main() {
		Producer.getInstance().run();
		Consumer.getInstance().run();
	}

}