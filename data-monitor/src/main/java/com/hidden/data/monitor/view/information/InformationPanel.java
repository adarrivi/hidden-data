package com.hidden.data.monitor.view.information;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class InformationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int EMPTY_BORDER_OFFSET = 5;
	private static final int ELEMENTS_X_OFFSET = 15;
	private static final int ELEMENTS_Y_OFFSET = 15;
	private static final int PERF_LABEL_HEIGTH = 14;
	private static final int PERF_LABEL_WIDTH = 150;
	private static final int ELEMENTS_SEPARATION = 34;

	private JTextArea display;
	private Rectangle position;
	private Rectangle cursorPosition;

	public InformationPanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public void draw(Rectangle position) {
		this.setBounds(position);
		this.position = position;
		setLayout(null);
		createElements();
	}

	private void createElements() {
		createPerformanceLabel();
		createDisplay();
	}

	private void createPerformanceLabel() {
		JLabel performanceLabel = new JLabel("Performance console",
				SwingConstants.LEFT);
		performanceLabel.setBounds(EMPTY_BORDER_OFFSET, EMPTY_BORDER_OFFSET,
				PERF_LABEL_WIDTH, PERF_LABEL_HEIGTH);
		add(performanceLabel);
		cursorPosition = performanceLabel.getBounds();
		cursorPosition.y = cursorPosition.height + ELEMENTS_SEPARATION;
	}

	private void createDisplay() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(cursorPosition.x, cursorPosition.y,
				getMaximunElementWidth(), getRemainingHeightLeft());
		add(scrollPane);

		display = new JTextArea();
		display.setLineWrap(true);
		display.setEditable(false);
		scrollPane.setViewportView(display);
	}

	private int getMaximunElementWidth() {
		return position.width - (2 * ELEMENTS_X_OFFSET);
	}

	private int getRemainingHeightLeft() {
		return position.height - cursorPosition.y - ELEMENTS_Y_OFFSET;
	}

	public void setDisplayText(String text) {
		display.setText(text);
	}

}
