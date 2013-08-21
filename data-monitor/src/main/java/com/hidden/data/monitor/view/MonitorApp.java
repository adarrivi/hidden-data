package com.hidden.data.monitor.view;

import java.awt.Rectangle;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.hidden.data.aggregator.BlockDataAggregator;
import com.hidden.data.filter.RowComsumer;
import com.hidden.data.monitor.interceptor.PerformanceHub;
import com.hidden.data.monitor.view.component.Position;
import com.hidden.data.monitor.view.component.ViewComponent;
import com.hidden.data.monitor.view.component.swing.TaskButton;
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

	@Autowired()
	private Runnable libraryLoader;
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
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	private JPanel mainPanel;
	private InformationPanel informationPanel;

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
		ConnectionActiveMqFactory.getInstance().setVmQueue();
		rowComsumer.setConnectionFactory(ConnectionActiveMqFactory
				.getInstance());
		bookProducer.setConnectionFactory(ConnectionActiveMqFactory
				.getInstance());
	}

	private void createPanelsAndButtons() {
		setTitle("Data Monitor");
		setBounds(100, 100, 600, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(null);

		createInfoLabels();
		createActionButtons();
	}

	private void createInfoLabels() {
		informationPanel = new InformationPanel(new Rectangle(LABEL_X_OFFSET,
				15, 400, 170));
		mainPanel.add(informationPanel);
		createInfoUpdaterTimer();
	}

	private void createInfoUpdaterTimer() {
		UpdatePerformanceInfoTask updatePerformanceInfoTask = new UpdatePerformanceInfoTask();
		updatePerformanceInfoTask.setInformationPanel(informationPanel);
		updatePerformanceInfoTask.setPerformanceHub(performanceHub);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(updatePerformanceInfoTask, 0,
				TimeUnit.SECONDS.toMillis(1));
	}

	// TODO move buttons to different panel
	private void createActionButtons() {
		ViewComponent<JButton> loadButton = new TaskButton("Load Library",
				new Position(BUTTON_X_OFFSET, 11, BUTTON_WIDTH, BUTTON_HEIGTH),
				libraryLoader, threadPoolTaskExecutor);
		loadButton.draw();
		mainPanel.add((JButton) loadButton);

		ViewComponent<JButton> produceButton = new TaskButton("Start Producer",
				new Position(BUTTON_X_OFFSET, 45, BUTTON_WIDTH, BUTTON_HEIGTH),
				bookProducer, threadPoolTaskExecutor);
		produceButton.draw();
		mainPanel.add((JButton) produceButton);

		ViewComponent<JButton> consumerButton = new TaskButton(
				"Start Consumer", new Position(BUTTON_X_OFFSET, 79,
						BUTTON_WIDTH, BUTTON_HEIGTH), rowComsumer,
				threadPoolTaskExecutor);
		consumerButton.draw();
		mainPanel.add((JButton) consumerButton);

		ViewComponent<JButton> showFilteredButton = new TaskButton(
				"ShowFiltered", new Position(BUTTON_X_OFFSET, 113,
						BUTTON_WIDTH, BUTTON_HEIGTH), new Runnable() {

					@Override
					public void run() {
						List<FilteredBlock> allFiltered = filteredBlockDao
								.loadAll();
						informationPanel.setShortInformationText("Filtered: "
								+ allFiltered.size());
					}
				}, threadPoolTaskExecutor);
		showFilteredButton.draw();
		mainPanel.add((JButton) showFilteredButton);

		ViewComponent<JButton> aggregatorButton = new TaskButton(
				"AggregateData", new Position(BUTTON_X_OFFSET, 147,
						BUTTON_WIDTH, BUTTON_HEIGTH), blockDataAggregator,
				threadPoolTaskExecutor);
		aggregatorButton.draw();
		mainPanel.add((JButton) aggregatorButton);

		getContentPane().add(mainPanel);
	}
}
