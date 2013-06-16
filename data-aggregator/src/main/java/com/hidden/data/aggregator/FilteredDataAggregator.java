package com.hidden.data.aggregator;

import java.util.List;

import com.hidden.data.queue.connection.QueueConnectionFactory;
import com.hidden.data.queue.consumer.RowConsumerTemplate;
import com.hidden.data.queue.model.FilterItem;

public class FilteredDataAggregator extends RowConsumerTemplate {

	protected FilteredDataAggregator(QueueConnectionFactory connectionFactory) {
		super(connectionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void consumeRows(List<FilterItem> rows) {
		// TODO Auto-generated constructor stub
	}

}
