package com.hidden.data.db.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hidden.data.db.dao.AuthorDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class AuthorDaoHibernateTest {

	@Autowired
	private AuthorDao victim;

	@Test
	public void findByname() {
		victim.findByName("name");
	}

}
