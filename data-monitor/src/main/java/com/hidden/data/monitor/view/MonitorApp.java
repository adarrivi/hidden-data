package com.hidden.data.monitor.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.hidden.data.filter.RowComsumer;
import com.hidden.data.loader.LibraryLoader;
import com.hidden.data.nosql.HelloMongo;
import com.hidden.data.producer.BookProducer;
import com.hidden.data.queue.connection.activemq.ConnectionActiveMqFactory;

@Component("monitorApp")
public class MonitorApp extends JFrame {

	private static final long serialVersionUID = 1L;

	protected Logger LOG = Logger.getLogger(MonitorApp.class);

	@Autowired
	protected LibraryLoader libraryLoader;
	@Autowired
	protected BookProducer bookProducer;
	@Autowired
	protected RowComsumer rowComsumer;
	@Autowired
	private HelloMongo helloMongo;
	private int times;
	private JLabel label;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationMonitorContext.xml");
		ctx.registerShutdownHook();
		final MonitorApp monitorApp = (MonitorApp) ctx.getBean("monitorApp");

		monitorApp.createPanelsAndButtons();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				monitorApp.setVisible(true);
			}
		});
	}

	private void createPanelsAndButtons() {
		rowComsumer.setConnectionFactory(ConnectionActiveMqFactory
				.getInstance());
		bookProducer.setConnectionFactory(ConnectionActiveMqFactory
				.getInstance());

		setTitle("Data Monitor");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));
		label = new JLabel("A label", SwingConstants.CENTER);
		panel.add(label);

		JButton loadButton = new JButton("LoadLibrary");
		loadButton
				.addActionListener(new NewThreadActionListener(libraryLoader));
		panel.add(loadButton);

		JButton produceButton = new JButton("StartProducer");
		produceButton.addActionListener(new NewThreadActionListener(
				bookProducer));
		panel.add(produceButton);

		JButton consumerButton = new JButton("StartConsumer");
		consumerButton.addActionListener(new NewThreadActionListener(
				rowComsumer));
		panel.add(consumerButton);

		JButton mongoButton = new JButton("Mongo");
		mongoButton.addActionListener(new NewThreadActionListener(helloMongo));
		panel.add(mongoButton);

		getContentPane().add(panel);
	}

	public void setLabel(String text) {
		label.setText(text + times++);
	}
}
