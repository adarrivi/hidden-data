package com.hidden.data.monitor.view.information;

import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.monitor.view.PanelTest;

public class InformationPanelTest extends PanelTest {

	private static final String DISPLAY_TEXT = "Executions: 3";

	@Mock
	private JTextArea display;

	@InjectMocks
	private InformationPanel victim = new InformationPanel();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		setPanel(victim);
	}

	@Test
	public void setDisplayText_SetsDisplayText() {
		whenSetDisplayText();
		thenDisplayTextShouldBeSet(DISPLAY_TEXT);
	}

	private void whenSetDisplayText() {
		victim.setDisplayText(DISPLAY_TEXT);
	}

	private void thenDisplayTextShouldBeSet(String expectedText) {
		Mockito.verify(display).setText(expectedText);
	}

}
