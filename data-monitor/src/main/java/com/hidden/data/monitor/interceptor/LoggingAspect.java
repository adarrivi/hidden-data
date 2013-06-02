package com.hidden.data.monitor.interceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hidden.data.monitor.view.MonitorApp;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger LOG = Logger.getLogger(LoggingAspect.class);

	@Autowired
	private MonitorApp monitorApp;

	@Before("execution(* com.hidden.data.db.dao..*(..))")
	public void logBefore(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		LOG.debug("Intercepted: " + name);
		monitorApp.setLabel(name);
	}

}
