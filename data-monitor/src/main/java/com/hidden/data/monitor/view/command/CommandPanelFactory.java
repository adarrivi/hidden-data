package com.hidden.data.monitor.view.command;

import java.awt.Rectangle;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.hidden.data.aggregator.BlockDataAggregator;
import com.hidden.data.filter.RowComsumer;
import com.hidden.data.producer.BookProducer;
import com.hidden.data.queue.connection.QueueConnectionFactory;

@Component
public class CommandPanelFactory {

	@Autowired
	private Runnable libraryLoader;
	@Autowired
	private BookProducer bookProducer;
	@Autowired
	private RowComsumer rowComsumer;
	@Autowired
	private BlockDataAggregator blockDataAggregator;
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	public JPanel createCommandsPanel(Rectangle position) {
		CommandsPanel panel = new CommandsPanel();
		panel.addButton(createLoadLibraryButton());
		panel.addButton(createStartProducerButton());
		panel.addButton(createStartConsumerButton());
		panel.addButton(createAggregateDataButton());
		panel.draw(position);
		return panel;
	}

	private TaskButton createLoadLibraryButton() {
		return new TaskButton("Load Library", libraryLoader,
				threadPoolTaskExecutor);
	}

	private TaskButton createStartProducerButton() {
		return new TaskButton("Start Producer", bookProducer,
				threadPoolTaskExecutor);
	}

	private TaskButton createStartConsumerButton() {
		return new TaskButton("Start Consumer", rowComsumer,
				threadPoolTaskExecutor);
	}

	private TaskButton createAggregateDataButton() {
		return new TaskButton("Aggregate Data", blockDataAggregator,
				threadPoolTaskExecutor);
	}

	public void setQueueConnectionFactory(
			QueueConnectionFactory connectionFactory) {
		rowComsumer.setConnectionFactory(connectionFactory);
		bookProducer.setConnectionFactory(connectionFactory);
	}
}
