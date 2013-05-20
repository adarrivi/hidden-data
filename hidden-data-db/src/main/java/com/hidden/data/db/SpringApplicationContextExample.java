package com.hidden.data.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContextExample {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SpringApplicationContextExample example = ctx
				.getBean(SpringApplicationContextExample.class);
		example.start();
	}

	@Autowired
	private BookDao bookDao;

	private void start() {
		bookDao.doSomething();
	}

}
