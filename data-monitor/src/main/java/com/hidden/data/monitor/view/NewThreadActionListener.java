package com.hidden.data.monitor.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;

public class NewThreadActionListener implements ActionListener {

	protected Logger LOG = Logger.getLogger(NewThreadActionListener.class);

	private Thread thread;
	private Runnable runnable;

	public NewThreadActionListener(Runnable runnable) {
		this.runnable = runnable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (thread != null && thread.isAlive()) {
			LOG.debug("Process is still running");
		} else {
			thread = new Thread(runnable);
			thread.start();
		}
	}

}
