package com.hidden.data.monitor.view;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.hidden.data.monitor.interceptor.PerformanceHub;
import com.hidden.data.monitor.view.command.CommandPanelFactory;
import com.hidden.data.monitor.view.information.InformationPanel;
import com.hidden.data.nosql.dao.FilteredBlockDao;
import com.hidden.data.queue.connection.activemq.ConnectionActiveMqFactory;

@Component("monitorApp")
public class MonitorApp extends JFrame {

	private static final int MAIN_PANEL_EMPTY_BORDER_WIDTH = 5;
	private static final Rectangle DIALOG_POS = new Rectangle(100, 100, 600,
			250);
	private static final Rectangle COMMAND_PANEL_POS = new Rectangle(
			MAIN_PANEL_EMPTY_BORDER_WIDTH, MAIN_PANEL_EMPTY_BORDER_WIDTH, 150,
			200);
	private static final int PANEL_DISTANCE = 5;
	private static final Rectangle INFO_PANEL_POS = new Rectangle(
			COMMAND_PANEL_POS.x + COMMAND_PANEL_POS.width + PANEL_DISTANCE,
			MAIN_PANEL_EMPTY_BORDER_WIDTH, 400, 200);
	private static final long serialVersionUID = 1L;

	@Autowired
	protected FilteredBlockDao filteredBlockDao;
	@Autowired
	private PerformanceHub performanceHub;
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	@Autowired
	private CommandPanelFactory commandPanelFactory;

	private JPanel mainPanel;
	private JPanel commandsPanel;
	private InformationPanel informationPanel;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationMonitorContext.xml");
		ctx.registerShutdownHook();
		final MonitorApp monitorApp = (MonitorApp) ctx.getBean("monitorApp");

		monitorApp.setVirtualQueueConnection();
		monitorApp.createPanelsAndButtons();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				monitorApp.setVisible(true);
			}
		});
	}

	private void setVirtualQueueConnection() {
		ConnectionActiveMqFactory.getInstance().setVmQueue();
		commandPanelFactory.setQueueConnectionFactory(ConnectionActiveMqFactory
				.getInstance());
	}

	private void createPanelsAndButtons() {
		setTitle("Data Monitor");
		setBounds(DIALOG_POS);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		createCommandsPanel();
		createInfoLabels();
		getContentPane().add(mainPanel);
	}

	private void createCommandsPanel() {
		commandsPanel = commandPanelFactory
				.createCommandsPanel(COMMAND_PANEL_POS);
		mainPanel.add(commandsPanel);
	}

	private void createInfoLabels() {
		informationPanel = new InformationPanel();
		informationPanel.draw(INFO_PANEL_POS);
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
}
