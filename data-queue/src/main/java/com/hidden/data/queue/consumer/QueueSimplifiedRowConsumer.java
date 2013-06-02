package com.hidden.data.queue.consumer;

import java.util.List;

import com.hidden.data.queue.model.SimplifiedBookRow;

public interface QueueSimplifiedRowConsumer {

	void consumeRows(List<SimplifiedBookRow> rows);

}
