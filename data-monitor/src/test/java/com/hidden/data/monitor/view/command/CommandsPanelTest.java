package com.hidden.data.monitor.view.command;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.hidden.data.monitor.view.PanelTest;

public class CommandsPanelTest extends PanelTest {

	private TaskButton button = new TaskButton("", null, null);

	@InjectMocks
	private CommandsPanel victim = new CommandsPanel();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		setPanel(victim);
		victim.addButton(button);
	}
}
