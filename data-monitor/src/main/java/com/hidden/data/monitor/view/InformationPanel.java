package com.hidden.data.monitor.view;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class InformationPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int ELEMENTS_X_OFFSET = 15;
	private static final int ELEMENTS_Y_OFFSET = 15;
	private static final int SHORT_INFORMATION_HEIGTH = 14;
	private static final int ELEMENTS_SEPARATION = 34;

	private JLabel shortInformation;
	private JTextArea display;
	private Rectangle position;
	private Rectangle cursorPosition;

	public InformationPanel(Rectangle position) {
		this.setBounds(position);
		this.position = position;
		this.cursorPosition = new Rectangle(ELEMENTS_X_OFFSET,
				ELEMENTS_Y_OFFSET);
		createElements();
	}

	private void createElements() {
		setLayout(null);
		createShortInformation();
		createDisplay();
	}

	private void createShortInformation() {
		shortInformation = new JLabel("Short Info Label", SwingConstants.LEFT);
		shortInformation.setBounds(cursorPosition.x, cursorPosition.y,
				getMaximunElementWidth(), SHORT_INFORMATION_HEIGTH);
		add(shortInformation);
		cursorPosition.y += ELEMENTS_SEPARATION;
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

	public void setShortInformationText(String text) {
		shortInformation.setText(text);
	}

	public void setDisplayText(String text) {
		display.setText(text);
	}

}
