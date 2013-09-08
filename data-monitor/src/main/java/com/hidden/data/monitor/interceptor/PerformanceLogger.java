package com.hidden.data.monitor.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hidden.data.common.performance.PerformanceLogged;

@Aspect
@Component
public class PerformanceLogger {

	@Autowired
	private PerformanceHub performanceHub;

	@Around("@annotation(performanceAnnotation)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint,
			PerformanceLogged performanceAnnotation) throws Throwable {
		String identifier = performanceAnnotation.identifier();
		long currentTimeMillis = System.currentTimeMillis();
		try {
			return joinPoint.proceed();
		} catch (Throwable e) {
			throw e;
		} finally {
			currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
			performanceHub.addExecution(identifier, currentTimeMillis);
		}
	}

}
