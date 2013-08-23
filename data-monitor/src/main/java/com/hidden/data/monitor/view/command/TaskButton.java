package com.hidden.data.monitor.view.command;

import java.awt.Rectangle;

import javax.swing.JButton;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

class TaskButton extends JButton {

	private static final long serialVersionUID = 5672086126747756862L;

	private Runnable task;
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	TaskButton(String label, Runnable task,
			ThreadPoolTaskExecutor threadPoolTaskExecutor) {
		super(label);
		this.task = task;
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
	}

	void draw(Rectangle position) {
		setBounds(position);
		addActionListener(new PooledThreadActionListener(task,
				threadPoolTaskExecutor));
	}

}
