package com.hidden.data.monitor.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.hidden.data.loader.LibraryLoader;

@Component("monitorApp")
public class MonitorApp extends JFrame {

	private static final long serialVersionUID = 1L;

	@Autowired
	protected LibraryLoader libraryLoader;

	private JLabel label;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationMonitorContext.xml");
		ctx.registerShutdownHook();
		final MonitorApp monitorApp = (MonitorApp) ctx.getBean("monitorApp");
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				monitorApp.setVisible(true);
			}
		});
	}

	public MonitorApp() {
		setTitle("Data Monitor");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));
		label = new JLabel("A label", SwingConstants.CENTER);
		panel.add(label);

		JButton loadButton = new JButton("LoadLibrary");
		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					libraryLoader.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(loadButton);
		getContentPane().add(panel);
	}

	private int times = 0;

	public void setLabel(String text) {
		label.setText(text + times++);
	}
}
