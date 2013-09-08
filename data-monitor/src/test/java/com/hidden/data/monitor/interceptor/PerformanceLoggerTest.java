package com.hidden.data.monitor.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.common.performance.PerformanceLogged;

public class PerformanceLoggerTest {

	private static final String METHOD_ID = "saveIfNotExist";
	@Mock
	private PerformanceHub performanceHub;
	@Mock
	private PerformanceLogged annotation;
	@Mock
	private ProceedingJoinPoint joinPoint;

	@InjectMocks
	private PerformanceLogger victim = new PerformanceLogger();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(annotation.identifier()).thenReturn(METHOD_ID);
	}

	@Test
	public void logExecutionTime_StoresExecutionInfoInHub() throws Throwable {
		whenLogExecutionTime();
		thenExecutionShouldBeSotoredInHub();
	}

	private void whenLogExecutionTime() throws Throwable {
		victim.logExecutionTime(joinPoint, annotation);
	}

	private void thenExecutionShouldBeSotoredInHub() {
		Mockito.verify(performanceHub).addExecution(Matchers.eq(METHOD_ID),
				Matchers.anyLong());
	}

	@Test
	public void logExecutionTime_ExecutionThrosEx_StoresExecutionInfoInHub()
			throws Throwable {
		expectException();
		givenExecutionThrowsEx();
		whenLogExecutionTime();
		thenExecutionShouldBeSotoredInHub();
	}

	private void expectException() {
		expectedException.expect(Exception.class);
	}

	private void givenExecutionThrowsEx() throws Throwable {
		Mockito.doThrow(Exception.class).when(joinPoint).proceed();
	}

}
