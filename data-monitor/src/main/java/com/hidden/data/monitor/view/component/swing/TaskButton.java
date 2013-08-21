package com.hidden.data.monitor.view.component.swing;

import javax.swing.JButton;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.hidden.data.monitor.view.component.Position;
import com.hidden.data.monitor.view.component.ViewComponent;

public class TaskButton extends JButton implements ViewComponent<JButton> {

	private static final long serialVersionUID = 1L;

	private Position position;
	private Runnable task;
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	public TaskButton(String label, Position position, Runnable task,
			ThreadPoolTaskExecutor threadPoolTaskExecutor) {
		super(label);
		this.task = task;
		this.position = position;
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
	}

	@Override
	public void draw() {
		setBounds(position.getX(), position.getY(), position.getWidth(),
				position.getHeight());
		addActionListener(new PooledThreadActionListener(task,
				threadPoolTaskExecutor));
	}

}
