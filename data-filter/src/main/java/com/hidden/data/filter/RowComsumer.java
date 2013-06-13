package com.hidden.data.filter;

import java.util.List;

import org.apache.log4j.Logger;

import com.hidden.data.queue.connection.activemq.ConnectionActiveMqFactory;
import com.hidden.data.queue.consumer.RowConsumerTemplate;
import com.hidden.data.queue.model.SimplifiedBookRow;

public class RowComsumer extends RowConsumerTemplate {

	private static final Logger LOG = Logger.getLogger(RowComsumer.class);

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

	private Pattern pattern;

	public RowComsumer(Pattern pattern) {
		super(ConnectionActiveMqFactory.getInstance());
		this.pattern = pattern;
	}

	@Override
	public void consumeRows(List<SimplifiedBookRow> rows) {
		if (pattern.matches(rows)) {
			LOG.debug("pattern matched from row: " + rows.get(0).getRowNumber()
					+ " in book: " + rows.get(0).getBookId().intValue());
			for (SimplifiedBookRow row : rows) {
				printLine(row.getContent());
			}
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
