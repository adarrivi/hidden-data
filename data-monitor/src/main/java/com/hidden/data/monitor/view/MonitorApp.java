package com.hidden.data.monitor.view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.hidden.data.aggregator.BlockDataAggregator;
import com.hidden.data.filter.RowComsumer;
import com.hidden.data.loader.LibraryLoader;
import com.hidden.data.monitor.interceptor.PerformanceHub;
import com.hidden.data.nosql.dao.FilteredBlockDao;
import com.hidden.data.nosql.model.FilteredBlock;
import com.hidden.data.producer.BookProducer;
import com.hidden.data.queue.connection.activemq.ConnectionActiveMqFactory;

@Component("monitorApp")
public class MonitorApp extends JFrame {

	private static final int BUTTON_X_OFFSET = 10;
	private static final int BUTTON_HEIGTH = 23;
	private static final int BUTTON_WIDTH = 140;
	private static final int LABEL_X_OFFSET = BUTTON_X_OFFSET + BUTTON_WIDTH
			+ 20;
	private static final long serialVersionUID = 1L;

	@Autowired
	private LibraryLoader libraryLoader;
	@Autowired
	private BookProducer bookProducer;
	@Autowired
	private RowComsumer rowComsumer;
	@Autowired
	private BlockDataAggregator blockDataAggregator;
	@Autowired
	protected FilteredBlockDao filteredBlockDao;
	@Autowired
	private PerformanceHub performanceHub;

	private JPanel mainPanel;
	private JLabel infoLabel;
	private JTextArea display;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationMonitorContext.xml");
		ctx.registerShutdownHook();
		final MonitorApp monitorApp = (MonitorApp) ctx.getBean("monitorApp");

		monitorApp.initQueueConnection();
		monitorApp.createPanelsAndButtons();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				monitorApp.setVisible(true);
			}
		});
	}

	private void initQueueConnection() {
		rowComsumer.setConnectionFactory(ConnectionActiveMqFactory
				.getInstance());
		bookProducer.setConnectionFactory(ConnectionActiveMqFactory
				.getInstance());
	}

	private void createPanelsAndButtons() {
		setTitle("Data Monitor");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(null);

		createInfoLabels();
		createActionButtons();
	}

	private void createInfoLabels() {
		infoLabel = new JLabel("A label", SwingConstants.CENTER);
		infoLabel.setBounds(LABEL_X_OFFSET, 15, 250, 14);
		mainPanel.add(infoLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(LABEL_X_OFFSET, 49, 250, 121);
		mainPanel.add(scrollPane);

		display = new JTextArea();
		display.setLineWrap(true);
		scrollPane.setViewportView(display);
	}

	private void createActionButtons() {
		JButton loadButton = new JButton("LoadLibrary");
		loadButton.setBounds(BUTTON_X_OFFSET, 11, BUTTON_WIDTH, BUTTON_HEIGTH);
		loadButton
				.addActionListener(new NewThreadActionListener(libraryLoader));
		mainPanel.add(loadButton);

		JButton produceButton = new JButton("StartProducer");
		produceButton.setBounds(BUTTON_X_OFFSET, 45, BUTTON_WIDTH,
				BUTTON_HEIGTH);
		produceButton.addActionListener(new NewThreadActionListener(
				bookProducer));
		mainPanel.add(produceButton);

		JButton consumerButton = new JButton("StartConsumer");
		consumerButton.setBounds(BUTTON_X_OFFSET, 79, BUTTON_WIDTH,
				BUTTON_HEIGTH);
		consumerButton.addActionListener(new NewThreadActionListener(
				rowComsumer));
		mainPanel.add(consumerButton);

		JButton showFilteredButton = new JButton("ShowFiltered");
		showFilteredButton.setBounds(BUTTON_X_OFFSET, 113, BUTTON_WIDTH,
				BUTTON_HEIGTH);
		showFilteredButton.addActionListener(new NewThreadActionListener(
				new Runnable() {

					@Override
					public void run() {
						List<FilteredBlock> allFiltered = filteredBlockDao
								.loadAll();
						setInfoLabel("Filtered: " + allFiltered.size());
					}
				}));
		mainPanel.add(showFilteredButton);

		JButton aggregatorButton = new JButton("AggregateData");
		aggregatorButton.setBounds(BUTTON_X_OFFSET, 147, BUTTON_WIDTH,
				BUTTON_HEIGTH);
		aggregatorButton.addActionListener(new NewThreadActionListener(
				blockDataAggregator));
		mainPanel.add(aggregatorButton);

		JButton showPerformanceButton = new JButton("ShowPerformance");
		showPerformanceButton.setBounds(BUTTON_X_OFFSET, 181, BUTTON_WIDTH,
				BUTTON_HEIGTH);
		showPerformanceButton.addActionListener(new NewThreadActionListener(
				new Runnable() {

					@Override
					public void run() {
						display.setText(performanceHub.prettyPrint());
					}
				}));
		mainPanel.add(showPerformanceButton);

		getContentPane().add(mainPanel);
	}

	public void setInfoLabel(String text) {
		infoLabel.setText(text);
	}

}
