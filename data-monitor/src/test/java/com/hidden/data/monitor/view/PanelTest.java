package com.hidden.data.monitor.view;

import java.awt.Rectangle;

import org.junit.Test;

public abstract class PanelTest {

	private Panel panel;

	protected void setPanel(Panel panel) {
		this.panel = panel;
	}

	@Test
	public void draw() {
		panel.draw(new Rectangle(0, 0, 10, 10));
	}

}
