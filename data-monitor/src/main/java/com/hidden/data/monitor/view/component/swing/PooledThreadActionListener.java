package com.hidden.data.monitor.view.component.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//TODO Disable/enable button depending on thread status
public class PooledThreadActionListener implements ActionListener {

	protected Logger LOG = Logger.getLogger(PooledThreadActionListener.class);

	private Thread thread;
	private Runnable runnable;
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	public PooledThreadActionListener(Runnable runnable,
			ThreadPoolTaskExecutor threadPoolTaskExecutor) {
		this.runnable = runnable;
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (thread != null && thread.isAlive()) {
			LOG.debug("Process is still running");
		} else {
			thread = threadPoolTaskExecutor.newThread(runnable);
			thread.start();
		}
	}

}
