package com.hidden.data.monitor.view.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class PooledThreadActionListenerTest {

	@Mock
	private Runnable runnable;
	@Mock
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	@Mock
	private Thread aThread;

	@InjectMocks
	private PooledThreadActionListener victim = new PooledThreadActionListener(
			runnable, threadPoolTaskExecutor);

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(threadPoolTaskExecutor.newThread(runnable)).thenReturn(
				aThread);
	}

	@Test
	public void actionPerformed_NoThread_StartsNewThread() {
		whenActionPerformed();
		thenNewThreadFromPoolShouldBeStarted();
	}

	private void whenActionPerformed() {
		victim.actionPerformed(null);
	}

	private void thenNewThreadFromPoolShouldBeStarted() {
		Mockito.verify(threadPoolTaskExecutor).newThread(runnable);
		Mockito.verify(aThread).start();
	}

}
