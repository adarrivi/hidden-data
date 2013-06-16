package com.hidden.data.db.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hidden.data.db.dao.PatternDao;
import com.hidden.data.db.model.Pattern;

public class PatternDaoHibernateTest extends InMemoryDaoTest {

	@Autowired
	private PatternDao victim;

	private List<Pattern> patterns;

	@Test
	public void loadAll_ReturnsNotEmpty() {
		whenLoadAll();
		thenPatternsShouldNotBeEmpty();
	}

	private void whenLoadAll() {
		patterns = victim.loadAll();
	}

	private void thenPatternsShouldNotBeEmpty() {
		Assert.assertFalse(patterns.isEmpty());
	}

}
