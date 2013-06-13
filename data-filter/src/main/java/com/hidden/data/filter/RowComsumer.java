package com.hidden.data.filter;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.hidden.data.queue.connection.ProducerConnection;
import com.hidden.data.queue.connection.activemq.ConnectionActiveMqFactory;
import com.hidden.data.queue.consumer.RowConsumerTemplate;
import com.hidden.data.queue.model.SimplifiedBookRow;

public class RowComsumer extends RowConsumerTemplate {

	private static final Logger LOG = Logger.getLogger(RowComsumer.class);
	private Pattern pattern;
	private ProducerConnection producerConnection;

	public static void main(String[] args) {
		// boolean[][] rowIntheMiddle = new boolean[][] { { true, false, false
		// },
		// { true, false, false }, { true, false, false } };
		boolean[][] smallPyramid = new boolean[][] {
				{ false, false, false, true, false, false, false },
				{ false, false, true, false, true, false, false },
				{ false, true, false, false, false, true, false } };

		RowComsumer rowComsumer = new RowComsumer(new Pattern(smallPyramid));
		rowComsumer.receiveMessages();
	}

	public RowComsumer(Pattern pattern) {
		super(ConnectionActiveMqFactory.getInstance());
		this.pattern = pattern;
		this.producerConnection = ConnectionActiveMqFactory.getInstance()
				.createAggregateProducerConnection();
	}

	@Override
	public void consumeRows(List<SimplifiedBookRow> rows) {
		if (pattern.matches(rows)) {
			LOG.debug("pattern matched from row: " + rows.get(0).getRowNumber()
					+ " in book: " + rows.get(0).getBookId().intValue());
			for (SimplifiedBookRow row : rows) {
				printLine(row.getContent());
			}
			producerConnection.sendMessage((Serializable) rows);
		}
	}

	private void printLine(boolean[] line) {
		for (boolean value : line) {
			String toPrint = value ? " " : "X";
			System.out.print(toPrint);
		}
		System.out.println();
	}
}
