package com.hidden.data.monitor.view.command;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

class CommandsPanel extends JPanel {

	private static final long serialVersionUID = -2682355053925283442L;
	private static final int BUTTON_X_OFFSET = 10;
	private static final int BUTTON_Y_OFFSET = 11;
	private static final int BUTTON_Y_DISTANCE = 34;
	private static final int BUTTON_HEIGTH = 23;
	private static final int BUTTON_WIDTH = 140;

	private List<TaskButton> buttonsList = new ArrayList<TaskButton>();
	private int yPosition = BUTTON_Y_OFFSET;

	void draw(Rectangle position) {
		setBounds(position);
		setBorder(BorderFactory.createLineBorder(Color.black));
		drawButtons();
	}

	private void drawButtons() {
		for (TaskButton button : buttonsList) {
			button.draw(new Rectangle(BUTTON_X_OFFSET, yPosition, BUTTON_WIDTH,
					BUTTON_HEIGTH));
			add(button);
			yPosition += BUTTON_Y_DISTANCE;
		}
	}

	void addButton(TaskButton button) {
		buttonsList.add(button);
	}

}
